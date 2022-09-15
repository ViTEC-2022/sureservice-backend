package com.sureservice.backend.employee.service;

import com.sureservice.backend.employee.domain.model.entity.Employee;
import com.sureservice.backend.employee.domain.persistence.EmployeeRepository;
import com.sureservice.backend.employee.domain.service.EmployeeService;
import com.sureservice.backend.service.domain.persistence.ServiceRepository;
import com.sureservice.backend.shared.exception.ResourceNotFoundException;
import com.sureservice.backend.shared.exception.ResourceValidationException;
import com.sureservice.backend.user.domain.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String ENTITY = "Employee";
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final Validator validator;

    public EmployeeServiceImpl(UserRepository userRepository, ServiceRepository serviceRepository, EmployeeRepository employeeRepository, Validator validator) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
        this.validator = validator;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getAllByServiceId(Long serviceId) {
        return employeeRepository.findAllByServiceId(serviceId);
    }

    @Override
    public Employee getById(Long userId) {
        return employeeRepository.findByUserId(userId);
    }

    @Override
    public Employee getByServiceId(Long serviceId) {
        return employeeRepository.findByServiceId(serviceId);
    }

    @Override
    public Employee create(Long userId,Long serviceId,Employee employee) {
        Set<ConstraintViolation<Employee>> violations=validator.validate(employee);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        return userRepository.findById(userId).map(data -> {
            employee.setUser(data);
            return serviceRepository.findById(serviceId).map(service->{
                employee.setService(service);
                return employeeRepository.save(employee);
            }).orElseThrow(() -> new ResourceNotFoundException("Service", serviceId));
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public Employee update(Long employeeId, Employee employee) {
        Set<ConstraintViolation<Employee>> violations=validator.validate(employee);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        if(!employeeRepository.existsById(employeeId))
            throw new ResourceNotFoundException("Employee", employeeId);

        return employeeRepository.findById(employeeId).map(data ->
                        employeeRepository.save(data.withAge(employee.getAge())
                                .withAltphone(employee.getAltphone())
                                .withDescription(employee.getDescription())
                                .withPhone(employee.getPhone())
                                .withUrlToImage(employee.getUrlToImage())
                        ))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, employeeId));
    }

    @Override
    public ResponseEntity<?> delete(Long employeeId) {
        return employeeRepository.findById(employeeId).map(data ->{
            employeeRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,employeeId));
    }
}

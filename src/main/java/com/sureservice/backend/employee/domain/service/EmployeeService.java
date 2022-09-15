package com.sureservice.backend.employee.domain.service;

import com.sureservice.backend.employee.domain.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Page<Employee> getAll(Pageable pageable);
    List<Employee> getAllByServiceId(Long serviceId);
    Employee getById(Long userId);
    Employee getByServiceId(Long serviceId);
    Employee create(Long userId, Long serviceId, Employee employee);
    Employee update(Long employeeId, Employee employee);
    ResponseEntity<?> delete(Long employeeId);
}

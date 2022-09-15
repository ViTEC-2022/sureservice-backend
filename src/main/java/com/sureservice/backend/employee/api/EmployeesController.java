package com.sureservice.backend.employee.api;

import com.sureservice.backend.employee.domain.service.EmployeeService;
import com.sureservice.backend.employee.mapping.EmployeeMapper;
import com.sureservice.backend.employee.resource.CreateEmployeeResource;

import com.sureservice.backend.employee.resource.EmployeeResource;
import com.sureservice.backend.employee.resource.UpdateEmployeeResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employees", description = "Create, read, update and delete employees")
@RestController
@RequestMapping(value = "api/v1/employees")
public class EmployeesController {
    private final EmployeeService employeeService;
    private final EmployeeMapper mapper;

    public EmployeesController(EmployeeService employeeService, EmployeeMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<EmployeeResource> getAllEmployees(){
        return mapper.modelListToResource(employeeService.getAll());
    }

    @GetMapping("users/{userId}")
    public EmployeeResource getEmployeeUserById(@PathVariable("userId") Long userId){
        return mapper.toResource(employeeService.getById(userId));
    }

    @GetMapping("services/{serviceId}")
    public List<EmployeeResource> getEmployeeByServiceId(@PathVariable("serviceId") Long serviceId){
        return mapper.modelListToResource(employeeService.getAllByServiceId(serviceId));
    }

    @PostMapping("{userId}/{serviceId}")
    public EmployeeResource createEmployee(@PathVariable("userId") Long userId,@PathVariable("serviceId")Long serviceId,@RequestBody CreateEmployeeResource resource){
        return mapper.toResource(employeeService.create(userId,serviceId,mapper.toModel(resource)));
    }

    @PutMapping("{employeeId}")
    private EmployeeResource updateEmployee(@PathVariable("employeeId") Long employeeId,@RequestBody UpdateEmployeeResource resource){
        return mapper.toResource(employeeService.update(employeeId,mapper.toModel(resource)));
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId){
        return employeeService.delete(employeeId);
    }
}

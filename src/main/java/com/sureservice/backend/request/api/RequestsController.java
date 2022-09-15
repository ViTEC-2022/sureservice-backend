package com.sureservice.backend.request.api;

import com.sureservice.backend.request.domain.service.RequestService;
import com.sureservice.backend.request.mapping.RequestMapper;
import com.sureservice.backend.request.resource.CreateRequestResource;
import com.sureservice.backend.request.resource.RequestResource;
import com.sureservice.backend.request.resource.UpdateRequestResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Requests", description = "Create, read, update and delete requests")
@RestController
@RequestMapping(value = "api/v1/requests")
public class RequestsController {
    private final RequestService requestService;
    private final RequestMapper mapper;

    public RequestsController(RequestService requestService, RequestMapper mapper) {
        this.requestService = requestService;
        this.mapper = mapper;
    }
    @GetMapping
    public List<RequestResource> getAll() {
        return mapper.modelListToResource(requestService.getAll());
    }
    @GetMapping("{requestId}")
    public RequestResource getById(@PathVariable Long requestId) {
        return mapper.toResource(requestService.getById(requestId));
    }
    @GetMapping("clients/{clientId}")
    public List<RequestResource> getAllRequestsByClientId(@PathVariable Long clientId) {
        return mapper.modelListToResource(requestService.getAllByClientId(clientId));
    }
    @GetMapping("employees/{employeeId}")
    public List<RequestResource> getAllRequestByEmployeeId(@PathVariable Long employeeId) {
        return mapper.modelListToResource(requestService.getAllByEmployeeId(employeeId));
    }

    @PostMapping("{clientId}/{employeeId}/{serviceId}")
    public RequestResource createRequest(@PathVariable("clientId") Long clientId,@PathVariable("employeeId")
    Long employeeId,@PathVariable("serviceId") Long serviceId, @RequestBody CreateRequestResource request) {
        return mapper.toResource(requestService.create(clientId,employeeId,serviceId, mapper.toModel(request)));
    }

    @PutMapping("{requestId}")
    public RequestResource updateRequest(@PathVariable Long requestId, @RequestBody UpdateRequestResource request) {
        return mapper.toResource(requestService.update(requestId, mapper.toModel(request)));
    }

    @DeleteMapping("{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long requestId) {
        return requestService.delete(requestId);
    }
}

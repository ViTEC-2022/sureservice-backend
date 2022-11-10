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
    @GetMapping("done/{done}/{employeeId}")
    public List<RequestResource> getAllByConfirmationAndEmployeeId(@PathVariable Long employeeId,@PathVariable Boolean done) {
        return mapper.modelListToResource(requestService.getAllByDoneAndEmployeeId(done,employeeId));
    }
    @GetMapping("paid/{paid}/employees/{employeeId}")
    public List<RequestResource> getAllByPaidAndEmployeeId(@PathVariable Long employeeId,@PathVariable Boolean paid) {
        return mapper.modelListToResource(requestService.getAllByPaidAndEmployeeId(paid,employeeId));
    }
    @GetMapping("paid/{paid}/clients/{clientId}")
    public List<RequestResource> getAllByPaidAndClientId(@PathVariable Long clientId,@PathVariable Boolean paid) {
        return mapper.modelListToResource(requestService.getAllByPaidAndClientId(paid,clientId));
    }

    @PostMapping("{clientId}/{employeeId}")
    public RequestResource createRequest(@PathVariable("clientId") Long clientId,@PathVariable("employeeId")
    Long employeeId, @RequestBody CreateRequestResource request) {
        return mapper.toResource(requestService.create(clientId,employeeId, mapper.toModel(request)));
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

package com.sureservice.backend.client.api;

import com.sureservice.backend.client.domain.service.ClientService;
import com.sureservice.backend.client.mapping.ClientMapper;
import com.sureservice.backend.client.resource.ClientResource;
import com.sureservice.backend.client.resource.CreateClientResource;
import com.sureservice.backend.client.resource.UpdateClientResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clients", description = "Create, read, update and delete clients")
@RestController
@RequestMapping(value = "api/v1/clients")
public class ClientsController {
    private final ClientService clientService;
    private final ClientMapper mapper;


    public ClientsController(ClientService clientService, ClientMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ClientResource> getAllClients(Pageable pageable){
        return mapper.modelListPage(clientService.getAll(),pageable);
    }

    @GetMapping("{userId}")
    public ClientResource getClientByUserId(@PathVariable("userId") Long userId){
        return mapper.toResource(clientService.getByUserId(userId));
    }

    @GetMapping("client/{clientId}")
    public ClientResource getClientByClientId(@PathVariable("clientId") Long clientId){
        return mapper.toResource(clientService.getById(clientId));
    }

    @PostMapping("{userId}")
    public ClientResource createClient(@PathVariable("userId") Long userId,@RequestBody CreateClientResource resource){
        return mapper.toResource(clientService.create(userId, mapper.toModel(resource)));
    }

    @PutMapping("{clientId}")
    private ClientResource updateClient(@PathVariable("clientId") Long clientId,@RequestBody UpdateClientResource resource){
        return mapper.toResource(clientService.update(clientId,mapper.toModel(resource)));
    }

    @DeleteMapping("{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId){
        return clientService.delete(clientId);
    }
}

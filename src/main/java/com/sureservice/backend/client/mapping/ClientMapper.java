package com.sureservice.backend.client.mapping;

import com.sureservice.backend.client.domain.model.entity.Client;
import com.sureservice.backend.client.resource.ClientResource;
import com.sureservice.backend.client.resource.CreateClientResource;
import com.sureservice.backend.client.resource.UpdateClientResource;
import com.sureservice.backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClientMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ClientResource toResource(Client model){
        return mapper.map(model,ClientResource.class);
    }

    public Page<ClientResource> modelListPage(List<Client> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ClientResource.class),pageable,modelList.size());
    }

    public Client toModel(CreateClientResource resource){ return mapper.map(resource,Client.class);}

    public Client toModel(UpdateClientResource resource){
        return mapper.map(resource,Client.class);
    }
}

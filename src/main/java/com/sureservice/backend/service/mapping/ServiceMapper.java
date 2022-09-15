package com.sureservice.backend.service.mapping;

import com.sureservice.backend.service.domain.model.entity.Service;
import com.sureservice.backend.service.resource.CreateServiceResource;
import com.sureservice.backend.service.resource.ServiceResource;
import com.sureservice.backend.service.resource.UpdateServiceResource;
import com.sureservice.backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ServiceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping
    public ServiceResource toResource(Service model){
        return mapper.map(model,ServiceResource.class);
    }

    public Page<ServiceResource> modelListPage(List<Service> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ServiceResource.class),pageable,modelList.size());
    }


    public List<ServiceResource> modelListToResource(List<Service> modelList){
        return mapper.mapList(modelList, ServiceResource.class);
    }

    public Service toModel(CreateServiceResource resource){
        return mapper.map(resource,Service.class);
    }

    public Service toModel(UpdateServiceResource resource){
        return mapper.map(resource,Service.class);
    }
}

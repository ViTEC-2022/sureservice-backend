package com.sureservice.backend.request.mapping;

import com.sureservice.backend.request.domain.model.entity.Request;
import com.sureservice.backend.request.resource.CreateRequestResource;
import com.sureservice.backend.request.resource.RequestResource;
import com.sureservice.backend.request.resource.UpdateRequestResource;
import com.sureservice.backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RequestMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping
    public RequestResource toResource(Request model){
        return mapper.map(model,RequestResource.class);
    }

    public Page<RequestResource> modelListPage(List<Request> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,RequestResource.class),pageable,modelList.size());
    }

    public List<RequestResource> modelListToResource(List<Request> modelList){return mapper.mapList(modelList, RequestResource.class); }

    public Request toModel(CreateRequestResource resource){
        return mapper.map(resource,Request.class);
    }

    public Request toModel(UpdateRequestResource resource){
        return mapper.map(resource,Request.class);
    }
}

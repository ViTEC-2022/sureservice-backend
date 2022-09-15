package com.sureservice.backend.security.domain.service.communication;

import com.sureservice.backend.shared.domain.service.communication.BaseResponse;
import com.sureservice.backend.user.resource.UserResource;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}
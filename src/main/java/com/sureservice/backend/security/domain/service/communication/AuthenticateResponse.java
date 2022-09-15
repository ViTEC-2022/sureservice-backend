package com.sureservice.backend.security.domain.service.communication;

import com.sureservice.backend.security.resource.AuthenticateResource;
import com.sureservice.backend.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}

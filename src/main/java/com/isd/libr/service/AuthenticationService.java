package com.isd.libr.service;

import com.isd.libr.web.entity.Person;
import com.isd.libr.web.dto.requests.RegisterRequest;

public interface AuthenticationService {
    Person create(RegisterRequest request, String password);


}
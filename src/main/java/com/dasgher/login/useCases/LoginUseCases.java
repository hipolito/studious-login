package com.dasgher.login.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dasgher.login.controllers.login.model.LoginRequest;
import com.dasgher.login.services.Auth0Service;

@Component
public class LoginUseCases {
    
    @Autowired
    Auth0Service auth0Service;

    public String doLogin(LoginRequest loginInformation) {
        return auth0Service.login(loginInformation.login(), loginInformation.password());
    }

}

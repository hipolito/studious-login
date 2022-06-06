package com.dasgher.login.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;

@Component
public class Auth0Service {
    private AuthAPI auth;

    public Auth0Service(
        @Value("${auth0.domain}") String domain,
        @Value("${auth0.clientId}") String clientId,
        @Value("${auth0.clientSecret}") String clientSecret){
        auth = new AuthAPI(domain, clientId, clientSecret);
    }

    public String login(String emailOrUsername, String password){
        try {
            TokenHolder result = auth.login(emailOrUsername, password.toCharArray()).setScope("openid email").execute();
            return result.getAccessToken();
        } catch (Auth0Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}

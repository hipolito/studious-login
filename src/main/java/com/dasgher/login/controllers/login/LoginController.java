package com.dasgher.login.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasgher.login.controllers.login.model.*;
import com.dasgher.login.useCases.LoginUseCases;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    LoginUseCases loginUseCases;

    @PostMapping("/signin")
    public ResponseEntity<Object> doSigning(@RequestBody LoginRequest loginInformation){
        if(loginInformation == null) return new ResponseEntity<Object>("Login info missed", HttpStatus.BAD_REQUEST);

        var token = loginUseCases.doLogin(loginInformation);
        
        return new ResponseEntity<Object>(new LoginResponse("User logged in", token, HttpStatus.OK.toString()), HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntity<String> doSignout(@RequestBody LogoutRequest logoutInformation){
        if(logoutInformation == null) return new ResponseEntity<String>("Logout info missed", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("logoutDone", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> doRegister(@RequestBody RegisterAccountRequest registerInformation){        
        if(registerInformation == null) return new ResponseEntity<String>("Register info missed", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("registerDone", HttpStatus.OK);
    }

    @PostMapping("/recover")
    public ResponseEntity<String> doRecover(@RequestBody RecoverAccountRequest recoverInformation){
        if(recoverInformation == null) return new ResponseEntity<String>("Register info missed", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("recoverDone", HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMissingRequestBody(HttpMessageNotReadableException ex) {
        return new ResponseEntity<Object>("Request Body is missing", HttpStatus.BAD_REQUEST);
    }

}

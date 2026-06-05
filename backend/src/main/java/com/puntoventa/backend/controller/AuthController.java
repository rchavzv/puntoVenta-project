package com.puntoventa.backend.controller;

import com.puntoventa.backend.dto.LoginRequest;
import com.puntoventa.backend.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController{
  
  private final AuthService authService;

  public AuthController(AuthService authService){
    this.authService=authService;
  }

@PostMapping("/login")
public String login(@RequestBody LoginRequest request){

  boolean loginOk=
    authService.login(
        request.getUsername(),
        request.getPassword()
        );
  if(loginOk){
    return "LOGIN OK";
  }
  
  return "LOGIN FAIL";

}

}



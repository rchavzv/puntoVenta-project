package com.puntoventa.backend.controller;

import com.puntoventa.backend.dto.LoginRequest;
import com.puntoventa.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController{
  
  private final AuthService authService;

  public AuthController(AuthService authService){
    this.authService=authService;
  }

@PostMapping("/login")
public ResponseEntity<String> login(
    @RequestBody LoginRequest request){

  boolean loginOk = authService.login(
        request.getUsername(),
        request.getPassword()
        );
  if(loginOk){
    return ResponseEntity.ok("LOGIN OK");
  }
  
  return ResponseEntity
    .status(HttpStatus.UNAUTHORIZED)
    .body("Usuario o contraseña incorrectos");

}

}



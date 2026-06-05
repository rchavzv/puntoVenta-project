package com.puntoventa.backend.service;

import com.puntoventa.backend.entity.SysUsr;
import com.puntoventa.backend.repository.SysUsrRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService{
  
  private final SysUsrRepository repo;

  public AuthService(SysUsrRepository repo){
    this.repo = repo;
  }

  public boolean login(String username, String password){

    SysUsr user = repo.findByUsername(username)
          .orElse(null);

    if(user == null){
      return false;
    }
    
    if(user.getStatus() == null || !user.getStatus()){
      return false;
    }

    return user.getPassword().equals(password);

  }


}













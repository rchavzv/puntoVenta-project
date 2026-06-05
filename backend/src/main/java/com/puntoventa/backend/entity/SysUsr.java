package com.puntoventa.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_usrs")
public class SysUsr {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sys_usrs_id")
  private Long id;
  
  @Column(name = "sys_usrs_name")
  private String name;

  @Column(name = "sys_usrs_lastName")
  private String lastName;

  @Column(name = "sys_usrs_usrName")
  private String username;

  @Column(name = "sys_usrs_password")
  private String password;

  @Column(name = "sys_usrs_phone")
  private String phone;

  @Column(name = "sys_usrs_email")
  private String email;

  @Column(name = "sys_usrs_status")
  private Boolean status;

  @Column(name = "sys_usrs_fechaIngreso")
  private LocalDate fechaIngreso;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

public Long getId(){ return id; }

public String getUsername(){ return username; }

public String getPassword(){ return password; }

public Boolean getStatus(){ return status; }

public void setUsername(String username){
  this.username=username;
}

public void setPassword(String password){
  this.password=password;
}


}



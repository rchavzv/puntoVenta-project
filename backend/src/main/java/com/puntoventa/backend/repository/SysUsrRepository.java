package com.puntoventa.backend.repository;

import com.puntoventa.backend.entity.SysUsr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SysUsrRepository extends JpaRepository<SysUsr, Long>{
  
  Optional<SysUsr> findByUsername(String username);

}

package com.puntoventa.backend.repository;

import com.puntoventa.backend.entity.SysProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysProductRepository extends JpaRepository<SysProduct, Long> {
}
package com.puntoventa.backend.controller;

import com.puntoventa.backend.entity.SysProduct;
import com.puntoventa.backend.service.SysProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class SysProductController {

    private final SysProductService service;

    public SysProductController(SysProductService service) {
        this.service = service;
    }

    // 🟢 POST /api/products → crear producto
    @PostMapping
    public SysProduct create(@RequestBody SysProduct product) {
        return service.save(product);
    }

    // 🟢 GET /api/products → listar productos
    @GetMapping
    public List<SysProduct> getAll() {
        return service.findAll();
    }

    // 🟢 TEST de conexión
    @GetMapping("/ping")
    public String ping() {
        return "PING OK";
    }
}
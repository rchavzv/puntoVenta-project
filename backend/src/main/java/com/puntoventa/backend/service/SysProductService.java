package com.puntoventa.backend.service;

import com.puntoventa.backend.entity.SysProduct;
import com.puntoventa.backend.repository.SysProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysProductService {

    private final SysProductRepository repository;
    private final ProductEventService eventService;

    public SysProductService(SysProductRepository repository,
                             ProductEventService eventService) {
        this.repository = repository;
        this.eventService = eventService;
    }

public SysProduct save(SysProduct product) {

    System.out.println("🔥 SysProductService.save EJECUTADO");

    SysProduct saved = repository.save(product);

    eventService.notifyProductsUpdated();

    return saved;
}

    public List<SysProduct> findAll() {
        return repository.findAll();
    }
}
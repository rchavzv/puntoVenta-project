package com.puntoventa.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_products")
public class SysProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sys_products_id")
    private Long sysProductsId;

    @Column(name = "sys_products_name", nullable = false)
    private String sysProductsName;

    @Column(name = "sys_products_sku", nullable = false, unique = true)
    private String sysProductsSku;

    @Column(name = "sys_products_description")
    private String sysProductsDescription;

    @Column(name = "sys_products_purchase_price", nullable = false)
    private BigDecimal sysProductsPurchasePrice;

    @Column(name = "sys_products_sale_price", nullable = false)
    private BigDecimal sysProductsSalePrice;

    @Column(name = "sys_products_tax")
    private BigDecimal sysProductsTax = new BigDecimal("16.00");

    @Column(name = "sys_products_stock", nullable = false)
    private Integer sysProductsStock;

    @Column(name = "sys_products_min_stock")
    private Integer sysProductsMinStock = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    // =====================
    // Lifecycle hooks
    // =====================

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        modifiedAt = LocalDateTime.now();
    }

    // =====================
    // Getters y Setters
    // =====================

    public Long getSysProductsId() {
        return sysProductsId;
    }

    public void setSysProductsId(Long sysProductsId) {
        this.sysProductsId = sysProductsId;
    }

    public String getSysProductsName() {
        return sysProductsName;
    }

    public void setSysProductsName(String sysProductsName) {
        this.sysProductsName = sysProductsName;
    }

    public String getSysProductsSku() {
        return sysProductsSku;
    }

    public void setSysProductsSku(String sysProductsSku) {
        this.sysProductsSku = sysProductsSku;
    }

    public String getSysProductsDescription() {
        return sysProductsDescription;
    }

    public void setSysProductsDescription(String sysProductsDescription) {
        this.sysProductsDescription = sysProductsDescription;
    }

    public BigDecimal getSysProductsPurchasePrice() {
        return sysProductsPurchasePrice;
    }

    public void setSysProductsPurchasePrice(BigDecimal sysProductsPurchasePrice) {
        this.sysProductsPurchasePrice = sysProductsPurchasePrice;
    }

    public BigDecimal getSysProductsSalePrice() {
        return sysProductsSalePrice;
    }

    public void setSysProductsSalePrice(BigDecimal sysProductsSalePrice) {
        this.sysProductsSalePrice = sysProductsSalePrice;
    }

    public BigDecimal getSysProductsTax() {
        return sysProductsTax;
    }

    public void setSysProductsTax(BigDecimal sysProductsTax) {
        this.sysProductsTax = sysProductsTax;
    }

    public Integer getSysProductsStock() {
        return sysProductsStock;
    }

    public void setSysProductsStock(Integer sysProductsStock) {
        this.sysProductsStock = sysProductsStock;
    }

    public Integer getSysProductsMinStock() {
        return sysProductsMinStock;
    }

    public void setSysProductsMinStock(Integer sysProductsMinStock) {
        this.sysProductsMinStock = sysProductsMinStock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
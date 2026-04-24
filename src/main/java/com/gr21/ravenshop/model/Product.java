package com.gr21.ravenshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private String id;
    private String name;
    private String category;
    private BigDecimal price;
    private int stock;
    private List<String> tags = new ArrayList<>();
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @JsonIgnore
    private String description;

    @JsonIgnore
    private boolean active = true;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags == null ? new ArrayList<>() : new ArrayList<>(tags);
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public String getDescription() {
        return description;
    }

    @JsonIgnore
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public BigDecimal getUnitPrice() {
        return price;
    }

    @JsonIgnore
    public void setUnitPrice(BigDecimal unitPrice) {
        this.price = unitPrice;
    }

    @JsonIgnore
    public boolean isActive() {
        return active;
    }

    @JsonIgnore
    public void setActive(boolean active) {
        this.active = active;
    }
}

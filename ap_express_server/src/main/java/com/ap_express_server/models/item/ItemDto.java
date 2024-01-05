package com.ap_express_server.models.item;

import java.time.LocalDate;

public class ItemDto {

    private Long id;

    private String name;

    private String description;

    private String categoryName;

    private String uomName;

    private float unitPrice;

    private String createdBy;

    private LocalDate createdDate;

    public ItemDto(Long id, String name, String description, String categoryName,
                   String uomName, float unitPrice, String createdBy, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
        this.uomName = uomName;
        this.unitPrice = unitPrice;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}

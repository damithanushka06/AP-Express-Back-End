package com.ap_express_server.models.item;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemDto {

    private Integer id;

    private String name;

    private String description;

    private String categoryName;

    private String uomName;

    private float unitPrice;

    private String createdBy;

    private LocalDate createdDate;

    public ItemDto(Integer id, String name, String description, String categoryName,
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
}

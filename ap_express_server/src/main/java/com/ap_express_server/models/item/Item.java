package com.ap_express_server.models.item;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "item_master")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String number;

    private String description;

    private int categoryId;

    private int uomId;

    private float unitPrice;

    private String createdBy;

    private LocalDate createdDate;

    public Item(Integer id, String name, String description, int categoryId, int uomId, float unitPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.uomId = uomId;
        this.unitPrice = unitPrice;
    }

    public Item() {
    }
}

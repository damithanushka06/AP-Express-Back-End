package com.ap_express_server.models.common;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    private Integer id;

    private String name;

    private String description;
}

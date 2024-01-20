package com.ap_express_server.models.common;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uom")
@Data
public class UOM {
    @Id
    private Integer id;

    private String name;
}

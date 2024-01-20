package com.ap_express_server.models.common;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "account_type")
public class AccountType {
    @Id
    private Integer id;

    private String name;

    private String description;

    public AccountType(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public AccountType() {

    }
}

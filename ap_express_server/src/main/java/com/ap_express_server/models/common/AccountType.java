package com.ap_express_server.models.common;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_type")
public class AccountType {
    @Id
    private Long id;

    private String name;

    private String description;

    public AccountType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public AccountType() {

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
}

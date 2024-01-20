package com.ap_express_server.models.payment;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "payment_term")
public class PaymentTerm {
    @Id
    private Integer id;

    private String name;

    private String description;

    public PaymentTerm(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PaymentTerm() {

    }
}

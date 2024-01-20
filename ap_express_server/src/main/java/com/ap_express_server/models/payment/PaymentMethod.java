package com.ap_express_server.models.payment;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_method")
@Data
public class PaymentMethod {

    @Id
    private Integer id;

    private String name;

    private String description;

    public PaymentMethod(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PaymentMethod() {

    }


}

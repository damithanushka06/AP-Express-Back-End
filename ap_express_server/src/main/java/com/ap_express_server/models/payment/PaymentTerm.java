package com.ap_express_server.models.payment;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_term")
public class PaymentTerm {
    @Id
    private Long id;

    private String name;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentTerm(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PaymentTerm() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    private int days;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

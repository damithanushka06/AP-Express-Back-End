package com.ap_express_server.models.bill;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bill_account_information")
@Data
public class BillAccountInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer billId;

    private int accountId;

    private transient String accountName;

    private transient String accountNumber;

    private float lineAmount;

    public BillAccountInformation(Integer id, String description, Integer billId, int accountId, String accountName,
                                  float lineAmount, String accountNumber) {
        this.id = id;
        this.description = description;
        this.billId = billId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.lineAmount = lineAmount;
        this.accountNumber = accountNumber;
    }

    public BillAccountInformation() {
    }
}

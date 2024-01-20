package com.ap_express_server.models.po;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "po_account_information")
public class PoAccountInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer poId;

    private int accountId;

    private String accountName;

    private float lineAmount;

    public PoAccountInformation(Integer id, String description, Integer poId, int accountId, float lineAmount) {
        this.id = id;
        this.description = description;
        this.poId = poId;
        this.accountId = accountId;
        this.lineAmount = lineAmount;
    }

    public PoAccountInformation() {

    }
}

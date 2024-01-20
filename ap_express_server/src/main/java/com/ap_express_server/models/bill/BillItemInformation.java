package com.ap_express_server.models.bill;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bill_item_information")
public class BillItemInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "item_no")
    private int itemNo;

    private int qty;

    private transient String itemName;

    private transient String itemNumber;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "line_total")
    private float lineTotal;

    public BillItemInformation(Integer id, Integer billId, int itemNo, int qty, String itemName, float unitPrice,
                               float lineTotal, String itemNumber) {
        this.id = id;
        this.billId = billId;
        this.itemNo = itemNo;
        this.qty = qty;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.itemNumber = itemNumber;
    }

    public BillItemInformation() {

    }
}

package com.ap_express_server.models.po;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "po_item_information")
@Data
public class PoItemInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "po_id")
    private Integer poId;

    @Column(name = "item_no")
    private int itemNo;

    private int qty;

    private String itemName;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "line_total")
    private float lineTotal;
    public PoItemInformation(Integer id, Integer poId, int itemId, int qty, float unitPrice, float lineTotal) {
        this.id = id;
        this.poId = poId;
        this.itemNo = itemId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    public PoItemInformation() {

    }
}

package com.ap_express_server.models.po;
import javax.persistence.*;

@Entity
@Table(name = "po_item_information")
public class PoItemInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "po_id")
    private Long poId;

    @Column(name = "item_no")
    private int itemNo;

    private int qty;

    private String itemName;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "line_total")
    private float lineTotal;
    public PoItemInformation(Long id, Long poId, int itemId, int qty, float unitPrice, float lineTotal) {
        this.id = id;
        this.poId = poId;
        this.itemNo = itemId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    public PoItemInformation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getLineTotal() {
        return lineTotal;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setLineTotal(float lineTotal) {
        this.lineTotal = lineTotal;
    }
}

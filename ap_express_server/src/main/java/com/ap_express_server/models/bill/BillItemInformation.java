package com.ap_express_server.models.bill;
import javax.persistence.*;

@Entity
@Table(name = "bill_item_information")
public class BillItemInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_id")
    private Long billId;

    @Column(name = "item_no")
    private int itemNo;

    private int qty;

    private transient String itemName;

    private transient String itemNumber;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "line_total")
    private float lineTotal;

    public BillItemInformation(Long id, Long billId, int itemNo, int qty, String itemName, float unitPrice, float lineTotal, String itemNumber) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoId() {
        return billId;
    }

    public void setPoId(Long poId) {
        this.billId = poId;
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

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
}

package com.ap_express_server.models.po;
public class PoItemInformationDto {

    private Long id;

    private Long poId;

    private int itemNo;

    private String itemNumberStr;

    private String itemName;
    private int qty;

    private float unitPrice;

    private float lineTotal;

    public PoItemInformationDto(Long id, Long poId, int itemNo, String itemNumberStr, String itemName, int qty, float unitPrice, float lineTotal) {
        this.id = id;
        this.poId = poId;
        this.itemNo = itemNo;
        this.itemNumberStr = itemNumberStr;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public void setLineTotal(float lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getItemNumberStr() {
        return itemNumberStr;
    }

    public void setItemNumberStr(String itemNumberStr) {
        this.itemNumberStr = itemNumberStr;
    }
}

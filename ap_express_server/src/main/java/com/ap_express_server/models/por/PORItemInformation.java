package com.ap_express_server.models.por;

import javax.persistence.*;

@Entity
@Table(name = "por_item_information")
public class PORItemInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int itemId;

    private String itemName;
    private String description;
    private int receivedQty;

    private int remainingQty;

    private float lineAmount;

    private int porId;

    private int poId;

    public PORItemInformation(Long id, int itemId, String itemName, String description, int receivedQty,
                              int remainingQty, float lineAmount, int porId, int poId) {
        this.id = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.receivedQty = receivedQty;
        this.remainingQty = remainingQty;
        this.lineAmount = lineAmount;
        this.porId = porId;
        this.poId = poId;
    }

    public PORItemInformation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(int receivedQty) {
        this.receivedQty = receivedQty;
    }

    public int getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(int remainingQty) {
        this.remainingQty = remainingQty;
    }

    public float getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(float lineAmount) {
        this.lineAmount = lineAmount;
    }

    public int getPorId() {
        return porId;
    }

    public void setPorId(int porId) {
        this.porId = porId;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }
}

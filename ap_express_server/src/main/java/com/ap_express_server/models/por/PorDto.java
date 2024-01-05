package com.ap_express_server.models.por;

import java.util.Date;

public class PorDto {

    private Long id;

    private int poId;
    private int vendorId;

    private Date receivedDate;

    private Date createdDate;
    private String createdBy;

    private float totalAmount;

    private char status;

    private String vendorName;

    private String poName;

    private String porName;

    public PorDto(Long id, int poId, int vendorId, Date receivedDate, Date createdDate,
                  String createdBy, float totalAmount, char status, String vendorName, String poName, String porName) {
        this.id = id;
        this.poId = poId;
        this.vendorId = vendorId;
        this.receivedDate = receivedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.totalAmount = totalAmount;
        this.status = status;
        this.vendorName = vendorName;
        this.poName = poName;
        this.porName = porName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public String getPorName() {
        return porName;
    }

    public void setPorName(String porName) {
        this.porName = porName;
    }
}

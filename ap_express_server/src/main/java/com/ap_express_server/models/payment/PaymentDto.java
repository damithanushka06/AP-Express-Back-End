package com.ap_express_server.models.payment;

import java.util.Date;

public class PaymentDto {

    private Long id;

    private int vendorId;

    private int billId;

    private Date paymentDate;

    private Date createdDate;

    private String createdBy;

    private char status;

    private String referenceNo;

    private String notes;

    private float billBalance;

    private float amount;

    private String vendorName;

    private String billNumber;

    private String statusName;

    public PaymentDto(Long id, int vendorId, int billId, Date paymentDate, Date createdDate, String createdBy,
                      char status, String referenceNo, String notes, float billBalance,
                      float amount, String vendorName, String billNumber, String statusName) {
        this.id = id;
        this.vendorId = vendorId;
        this.billId = billId;
        this.paymentDate = paymentDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.status = status;
        this.referenceNo = referenceNo;
        this.notes = notes;
        this.billBalance = billBalance;
        this.amount = amount;
        this.vendorName = vendorName;
        this.billNumber = billNumber;
        this.statusName = statusName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getBillBalance() {
        return billBalance;
    }

    public void setBillBalance(float billBalance) {
        this.billBalance = billBalance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        switch (status) {
            case 'D':
                return "Void";
            case 'A':
                return "Active";
            default:
                return "Unknown";
        }
    }
}

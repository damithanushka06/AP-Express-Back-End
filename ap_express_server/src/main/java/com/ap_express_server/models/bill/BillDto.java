package com.ap_express_server.models.bill;
import java.time.LocalDate;
import java.util.Date;

public class BillDto {

    private Long id;

    private String billNo;
    private int vendorId;

    private String vendorName;

    private Date billDate;

    private float billAmount;

    private String createdBy;

    private LocalDate createdDate;

    private char status;

    private String statusName;

    private float paymentAmount;

    private float billBalance;

    public BillDto(Long id, String billNo, int vendorId, String vendorName, Date billDate,
                   float billAmount, String createdBy, LocalDate createdDate, char status, String statusName) {
        this.id = id;
        this.billNo = billNo;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.billDate = billDate;
        this.billAmount = billAmount;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.status = status;
        this.statusName = statusName;
    }

    public BillDto(float paymentAmount, float billBalance) {
        this.paymentAmount = paymentAmount;
        this.billBalance = billBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    // Getter and Setter for status name field
    public String getStatusName() {
        switch (status) {
            case 'P':
                return "Pending";
            case 'A':
                return "Approved";
            case 'R':
                return "Rejected";
            default:
                return "Unknown";
        }
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public float getBillBalance() {
        return billBalance;
    }

    public void setBillBalance(float billBalance) {
        this.billBalance = billBalance;
    }
}

package com.ap_express_server.models.po;
import java.time.LocalDate;
import java.util.Date;

public class PoDto {

    private Long id;

    private String poNo;
    private int vendorId;

    private String vendorName;

    private Date orderDate;

    private Date deliveryDate;

    private float total;

    private String createdBy;

    private Date createdDate;

    private char status;

    private String statusName;

    public PoDto(Long id, String poNo, int vendorId, String vendorName, Date orderDate, Date deliveryDate, float total, String createdBy, Date createdDate, char status, String statusName) {
        this.id = id;
        this.poNo = poNo;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.total = total;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.status = status;
        this.statusName = statusName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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
}

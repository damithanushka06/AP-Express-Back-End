package com.ap_express_server.models.vendor;

import java.time.LocalDate;

public class VendorDto {
    private Long id;
    private String name;
    private String address;
    private String contactNo;
    private String email;

    private int paymentTermId;

    private int paymentMethodId;
    private String paymentTermName;
    private String paymentMethodName;

    private String createdBy;

    private LocalDate createdAt;

    public VendorDto(Long id, String name, String address, String contactNo, String email, int paymentTermId,
                     int paymentMethodId, String paymentTermName, String paymentMethodName,
                     String createdBy, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.paymentTermId = paymentTermId;
        this.paymentMethodId = paymentMethodId;
        this.paymentTermName = paymentTermName;
        this.paymentMethodName = paymentMethodName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(int paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

package com.ap_express_server.models.vendor;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VendorDto {
    private Integer id;
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

    public VendorDto(Integer id, String name, String address, String contactNo, String email, int paymentTermId,
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
}

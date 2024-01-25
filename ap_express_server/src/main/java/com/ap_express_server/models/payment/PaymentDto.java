package com.ap_express_server.models.payment;

import lombok.Data;

import java.util.Date;

@Data
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

    public PaymentDto(long id, int vendorId, int billId, Date paymentDate, Date createdDate, String createdBy,
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

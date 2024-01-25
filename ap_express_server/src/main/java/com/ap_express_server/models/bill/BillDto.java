package com.ap_express_server.models.bill;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BillDto {

    private Integer id;

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

    private int userId;

    public BillDto(Integer id, String billNo, int vendorId, String vendorName, Date billDate,
                   float billAmount, String createdBy, LocalDate createdDate, int userId, char status, String statusName) {
        this.id = id;
        this.billNo = billNo;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.billDate = billDate;
        this.billAmount = billAmount;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.status = status;
        this.userId = userId;
        this.statusName = statusName;

    }

    public BillDto(Integer id, String billNo, int vendorId, String vendorName, Date billDate, float billAmount, String
            createdBy, LocalDate createdDate, char status, String statusName, float paymentAmount,
                   float billBalance, Integer userId) {
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
        this.paymentAmount = paymentAmount;
        this.billBalance = billBalance;
        this.userId = userId;
    }

    public BillDto(float paymentAmount, float billBalance) {
        this.paymentAmount = paymentAmount;
        this.billBalance = billBalance;
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

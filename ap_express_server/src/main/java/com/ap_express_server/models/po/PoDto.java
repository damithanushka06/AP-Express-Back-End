package com.ap_express_server.models.po;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PoDto {

    private Integer id;

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

    public PoDto(Integer id, String poNo, int vendorId, String vendorName, Date orderDate, Date deliveryDate,
                 float total, String createdBy, Date createdDate, char status, String statusName) {
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

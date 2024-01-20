package com.ap_express_server.models.por;

import lombok.Data;

import java.util.Date;

@Data
public class PorDto {

    private Integer id;

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

    public PorDto(Integer id, int poId, int vendorId, Date receivedDate, Date createdDate,
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

}

package com.ap_express_server.models.po;

import lombok.Data;

@Data
public class PoAccountInformationDto {

    private Integer id;

    private String description;

    private Integer poId;

    private int accountId;

    private String accountName;

    private float lineAmount;

    public PoAccountInformationDto(Integer id, String description, Integer poId, int accountId, String accountName, float lineAmount) {
        this.id = id;
        this.description = description;
        this.poId = poId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.lineAmount = lineAmount;
    }
}

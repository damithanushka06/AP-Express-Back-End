package com.ap_express_server.models.po;

import lombok.Data;

@Data
public class PoItemInformationDto {

    private Integer id;

    private Integer poId;

    private int itemNo;

    private String itemNumberStr;

    private String itemName;
    private int qty;

    private float unitPrice;

    private float lineTotal;

    public PoItemInformationDto(Integer id, Integer poId, int itemNo, String itemNumberStr,
                                String itemName, int qty, float unitPrice, float lineTotal) {
        this.id = id;
        this.poId = poId;
        this.itemNo = itemNo;
        this.itemNumberStr = itemNumberStr;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }
}

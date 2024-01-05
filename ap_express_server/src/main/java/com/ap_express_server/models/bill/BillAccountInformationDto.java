package com.ap_express_server.models.bill;

public class BillAccountInformationDto {

    private Long id;

    private String description;

    private Long poId;

    private int accountId;

    private String accountName;

    private float lineAmount;

    public BillAccountInformationDto(Long id, String description, Long poId, int accountId, String accountName, float lineAmount) {
        this.id = id;
        this.description = description;
        this.poId = poId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.lineAmount = lineAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public float getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(float lineAmount) {
        this.lineAmount = lineAmount;
    }
}

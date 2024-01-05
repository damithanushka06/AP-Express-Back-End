package com.ap_express_server.models.po;
import javax.persistence.*;

@Entity
@Table(name = "po_account_information")
public class PoAccountInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Long poId;

    private int accountId;

    private String accountName;

    private float lineAmount;

    public PoAccountInformation(Long id, String description, Long poId, int accountId, float lineAmount) {
        this.id = id;
        this.description = description;
        this.poId = poId;
        this.accountId = accountId;
        this.lineAmount = lineAmount;
    }

    public PoAccountInformation() {

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

    public float getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(float lineAmount) {
        this.lineAmount = lineAmount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}

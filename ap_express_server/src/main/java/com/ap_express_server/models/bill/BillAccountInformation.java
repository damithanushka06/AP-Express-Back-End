package com.ap_express_server.models.bill;
import javax.persistence.*;

@Entity
@Table(name = "bill_account_information")
public class BillAccountInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Long billId;

    private int accountId;

    private transient String accountName;

    private transient String accountNumber;

    private float lineAmount;

    public BillAccountInformation(Long id, String description, Long billId, int accountId, String accountName, float lineAmount, String accountNumber) {
        this.id = id;
        this.description = description;
        this.billId = billId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.lineAmount = lineAmount;
        this.accountNumber = accountNumber;
    }

    public BillAccountInformation() {

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
        return billId;
    }

    public void setPoId(Long poId) {
        this.billId = poId;
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

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

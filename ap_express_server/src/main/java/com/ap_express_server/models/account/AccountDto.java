package com.ap_express_server.models.account;

import java.time.LocalDate;

public class AccountDto {
    private Long id;

    private String name;

    private String description;

    private int accountTypeId;

    private int parentAccountId;

    private String accountTypeName;

    private String parentAccountName;

    private String createdBy;

    private LocalDate createdDate;


    public AccountDto(Long id, String name, String description, int accountTypeId, int parentAccountId,
                      String accountTypeName, String parentAccountName, String createdBy, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.accountTypeId = accountTypeId;
        this.parentAccountId = parentAccountId;
        this.accountTypeName = accountTypeName;
        this.parentAccountName = parentAccountName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public int getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(int parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getParentAccountName() {
        return parentAccountName;
    }

    public void setParentAccountName(String parentAccountName) {
        this.parentAccountName = parentAccountName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


}

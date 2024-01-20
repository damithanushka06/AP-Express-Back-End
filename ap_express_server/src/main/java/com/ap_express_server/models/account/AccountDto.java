package com.ap_express_server.models.account;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDto {
    private Integer id;

    private String name;

    private String description;

    private int accountTypeId;

    private int parentAccountId;

    private String accountTypeName;

    private String parentAccountName;

    private String createdBy;

    private LocalDate createdDate;


    public AccountDto(Integer id, String name, String description, int accountTypeId, int parentAccountId,
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


}

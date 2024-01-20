package com.ap_express_server.models.account;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "account_master")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String number;

    private String description;

    private int accountTypeId;

    private int parentAccountId;

    private String createdBy;

    private LocalDate createdDate;
}

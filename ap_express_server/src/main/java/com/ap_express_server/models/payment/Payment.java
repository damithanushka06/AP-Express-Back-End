package com.ap_express_server.models.payment;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "payment")

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int vendorId;

    private int billId;

    private Date paymentDate;

    private Date createdDate;

    private String createdBy;

    private char status;

    private String referenceNo;

    private String notes;

    private float billBalance;

    private float amount;
}

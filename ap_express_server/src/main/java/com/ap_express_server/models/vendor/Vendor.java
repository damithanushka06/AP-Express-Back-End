package com.ap_express_server.models.vendor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vendor",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id"),
        })
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private String notes;

    private int paymentTermId;

    private int paymentMethodId;

    private String createdBy;

    @Column(name = "created_at")
    private LocalDate createdAt;

    private String contactNo;

    private String email;
}

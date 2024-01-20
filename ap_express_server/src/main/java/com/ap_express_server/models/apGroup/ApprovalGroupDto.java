package com.ap_express_server.models.apGroup;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "approval_group_mst")
public class ApprovalGroupDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String createdBy;

    private LocalDate createdDate;

    public ApprovalGroupDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ApprovalGroupDto() {

    }
}

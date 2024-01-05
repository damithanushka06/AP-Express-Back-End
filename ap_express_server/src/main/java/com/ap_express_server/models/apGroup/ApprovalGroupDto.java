package com.ap_express_server.models.apGroup;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "approval_group_mst")
public class ApprovalGroupDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName() {
        return name;
    }

    public ApprovalGroupDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

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

    private String createdBy;

    private LocalDate createdDate;

    public ApprovalGroupDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ApprovalGroupDto(String name) {
        this.name = name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}

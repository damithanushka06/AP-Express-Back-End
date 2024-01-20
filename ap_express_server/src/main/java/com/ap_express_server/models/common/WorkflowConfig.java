package com.ap_express_server.models.common;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "common_workflow_config")
@Data
public class WorkflowConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int documentTypeId;

    private Integer documentId;

    private int groupId;

    private int userId;

    private String createdBy;

    private Date createdDate;
}

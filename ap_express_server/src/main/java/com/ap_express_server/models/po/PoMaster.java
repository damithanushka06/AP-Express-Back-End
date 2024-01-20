package com.ap_express_server.models.po;
import com.ap_express_server.models.common.WorkflowConfig;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "po_master")
@Data
public class PoMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String poNo;
    private Integer vendorId;

    private Date orderDate;

    private Date deliveryDate;

    private String notes;

    private String createdBy;

    private Date createdDate;

    private int currentLevel;

    private int totalLevels;

    private float taxAmount;

    private float total;

    private float itemTotal;

    private float accountTotal;

    private char status;

    @Transient
    private transient String statusName;

    @Transient
    private transient String vendorName;

    @Transient
    private transient String deliveryDateStr;

    @Transient
    private transient String orderDateStr;
    @Transient
    private transient List<WorkflowConfig> workflowDetails = new ArrayList<>();

    @Transient
    private transient List<PoItemInformation> poItemInformation = new ArrayList<>();

    @Transient
    private transient List<PoAccountInformation> poAccountInformation = new ArrayList<>();

    @Transient
    private transient List<MultipartFile> files = new ArrayList<>();

    @Transient
    private transient List<PoAdditionalAttachment> attachments = new ArrayList<>();
}

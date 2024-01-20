package com.ap_express_server.models.bill;
import com.ap_express_server.models.common.WorkflowConfig;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "bill_mst")
public class BillMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String billNo;
    private int vendorId;

    private Date billDate;

    private String notes;

    private String createdBy;

    private LocalDate createdDate;

    private int currentLevel;

    private int totalLevels;

    private float taxAmount;

    private float billAmount;

    private float itemTotal;

    private float accountTotal;

    private char status;

    private float paymentAmount;

    private float billBalance;

    private int poId;

    @Column(name = "term_id", nullable = true)
    private int termId;


    @Transient
    private transient String statusName;

    @Transient
    private transient List<WorkflowConfig> workflowDetails = new ArrayList<>();

    @Transient
    private transient List<BillItemInformation> billItemInformation = new ArrayList<>();

    @Transient
    private transient List<BillAccountInformation> billAccountInformation = new ArrayList<>();

    @Transient
    private transient List<MultipartFile> files = new ArrayList<>();

    @Transient
    private transient List<BillAdditionalAttachment> attachments = new ArrayList<>();

    public BillMaster(Integer id, String billNo, Integer vendorId, Date billDate, String notes,
                      String createdBy, LocalDate createdDate, int currentLevel, int totalLevels,
                      float taxAmount, float billAmount, float itemTotal, float accountTotal,
                      char status, String statusName, List<WorkflowConfig> workflowDetails,
                      List<BillItemInformation> billItemInformation,
                      List<BillAccountInformation> billAccountInformation, List<MultipartFile> files,
                      List<BillAdditionalAttachment> attachments, int termId, int poId) {
        this.id = id;
        this.billNo = billNo;
        this.vendorId = vendorId;
        this.billDate = billDate;
        this.notes = notes;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.currentLevel = currentLevel;
        this.totalLevels = totalLevels;
        this.taxAmount = taxAmount;
        this.billAmount = billAmount;
        this.itemTotal = itemTotal;
        this.accountTotal = accountTotal;
        this.status = status;
        this.statusName = statusName;
        this.workflowDetails = workflowDetails;
        this.billItemInformation = billItemInformation;
        this.billAccountInformation = billAccountInformation;
        this.files = files;
        this.attachments = attachments;
        this.poId = poId;
        this.termId = termId;
    }

    public BillMaster() {

    }
}

package com.ap_express_server.models.bill;
import com.ap_express_server.models.common.WorkflowConfig;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill_mst")
public class BillMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public BillMaster(Long id, String billNo, int vendorId, Date billDate, String notes, String createdBy, LocalDate createdDate, int currentLevel, int totalLevels, float taxAmount, float billAmount, float itemTotal, float accountTotal, char status, String statusName, List<WorkflowConfig> workflowDetails, List<BillItemInformation> billItemInformation,
                      List<BillAccountInformation> billAccountInformation, List<MultipartFile> files, List<BillAdditionalAttachment> attachments, int termId, int poId) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getTotalLevels() {
        return totalLevels;
    }

    public void setTotalLevels(int totalLevels) {
        this.totalLevels = totalLevels;
    }

    public float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }

    public float getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(float itemTotal) {
        this.itemTotal = itemTotal;
    }

    public float getAccountTotal() {
        return accountTotal;
    }

    public void setAccountTotal(float accountTotal) {
        this.accountTotal = accountTotal;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<WorkflowConfig> getWorkflowDetails() {
        return workflowDetails;
    }

    public void setWorkflowDetails(List<WorkflowConfig> workflowDetails) {
        this.workflowDetails = workflowDetails;
    }

    public List<BillItemInformation> getBillItemInformation() {
        return billItemInformation;
    }

    public void setBillItemInformation(List<BillItemInformation> billItemInformation) {
        this.billItemInformation = billItemInformation;
    }

    public List<BillAccountInformation> getBillAccountInformation() {
        return billAccountInformation;
    }

    public void setBillAccountInformation(List<BillAccountInformation> billAccountInformation) {
        this.billAccountInformation = billAccountInformation;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<BillAdditionalAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<BillAdditionalAttachment> attachments) {
        this.attachments = attachments;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public float getBillBalance() {
        return billBalance;
    }

    public void setBillBalance(float billBalance) {
        this.billBalance = billBalance;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }
}

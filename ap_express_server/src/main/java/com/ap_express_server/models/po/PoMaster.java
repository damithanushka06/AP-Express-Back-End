package com.ap_express_server.models.po;
import com.ap_express_server.models.common.WorkflowConfig;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "po_master")
public class PoMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poNo;
    private int vendorId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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

    public List<WorkflowConfig> getWorkflowDetails() {
        return workflowDetails;
    }

    public void setWorkflowDetails(List<WorkflowConfig> workflowDetails) {
        this.workflowDetails = workflowDetails;
    }

    public List<PoItemInformation> getPoItemInformation() {
        return poItemInformation;
    }

    public void setPoItemInformation(List<PoItemInformation> poItemInformation) {
        this.poItemInformation = poItemInformation;
    }

    public List<PoAccountInformation> getPoAccountInformation() {
        return poAccountInformation;
    }

    public void setPoAccountInformation(List<PoAccountInformation> poAccountInformation) {
        this.poAccountInformation = poAccountInformation;
    }


    public float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<PoAdditionalAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<PoAdditionalAttachment> attachments) {
        this.attachments = attachments;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDeliveryDateStr() {
        return deliveryDateStr;
    }

    public void setDeliveryDateStr(String deliveryDateStr) {
        this.deliveryDateStr = deliveryDateStr;
    }

    public String getOrderDateStr() {
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {
        this.orderDateStr = orderDateStr;
    }
}

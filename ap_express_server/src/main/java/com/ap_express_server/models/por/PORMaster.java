package com.ap_express_server.models.por;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "po_receipt")
public class PORMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int poId;
    private int vendorId;
    private Date receivedDate;
    private Date createdDate;
    private String createdBy;
    private float totalAmount;
    private char status;
    private  String porNumber;
    @Transient
    private transient List<PORItemInformation> porItemInformationList = new ArrayList<>();
    @Transient
    private transient List<MultipartFile> files = new ArrayList<>();

    public PORMaster(Long id, int poId, int vendorId, Date receivedDate, Date createdDate, String createdBy,
                     float totalAmount, char status, String porNumber, List<PORItemInformation> porItemInformationList,
                     List<MultipartFile> files) {
        this.id = id;
        this.poId = poId;
        this.vendorId = vendorId;
        this.receivedDate = receivedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.totalAmount = totalAmount;
        this.status = status;
        this.porNumber = porNumber;
        this.porItemInformationList = porItemInformationList;
        this.files = files;
    }

    public PORMaster() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public List<PORItemInformation> getPorItemInformationList() {
        return porItemInformationList;
    }

    public void setPorItemInformationList(List<PORItemInformation> porItemInformationList) {
        this.porItemInformationList = porItemInformationList;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getPorNumber() {
        return porNumber;
    }

    public void setPorNumber(String porNumber) {
        this.porNumber = porNumber;
    }
}

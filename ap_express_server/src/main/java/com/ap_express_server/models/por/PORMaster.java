package com.ap_express_server.models.por;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "po_receipt")
public class PORMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public PORMaster(Integer id, int poId, int vendorId, Date receivedDate, Date createdDate, String createdBy,
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
}

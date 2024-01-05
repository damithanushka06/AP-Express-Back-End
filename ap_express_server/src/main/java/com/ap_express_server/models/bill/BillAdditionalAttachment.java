package com.ap_express_server.models.bill;
import javax.persistence.*;

@Entity
@Table(name = "bill_attachment")
public class BillAdditionalAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long billId;

    private String filePath;

    private char status;

    public BillAdditionalAttachment(Long id, int billId, String filePath) {
        this.id = id;
        this.billId = billId;
        this.filePath = filePath;
    }

    public BillAdditionalAttachment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPoId() {
        return billId;
    }

    public void setPoId(long poId) {
        this.billId = poId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}

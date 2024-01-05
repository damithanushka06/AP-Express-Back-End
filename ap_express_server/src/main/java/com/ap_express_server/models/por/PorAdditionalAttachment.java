package com.ap_express_server.models.por;
import javax.persistence.*;

@Entity
@Table(name = "por_attachment")
public class PorAdditionalAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long poId;

    private String filePath;

    private char status;

    public PorAdditionalAttachment(Long id, int poId, String filePath) {
        this.id = id;
        this.poId = poId;
        this.filePath = filePath;
    }

    public PorAdditionalAttachment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPoId() {
        return poId;
    }

    public void setPoId(long poId) {
        this.poId = poId;
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

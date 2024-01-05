package com.ap_express_server.repository.por;
import com.ap_express_server.models.po.PoAdditionalAttachment;
import com.ap_express_server.models.por.PORMaster;
import com.ap_express_server.models.por.PorAdditionalAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PorAttachmentRepository extends JpaRepository<PorAdditionalAttachment, Long> {

    /**
     * Retrieves a list of PorAdditionalAttachment objects for the specified porId.
     *
     * @param porId The ID of the POR (Purchase Order Receipt).
     * @return A list of PoAdditionalAttachment objects containing the desired attachments.
     */
    List<PoAdditionalAttachment> findByPoId(Long porId);

}

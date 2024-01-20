package com.ap_express_server.repository.po;
import com.ap_express_server.models.po.PoAdditionalAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoAttachmentRepository extends JpaRepository<PoAdditionalAttachment, Long> {

    /**
     * Retrieves a list of PoAdditionalAttachment objects for the specified poId.
     *
     * @param poId The ID of the PO (Purchase Order).
     * @return A list of PoAdditionalAttachment objects containing the desired attachments.
     */
    List<PoAdditionalAttachment> findByPoId(Integer poId);
}

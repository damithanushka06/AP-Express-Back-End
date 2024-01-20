package com.ap_express_server.repository.bill;
import com.ap_express_server.models.bill.BillAdditionalAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillAttachmentRepository extends JpaRepository<BillAdditionalAttachment, Long> {

    /**
     * Retrieves a list of BillAdditionalAttachment objects based on the provided billId.
     * @param billId The ID of the bill.
     * @return A list of BillAdditionalAttachment objects associated with the bill.
     */
    List<BillAdditionalAttachment> findByBillId(Integer billId);

}

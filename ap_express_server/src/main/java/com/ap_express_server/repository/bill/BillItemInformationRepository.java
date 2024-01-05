package com.ap_express_server.repository.bill;
import com.ap_express_server.models.bill.BillItemInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BillItemInformationRepository extends JpaRepository<BillItemInformation, Long> {

    /**
     * Retrieves a list of BillItemInformationDto objects based on the provided billId.
     *
     * @param billId The ID of the bill.
     * @return A list of BillItemInformationDto objects representing the item information for the bill.
     */
    @Query("SELECT NEW com.ap_express_server.models.bill.BillItemInformation(billItem.id, billItem.billId, " +
            "billItem.itemNo, billItem.qty, item.name, billItem.unitPrice, billItem.lineTotal, item.number) " +
            "FROM BillItemInformation billItem JOIN Item item ON billItem.itemNo = item.id WHERE billItem.billId = :billId")
    List<BillItemInformation> findByBillId(@Param("billId") Long billId);

}

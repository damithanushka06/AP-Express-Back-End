package com.ap_express_server.repository.bill;
import com.ap_express_server.models.bill.BillAccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BillAccountInformationRepository extends JpaRepository<BillAccountInformation, Long> {

    /**
     * Retrieves a list of BillAccountInformationDto objects based on the provided billId.
     *
     * @param billId The ID of the bill.
     * @return A list of BillAccountInformationDto objects representing the account information for the bill.
     */
    @Query("SELECT NEW com.ap_express_server.models.bill.BillAccountInformation(accBlll.id, accBlll.description, " +
            "accBlll.billId, accBlll.accountId, acc.name, accBlll.lineAmount, acc.number) " +
            "FROM BillAccountInformation accBlll JOIN Account acc ON accBlll.accountId = acc.id WHERE accBlll.billId = :billId")
    List<BillAccountInformation> findByBillId(@Param("billId") Integer billId);

}

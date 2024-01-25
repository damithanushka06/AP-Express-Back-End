package com.ap_express_server.repository.bill;
import com.ap_express_server.models.bill.BillDto;
import com.ap_express_server.models.bill.BillMaster;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillMaster, Integer> {

    /**
     * Retrieves a specific BillMaster object based on the provided billId.
     *
     * @param billId The ID of the bill.
     * @return An Optional containing the BillMaster object if found, or an empty Optional if not found.
     */
    @Query("SELECT bill FROM BillMaster bill WHERE bill.id = :billId")
    Optional<BillMaster> getBillDetailById(@Param("billId") Integer billId);


    /**
     * Retrieves a list of BillDto objects representing all bills, excluding those with a status of 'D' (Deleted).
     *
     * @return A list of BillDto objects representing the bills.
     */
    @Query("SELECT NEW com.ap_express_server.models.bill.BillDto(billMst.id, billMst.billNo, billMst.vendorId, " +
            "ven.name, DATE(billMst.billDate), billMst.billAmount, billMst.createdBy, billMst.createdDate,billMst.userId, billMst.status, " +
            "CASE billMst.status " +
            "    WHEN 'P' THEN 'Pending' " +
            "    WHEN 'A' THEN 'Approved' " +
            "    WHEN 'R' THEN 'Rejected' " +
            "    ELSE 'Unknown' " +
            "END) " +
            "FROM BillMaster billMst JOIN Vendor ven ON ven.id = billMst.vendorId WHERE billMst.status != 'D'")
    List<BillDto> getAllBills();


    /**
     * Retrieves the payment amount and bill balance from the BillMaster entity and maps them to a BillDto object.
     *
     * @param billId The ID of the bill.
     * @return An Optional containing the BillDto object with the payment amount and bill balance, or empty if not found.
     */
    @Query("select new com.ap_express_server.models.bill.BillDto(bill.paymentAmount, bill.billBalance) " +
            "from BillMaster bill where bill.id = :billId")
    Optional<BillDto> getBillBalance(@Param("billId") Integer billId);

    /**
     * Retrieves a specific BillMaster object based on the provided vendor id.
     *
     * @param vendorId to given vendor id
     * @return list of bill objects in a dropdown format
     */
    @Query("select new com.ap_express_server.models.dropdown.DropDownDto(bill.id, bill.billNo)" +
            "from BillMaster bill where bill.status = 'A'")
    List<DropDownDto> getVendorRelatedActiveBillList(@Param("vendorId") Integer vendorId);

    /**
     * Retrieves set of ApprovedBillChartData objects data.
     * @return list of data in a ApprovedBillChartData format
     */
    @Query("SELECT MONTH(bill.billDate) AS month, COUNT(bill) AS noOfBills FROM BillMaster bill where" +
            " bill.status = 'A' GROUP BY MONTH(bill.billDate)")
    List<Object[]> getApprovedBillsRawData();


}

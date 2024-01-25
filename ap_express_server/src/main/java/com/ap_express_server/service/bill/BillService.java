package com.ap_express_server.service.bill;
import com.ap_express_server.common_utitlity.CustomException;
import com.ap_express_server.models.bill.BillDto;
import com.ap_express_server.models.bill.BillMaster;
import com.ap_express_server.models.chart.ChartData;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface BillService {

    /**
     * Retrieves to create a new BillMaster.
     * @param billMaster The BillMaster object containing the information for the new BillMaster.
     * @return The created BillMaster object.
     */
    ResponseEntity<Object> createBillMaster(BillMaster billMaster) throws CustomException, IOException;

    /**
     * Retrieves the BillMaster detail by its ID.
     * @param billId The ID of the BillMaster to retrieve.
     * @return An optional containing the BillMaster detail if found, or an empty optional if not found.
     */
    Optional<BillMaster> getBillMasterDetailById(Integer billId);

    /**
     * Retrieves all BillMasters.
     * @return A list of all BillMasters.
     */
    List<BillDto> getAllBillMasters();

    /**
     * Deletes the BillMaster with the given ID.
     * @param billId The ID of the BillMaster to delete.
     */
    void deleteBillMasterById(Integer billId);

    /**
     * Retrieves update a BillMaster.
     * @param billMaster The BillMaster object containing the information for the updated BillMaster.
     * @return The updated BillMaster.
     */
    ResponseEntity<?> updateBillMaster(BillMaster billMaster);

    /**
     * Retrieves given active bill list related to vendor id
     * @param vendorId to vendor id
     * @return to bill active dropdown list
     */
    List<DropDownDto> getVendorRelatedActiveBillList(Integer vendorId);

    /**
     * Retrieves a list of ApprovedBillChartData objects.
     *
     * @return List of ApprovedBillChartData.
     */
    List<ChartData> getApprovedBills();

    /**
     * Approve Bill/Approve and finalize
     * @param billId to bill master id
     * @return ResponseEntity<Object>
     */
    ResponseEntity<Object> approveBill(Integer billId);
}

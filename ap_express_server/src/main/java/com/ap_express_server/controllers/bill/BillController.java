package com.ap_express_server.controllers.bill;
import com.ap_express_server.constant.Notification.HttpUrlConstants;
import com.ap_express_server.models.bill.BillDto;
import com.ap_express_server.models.bill.BillMaster;
import com.ap_express_server.models.chart.ChartData;
import com.ap_express_server.service.bill.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = HttpUrlConstants.FRONTEND_BASE_URL, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class BillController {

    private final BillService billMasterService;

    public BillController(BillService billService) {
        this.billMasterService = billService;
    }


    /**
     * Endpoint to create a new BillMaster.
     * @param billMaster The BillMaster object containing the information for the new BillMaster.
     * @return The created BillMaster object.
     */
    @PostMapping("/bill/create_bill")
    public ResponseEntity<Object> createBillMaster(@ModelAttribute BillMaster billMaster) throws IOException {
        // Delegate the creation of the BillMaster to the service layer
        return billMasterService.createBillMaster(billMaster);
    }

    /**
     * Retrieves the BillMaster detail by its ID.
     * @param billId The ID of the BillMaster to retrieve.
     * @return An optional containing the BillMaster detail if found, or an empty optional if not found.
     */
    @GetMapping("/bill/get_bill_master_detail_by_id")
    public Optional<BillMaster> getBillMasterDetailById(@RequestParam Integer billId) {
        return billMasterService.getBillMasterDetailById(billId);
    }

    /**
     * Retrieves all BillMasters.
     * @return A list of all BillMasters.
     */
    @GetMapping("/bill/getAll")
    public List<BillDto> getAllBillMasters() {
        return billMasterService.getAllBillMasters();
    }

    /**
     * Deletes the BillMaster with the given ID.
     * @param billId The ID of the BillMaster to delete.
     * @return A response entity indicating the success of the deletion.
     */
    @DeleteMapping("/bill/delete_bill_master_by_id")
    public ResponseEntity<?> deleteBillMasterById(@RequestParam Integer billId) {
        billMasterService.deleteBillMasterById(billId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to update a BillMaster.
     * @param billMaster The BillMaster object containing the information for the updated BillMaster.
     * @return The updated BillMaster.
     */
    @PostMapping("/bill/update_bill_master")
    public ResponseEntity<?> updateBillMaster(@ModelAttribute BillMaster billMaster) throws IOException {
        // Delegate the update of the BillMaster to the service layer
        return billMasterService.updateBillMaster(billMaster);
    }

    /**
     * Endpoint to retrieve a list of ApprovedBillChartData.
     *
     * @return List of ApprovedBillChartData objects.
     * @throws IOException if an I/O error occurs.
     */
    @GetMapping("/bill/get_chart_data_related_approved_bill")
    public List<ChartData> getChartDataForApprovedBills() throws IOException {
        return billMasterService.getApprovedBills();
    }


}

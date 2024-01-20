package com.ap_express_server.controllers.po;
import com.ap_express_server.models.chart.ChartData;
import com.ap_express_server.models.po.PoDto;
import com.ap_express_server.models.po.PoMaster;
import com.ap_express_server.service.po.PoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class PoController {

    private final PoService poService;

    public PoController(PoService poService) {
        this.poService = poService;
    }

    /**
     * Endpoint to create a new Purchase Order (PO).
     * @param poMaster The PO Master object containing the information for the new PO.
     * @return The created PO Master object.*/
    @PostMapping("po/create_po")
    public ResponseEntity<Object> createPo(@ModelAttribute PoMaster poMaster) throws IOException {
        // Delegate the creation of the PO to the service layer
        return poService.createPo(poMaster);
    }

    /**
     * Retrieves the Purchase Order detail by its ID.
     * @param poId The ID of the Purchase Order to retrieve.
     * @return An optional containing the Purchase Order detail if found, or an empty optional if not found.
     */
    @GetMapping("po/get_po_detail_by_id")
    public Optional<PoMaster> getPoDetailById(@RequestParam Integer poId) {
        return poService.getPoDetailById(poId);
    }

    /**
     * this method can be used to get all pos
     * @return to the service
     */
    @GetMapping("po/getAll")
    public List<PoDto> getAllPos() {
        return poService.getAllPOs();
    }

    /**
     * this method can be used to delete a PO
     *
     * @param poId to po master id
     */
    @DeleteMapping("po/delete_po_detail_by_id")
    public ResponseEntity<?> deletePOById(@RequestParam Integer poId) {
        poService.deletePODetailById(poId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to update a Purchase Order (PO).
     * @param poMaster The PO Master object containing the information for the new PO.
     * @return The updated PO Master object.*/
    @PostMapping("po/update_po")
    public ResponseEntity<?> updatePo(@ModelAttribute PoMaster poMaster) throws IOException {
        // Delegate the creation of the PO to the service layer
        return poService.updatePo(poMaster);
    }

    /**
     * Endpoint to retrieve a list of chart data.
     * @return List of ChartData objects.
     * @throws IOException if an I/O error occurs.
     */
    @GetMapping("/po/get_chart_data_related_approved_po")
    public List<ChartData> getApprovedPOs() throws IOException {
        return poService.getApprovedPOs();
    }

    /**
     * Endpoint to download the Purchase Order (PO) report as a byte array based on the given PO ID.
     * @param poId The ID of the PO to download the report for.
     * @return The downloaded PO report as a byte array.
     */
    @GetMapping("po/download_po_report")
    public byte[] downloadPoReport(@RequestParam Integer poId) {
        return poService.downloadPoReport(poId);
    }


}

package com.ap_express_server.service.po;
import com.ap_express_server.models.chart.ChartData;
import com.ap_express_server.models.po.PoDto;
import com.ap_express_server.models.po.PoMaster;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
public interface PoService {

    /**
     * Creates a new Purchase Order (PO) based on the provided PO Master object.
     * @param poMaster The PO Master object containing the information for the new PO.
     * @return The created PO Master object.
     */
    ResponseEntity<Object> createPo(PoMaster poMaster) throws IOException;

    /**
     * Retrieves the Purchase Order detail by its ID.
     * @param poId The ID of the Purchase Order to retrieve.
     * @return An optional containing the Purchase Order detail if found, or an empty optional if not found.
     */
    Optional<PoMaster> getPoDetailById(Long poId);

    /**
     * Retrieves all pos
     * @return po dto list
     */
    List<PoDto> getAllPOs();

    /**
     * delete po by id
     * @param poId to po master id
     */
    void deletePODetailById(Long poId);

    /**
     * Update a new Purchase Order (PO) based on the provided PO Master object.
     * @param poMaster The PO Master object update the information for the exist PO.
     * @return The updated PO Master object.
     */
    ResponseEntity<Object> updatePo(PoMaster poMaster) throws IOException;

    /**
     * Retrieves a list of ChartData objects.
     *
     * @return List of ChartData.
     */
    List<ChartData> getApprovedPOs();

    /**
     * Generates and downloads the Purchase Order (PO) report as a byte array based on the given PO ID.
     *
     * @param poId The ID of the PO to generate the report for.
     * @return The downloaded PO report as a byte array.
     */
    byte[] downloadPoReport(Long poId);
}

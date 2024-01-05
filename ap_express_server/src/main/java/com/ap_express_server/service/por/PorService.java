package com.ap_express_server.service.por;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.po.PoItemInformationDto;
import com.ap_express_server.models.por.PORItemInformation;
import com.ap_express_server.models.por.PORMaster;
import com.ap_express_server.models.por.PorDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PorService {

    /**
     * Creates a new Purchase Order Receipt(POR) based on the provided PO Master object.
     *
     * @param porMaster The POR Master object containing the information for the new POR.
     * @return The created POR Master object.
     */
    PORMaster createPor(PORMaster porMaster) throws IOException;

    /**
     * Retrieves all Purchase Order Records (PORs) from the database.
     * Returns a list of PORMaster objects representing the PORs.
     * @return List of PORMaster objects
     */
    List<PorDto> getAllPORs();

    /**
     * Deletes the Purchase Order Record (POR) detail with the specified ID from the database.
     * @param porId The ID of the POR detail to be deleted
     */

    void deletePORDetailById(Long porId);

    /**
     * Retrieves a list of vendor-related Purchase Order (PO) dropdown options.
     * Only active POs associated with the specified vendor ID are included.
     * @param vendorId The ID of the vendor
     * @return A list of DropDownDto objects representing vendor-related POs
     */
    List<DropDownDto> getVendorRelatedPoList(int vendorId);

    /**
     * Retrieves the list of PORItemInformation related to a specific PO ID.
     *
     * @param poId The ID of the PO.
     * @return The list of PORItemInformation related to the given PO ID.
     */
    List<PoItemInformationDto> getPORelatedItemList(Long poId);

}

package com.ap_express_server.controllers.por;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.po.PoItemInformationDto;
import com.ap_express_server.models.por.PORItemInformation;
import com.ap_express_server.models.por.PORMaster;
import com.ap_express_server.models.por.PorDto;
import com.ap_express_server.service.por.PorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class PorController {

    private final PorService porService;

    public PorController(PorService porService) {
        this.porService = porService;
    }

    /**
     * Endpoint to create a new Purchase Order Receipt (POR).
     *
     * @param porMaster The POR Master object containing the information for the new POR.
     * @return The created POR Master object.
     */
    @PostMapping("por/create_por")
    public PORMaster createPor(@ModelAttribute PORMaster porMaster) throws IOException {
        // Delegate the creation of the POR to the service layer
        return porService.createPor(porMaster);
    }

    /**
     * this method can be used to get all pors
     * @return to the service
     */
    @GetMapping("por/getAll")
    public List<PorDto> getAllPos() {
        return porService.getAllPORs();
    }

    /**
     * this method can be used to delete a POR
     *
     * @param porId to por master id
     */
    @DeleteMapping("por/delete_por_detail_by_id")
    public ResponseEntity<?> deleteUserById(@RequestParam Long porId) {
        porService.deletePORDetailById(porId);
        return ResponseEntity.ok().build();
    }

    /**
     * this method can be used to get selected vendor related approved PO
     * @return to the service
     */
    @GetMapping("por/get_vendor_related_po_list")
    public List<DropDownDto> getVendorRelatedPoList(@RequestParam int vendorId) {
        return porService.getVendorRelatedPoList(vendorId);
    }
    /**
     * Retrieves the list of PORItemInformation related to a specific PO ID.
     *
     * @param poId The ID of the PO.
     * @return The list of PORItemInformation related to the given PO ID.
     */
    @GetMapping("por/get_po_related_item_list")
    public List<PoItemInformationDto> getPORelatedItemList(@RequestParam Long poId) {
        return porService.getPORelatedItemList(poId);
    }

}

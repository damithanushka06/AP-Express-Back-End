package com.ap_express_server.controllers.vendor;
import com.ap_express_server.models.vendor.Vendor;
import com.ap_express_server.models.vendor.VendorDto;
import com.ap_express_server.service.vendor.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class VendorController {

  private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    /**
     * this method can be used to create vendor
     * @param vendor to Vendor
     * @return to the service
     */
    @PostMapping("vendor/create_vendor")
    private Vendor createVendor(@RequestBody Vendor vendor){
        return vendorService.createVendor(vendor);
    }


    /**
     * this method can be used to update vendor
     * @param vendor to Vendor Object
     * @return to the service
     */
    @PutMapping("vendor/update_vendor")
    private Vendor updateVendor(@RequestBody Vendor vendor) {
        return vendorService.updateVendor(vendor);
    }

    /**
     * this method can be used to get all vendors
     * @return to the service
     */
    @GetMapping("vendor/getAll")
    private List<VendorDto> getAllVendorList(){
        return vendorService.getAllVendorList();
    }


    /**
     * this method can be used to get all vendors
     * @return to the service
     */
    @GetMapping("vendor/get_vendor_detail_by_id")
    private Optional<Vendor> getVendorById(@RequestParam Integer vendorId){
        return vendorService.getVendorById(vendorId);
    }

    @DeleteMapping("vendor/delete_vendor_detail_by_id")
   private ResponseEntity<?> deleteVendorById(@RequestParam Integer vendorId){
        return vendorService.deleteVendorById(vendorId);
    }
}

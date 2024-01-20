package com.ap_express_server.service.vendor;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.vendor.Vendor;
import com.ap_express_server.models.vendor.VendorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface VendorService {

    /**
     * Creates a new vendor.
     *
     * @param vendor the vendor to create
     * @return the created vendor
     */
    Vendor createVendor(Vendor vendor);

    /**
     * Updates an existing vendor.
     *
     * @param vendor the vendor to update
     * @return the updated vendor
     */
    Vendor updateVendor(Vendor vendor);

    /**
     * Retrieves a list of all vendors.
     *
     * @return a list of VendorDto objects containing the vendor information
     */
    List<VendorDto> getAllVendorList();

    /**
     * Retrieves a vendor by its ID.
     *
     * @param id the ID of the vendor to retrieve
     * @return an optional containing the vendor if found, otherwise an empty optional
     */
    Optional<Vendor> getVendorById(Integer id);

    /**
     * Deletes a vendor by its ID.
     *
     * @param id the ID of the vendor to delete
     * @return a response entity indicating the result of the deletion
     */
    ResponseEntity<?> deleteVendorById(Integer id);

    /**
     * Retrieves a list of vendors in a dropdown format.
     *
     * @return a list of DropDownDto objects containing the vendor's ID and name
     */
    List<DropDownDto> getVendorDropDownList();
}


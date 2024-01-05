package com.ap_express_server.repository.vendor;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.vendor.Vendor;
import com.ap_express_server.models.vendor.VendorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    /**
     * Retrieves a list of all vendors along with their associated information.
     *
     * @return a list of VendorDto objects containing the vendor information
     */
    @Query("SELECT NEW com.ap_express_server.models.vendor.VendorDto(ven.id, ven.name, ven.address, ven.contactNo, " +
            "ven.email, ven.paymentTermId, ven.paymentMethodId, pTerm.name, pMethod.name, ven.createdBy, ven.createdAt) " +
            "FROM Vendor ven JOIN " +
            "PaymentTerm pTerm ON ven.paymentTermId = pTerm.id JOIN PaymentMethod pMethod ON ven.paymentMethodId = pMethod.id")
    List<VendorDto> getAllVendorList();

    /**
     * Retrieves a list of vendors in a dropdown format.
     *
     * @return a list of DropDownDto objects containing the vendor's ID and name
     */
    @Query("SELECT NEW com.ap_express_server.models.dropdown.DropDownDto(vendor.id, vendor.name) from Vendor vendor")
    List<DropDownDto> getVendorDropDownList();

}

package com.ap_express_server.repository.por;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.por.PORItemInformation;
import com.ap_express_server.models.por.PORMaster;
import com.ap_express_server.models.por.PorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PorRepository extends JpaRepository<PORMaster, Long> {

    /**
     * Retrieves a list of Purchase Order Records (PORs) along with related data,
     * including the PO number and vendor name.
     * The query joins PORMaster with PoMaster and Vendor entities based on their IDs,
     * and maps the result to a PorDto object using the constructor.
     *
     * @return A list of PORMaster objects with associated data mapped to PorDto objects
     */

    @Query("select new com.ap_express_server.models.por.PorDto(por.id, por.poId, por.vendorId," +
            "DATE(por.receivedDate), DATE(por.createdDate), por.createdBy, por.totalAmount, por.status, po.poNo, vendor.name, por.porNumber)" +
            " from PORMaster por join " +
            "PoMaster po on po.id = por.poId join Vendor vendor on vendor.id = por.vendorId where por.status != 'D'")
    List<PorDto> getAllPOR();


    /**
     * Retrieves a list of vendor-related Purchase Order (PO) dropdown options.
     * Only active POs (status 'A') associated with the specified vendor ID are included.
     * @param vendorId The ID of the vendor
     * @return A list of DropDownDto objects representing vendor-related POs
     */

    @Query("select new com.ap_express_server.models.dropdown.DropDownDto(po.id, po.poNo) from PoMaster po " +
            "where po.status = 'A' and po.vendorId =:vendorId")
    List<DropDownDto> getVendorRelatedPoList(@Param("vendorId") int vendorId);

}

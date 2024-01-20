package com.ap_express_server.repository.po;
import com.ap_express_server.models.po.PoDto;
import com.ap_express_server.models.po.PoMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PoRepository extends JpaRepository<PoMaster, Integer> {

    /**
     * Retrieves the PoMaster object for the specified poId.
     *
     * @param poId The ID of the PO (Purchase Order).
     * @return An Optional containing the PoMaster object if found, or an empty Optional if not found.
     */
    @Query("SELECT pm " +
            "FROM PoMaster pm " +
            "WHERE pm.id = :poId")
    Optional<PoMaster> getPoDetailById(@Param("poId") Integer poId);


    /**
     * Retrieves a list of PoDto objects representing all the Purchase Orders.
     *
     * @return A list of PoDto objects containing the desired PO information.
     */
    @Query("select new com.ap_express_server.models.po.PoDto(pom.id, pom.poNo, pom.vendorId, ven.name, DATE(pom.orderDate)" +
            ", DATE(pom.deliveryDate), pom.total, pom.createdBy, DATE(pom.createdDate), pom.status, " +
            "CASE pom.status " +
            "    WHEN 'P' THEN 'Pending' " +
            "    WHEN 'A' THEN 'Approved' " +
            "    WHEN 'R' THEN 'Rejected' " +
            "    ELSE 'Unknown' " +
            "END) " +
            "from PoMaster pom join Vendor ven on ven.id = pom.vendorId where pom.status != 'D'")
    List<PoDto> getAllPO();


    /**
     * Retrieves set of ApprovedBillChartData objects data.
     * @return list of data in a ApprovedBillChartData format
     */
    @Query("SELECT MONTH(po.createdDate) AS month , COUNT(po) AS noOfPos FROM PoMaster po where" +
            " po.status = 'A' group by MONTH(po.createdDate)")
    List<Object[]> getApprovedPOsRawData();

}

package com.ap_express_server.repository.po;
import com.ap_express_server.models.po.PoItemInformation;
import com.ap_express_server.models.po.PoItemInformationDto;
import com.ap_express_server.models.por.PORItemInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoItemInformationRepository extends JpaRepository<PoItemInformation, Long> {

    /**
     * Retrieves a list of PoItemInformationDto objects for the specified poId.
     *
     * @param poId The ID of the PO (Purchase Order).
     * @return A list of PoItemInformationDto objects containing the desired information.
     */
    @Query("select new com.ap_express_server.models.po.PoItemInformationDto(poItem.id, poItem.poId," +
            "poItem.itemNo,item.number, item.name, poItem.qty, poItem.unitPrice, poItem.lineTotal) from PoItemInformation poItem " +
            "join Item item on poItem.itemNo = item.id where poItem.poId =:poId")
            List<PoItemInformationDto> findByPoId(@Param("poId") Integer poId);

}

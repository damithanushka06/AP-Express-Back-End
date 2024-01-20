package com.ap_express_server.repository.po;
import com.ap_express_server.models.po.PoAccountInformation;
import com.ap_express_server.models.po.PoAccountInformationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoAccountInformationRepository extends JpaRepository<PoAccountInformation, Long> {

    /**
     * Retrieves a list of PoAccountInformation objects for the specified poId.
     *
     * @param poId The ID of the PO (Purchase Order).
     * @return A list of PoAccountInformation objects containing the desired information.
     */
    @Query("select new com.ap_express_server.models.po.PoAccountInformationDto(poAcc.id, poAcc.description" +
            ", poAcc.poId, poAcc.accountId, acc.name, poAcc.lineAmount) from PoAccountInformation poAcc join " +
            "Account acc on poAcc.accountId = acc.id where poAcc.poId =:poId")
    List<PoAccountInformationDto> findByPoId(@Param("poId") Integer poId);
}

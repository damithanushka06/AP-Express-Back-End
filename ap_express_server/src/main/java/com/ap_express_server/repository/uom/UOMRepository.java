package com.ap_express_server.repository.uom;
import com.ap_express_server.models.common.UOM;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UOMRepository extends JpaRepository<UOM, Integer> {

    @Query("SELECT new com.ap_express_server.models.dropdown.DropDownDto(uom.id, uom.name) FROM UOM uom")
    public List<DropDownDto> getUOMList();
}

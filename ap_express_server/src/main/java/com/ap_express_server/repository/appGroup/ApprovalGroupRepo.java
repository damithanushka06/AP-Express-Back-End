package com.ap_express_server.repository.appGroup;
import com.ap_express_server.models.apGroup.ApprovalGroupDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalGroupRepo extends JpaRepository <ApprovalGroupDto, Integer>{

    @Query("select  new com.ap_express_server.models.dropdown.DropDownDto(apporvalGroup.id, " +
            "apporvalGroup.name) from ApprovalGroupDto apporvalGroup")
    List<DropDownDto> getApprovalGroupDropDownList();

}

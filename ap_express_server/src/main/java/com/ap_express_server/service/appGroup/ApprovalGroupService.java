package com.ap_express_server.service.appGroup;
import com.ap_express_server.models.apGroup.ApprovalGroupDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public interface ApprovalGroupService {

    /**
     * Service of create approval group
     * @param groupEntity group to approval group entity
     */
    void createApprovalGroup(ApprovalGroupDto groupEntity);

    /**
     * Service of get approval group dropdown list
     */
    List<DropDownDto> getApprovalGroupList();


    /**
     * Service of search approval group
     */
    List<ApprovalGroupDto> getAllApprovalGroup();


    /**
     * Get approval group details using it's id
     */
    Optional<ApprovalGroupDto> getApprovalGroupById(Integer id);

    /**
     * Update Approval group details
     */
    void updateApprovalGroup(ApprovalGroupDto groupEntity);

    List<DropDownDto> getApprovalGroupDropDownList();
}


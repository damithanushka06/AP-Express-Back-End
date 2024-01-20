package com.ap_express_server.services_impl.appGroup;
import com.ap_express_server.models.apGroup.ApprovalGroupDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.repository.appGroup.ApprovalGroupRepo;
import com.ap_express_server.service.appGroup.ApprovalGroupService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApprovalGroupServiceImpl implements ApprovalGroupService {

    private final ApprovalGroupRepo approvalGroupRepo;

    public ApprovalGroupServiceImpl(ApprovalGroupRepo approvalGroupRepo) {
        this.approvalGroupRepo = approvalGroupRepo;
    }

    /**
     * this method can be sued to save approval group data to db
     * @param groupEntity group to approval group entity
     */
    @Override
    public void createApprovalGroup(ApprovalGroupDto groupEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        groupEntity.setCreatedBy(currentUserName);
        groupEntity.setCreatedDate(LocalDate.now());
        approvalGroupRepo.save(groupEntity);
    }


    /**
     * this method can be sued to get approval group data
     * @return to repository
     */
    @Override
    public List<DropDownDto> getApprovalGroupList() {
        return approvalGroupRepo.findAll().stream().
                map(groupEntity -> new DropDownDto(groupEntity.getId(), groupEntity.getName())).
                collect(Collectors.toList());
    }

    /**
     * this method can be sued to search approval group
     * @return to repository
     */
    @Override
    public List<ApprovalGroupDto> getAllApprovalGroup() {
        return approvalGroupRepo.findAll();
    }

    /**
     * this method can be sued to get approval group by id
     * @return to repository
     */
    @Override
    public Optional<ApprovalGroupDto> getApprovalGroupById(Integer id) {
        return approvalGroupRepo.findById(id);
    }

    /**
     * this method can be sued to update approval group
     * @return to repository
     */
    @Override
    public void updateApprovalGroup(ApprovalGroupDto groupEntity) {
        Optional <ApprovalGroupDto> existingGroup = approvalGroupRepo.findById(groupEntity.getId());
        if(existingGroup.isPresent()){
            ApprovalGroupDto approvalGroupDto = existingGroup.get();
            approvalGroupDto.setName(groupEntity.getName());
            approvalGroupRepo.save(approvalGroupDto);
        }
    }

    @Override
    public List<DropDownDto> getApprovalGroupDropDownList() {
        return approvalGroupRepo.getApprovalGroupDropDownList();
    }

}

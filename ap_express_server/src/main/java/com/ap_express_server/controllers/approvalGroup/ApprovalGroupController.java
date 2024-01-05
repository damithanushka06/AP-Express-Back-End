package com.ap_express_server.controllers.approvalGroup;
import com.ap_express_server.models.apGroup.ApprovalGroupDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.service.appGroup.ApprovalGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class ApprovalGroupController {

    private final ApprovalGroupService groupService;

    public ApprovalGroupController(ApprovalGroupService approvalGroupService) {
        this.groupService = approvalGroupService;
    }

    /**
     * this method can be used to hit create approval group api end point
     * @param groupEntity to approvalGroup  object
     * @return to the service
     */
    @PostMapping("appGroup/create_group")
    public ResponseEntity<?> createApprovalGroup(@RequestBody ApprovalGroupDto groupEntity){
        this.groupService.createApprovalGroup(groupEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * this method can be used to get approval group list
     * @return to the service
     */
    @GetMapping("app_group/get_approval_group_list")
    public List<DropDownDto> getAprovalGroupList() {
        return groupService.getApprovalGroupList();
    }

    /**
     * this method can be used to get approval group list
     * @return to the service
     */
    @GetMapping("appGroup/search_approval_group")
    public List<ApprovalGroupDto> searchApprovalGroupList() {
        return groupService.getAllApprovalGroup();
    }

    /**
     * this method can be used to get approval group details by id
     * @param groupId to Approval group id
     */
    @GetMapping("appGroup/get_appGroup_detail_by_id")
    public Optional<ApprovalGroupDto> getUserDetailById(@RequestParam Long groupId) {
        return groupService.getApprovalGroupById(groupId);
    }

    /**
     * this method can be used to update approval group
     */
    @PutMapping("appGroup/update_approval_group")
    public ResponseEntity<ApprovalGroupDto>updateApprovalGroup(@RequestBody ApprovalGroupDto groupEntity){
        this.groupService.updateApprovalGroup(groupEntity);
        return ResponseEntity.ok().build();
    }

}

package com.ap_express_server.models.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String employeeNo;

    private Integer roleId;

    private String roleName;

    private String approvalGroupName;

    private String createdBy;

    private LocalDate createdDate;

    private byte[] proPic;

    private Integer approvalGroupId;

    private MultipartFile profilePicture;

    public UserDTO(Integer id, String username, String password, String email, String employeeNo,
                   Integer roleId, String roleName, String approvalGroupName, String createdBy,
                   LocalDate createdDate, Integer approvalGroupId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.employeeNo = employeeNo;
        this.roleId = roleId;
        this.roleName = roleName;
        this.approvalGroupName = approvalGroupName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.approvalGroupId = approvalGroupId;
    }

    public UserDTO(Integer id, String username, String password, String email, String employeeNo, Integer roleId,
                   String roleName, String approvalGroupName, Integer approvalGroupId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.employeeNo = employeeNo;
        this.roleId = roleId;
        this.roleName = roleName;
        this.approvalGroupName = approvalGroupName;
        this.approvalGroupId = approvalGroupId;
    }
}

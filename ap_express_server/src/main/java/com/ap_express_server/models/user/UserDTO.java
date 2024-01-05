package com.ap_express_server.models.user;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String employeeNo;
    private Integer roleId;

    private String roleName;

    private String approvalGroupName;

    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public UserDTO(Long id, String username, String password, String email, String employeeNo, Integer roleId, String roleName, String approvalGroupName, String createdBy, LocalDate createdDate, Integer approvalGroupId) {
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

    private LocalDate createdDate;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setProPic(byte[] proPic) {
        this.proPic = proPic;
    }

    public UserDTO(Long id, String username, String password, String email, String employeeNo, Integer roleId, String roleName, String approvalGroupName, Integer approvalGroupId) {
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

    private Integer approvalGroupId;

    public String getApprovalGroupName() {
        return approvalGroupName;
    }

    public void setApprovalGroupName(String approvalGroupName) {
        this.approvalGroupName = approvalGroupName;
    }

    private byte[] proPic;

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    private MultipartFile profilePicture;

    public UserDTO() {}

    public UserDTO(Long id, String username, String email, String employeeNo, Integer roleId, Integer approvalGroupId, String proPic) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.employeeNo = employeeNo;
        this.roleId = roleId;
        this.approvalGroupId = approvalGroupId;
        this.proPic = proPic.getBytes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getApprovalGroupId() {
        return approvalGroupId;
    }

    public void setApprovalGroupId(Integer approvalGroupId) {
        this.approvalGroupId = approvalGroupId;
    }

    public byte[] getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic.getBytes();
    }
}

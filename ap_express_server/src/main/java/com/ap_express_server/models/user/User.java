package com.ap_express_server.models.user;
import com.ap_express_server.models.role.Role;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Table(name = "user_mst",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String username;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;

  private String employeeNo;

  private Integer roleId;

  private String roleName; // this will contain the role name for the user

  public String getApprovalGroupName() {
    return approvalGroupName;
  }

  public void setApprovalGroupName(String approvalGroupName) {
    this.approvalGroupName = approvalGroupName;
  }

  private transient String approvalGroupName;
  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @NotNull
  @Positive
  private Integer approvalGroupId;

  private byte[] proPic;

  @Transient
  private String proPicBase64;

  @Column(name = "created_by")
  private String createdBy;

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public void setApprovalGroupId(Integer approvalGroupId) {
    this.approvalGroupId = approvalGroupId;
  }

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

  @Column(name = "created_date")
  private LocalDate createdDate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_role",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getEmployeeNo() {
    return employeeNo;
  }

  public void setEmployeeNo(String employeeNo) {
    this.employeeNo = employeeNo;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public int getApprovalGroupId() {
    return approvalGroupId;
  }

  public String getProPicBase64() {
    return proPicBase64;
  }

  public void setProPicBase64(String proPicBase64) {
    this.proPicBase64 = proPicBase64;
  }

  public void setApprovalGroupId(int approvalGroupId) {
    this.approvalGroupId = approvalGroupId;
  }

  public byte[] getProPic() {
    return proPic;
  }

  public void setProPic(byte[] proPic) {
    this.proPic = proPic;
  }
}

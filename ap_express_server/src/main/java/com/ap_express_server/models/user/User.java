package com.ap_express_server.models.user;
import com.ap_express_server.models.role.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Data
@Table(name = "user_mst",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

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

  private transient String approvalGroupName;

  private Integer approvalGroupId;

  private byte[] proPic;

  @Transient
  private String proPicBase64;

  @Column(name = "created_by")
  private String createdBy;

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
}

package com.ap_express_server.repository.user;
import java.util.List;
import java.util.Optional;

import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ap_express_server.models.user.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  /**
   * Retrieves an optional user by their username.
   *
   * @param username the username of the user to retrieve
   * @return an optional containing the user if found, otherwise an empty optional
   */
  Optional<User> findByUsername(String username);

  /**
   * Checks if a user exists with the specified username.
   *
   * @param username the username to check
   * @return true if a user exists with the given username, false otherwise
   */
  Boolean existsByUsername(String username);

  /**
   * Checks if a user exists with the specified email.
   *
   * @param email the email to check
   * @return true if a user exists with the given email, false otherwise
   */
  Boolean existsByEmail(String email);

  /**
   * Retrieves a list of users along with their associated information.
   *
   * @return a list of UserDTO objects containing the user information
   */
  @Query("SELECT new com.ap_express_server.models.user.UserDTO(u.id, u.username, u.password, u.email, u.employeeNo, " +
          "u.roleId, u.roleName, ag.name, u.createdBy, u.createdDate, u.approvalGroupId) FROM User u JOIN ApprovalGroupDto ag " +
          "ON u.approvalGroupId = ag.id")
  List<UserDTO> getUserList();

  /**
   * Retrieves a list of users in a dropdown format.
   *
   * @return a list of DropDownDto objects containing the user's ID and username
   */
  @Query("SELECT new com.ap_express_server.models.dropdown.DropDownDto(user.id, user.username) from User user where user.roleId != 2")
  List<DropDownDto> getUserDropDownList();

}

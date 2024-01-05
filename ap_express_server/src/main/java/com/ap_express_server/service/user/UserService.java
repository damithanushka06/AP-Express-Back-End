package com.ap_express_server.service.user;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.user.User;
import com.ap_express_server.models.user.UserDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    /**
     * service of create user
     * @param user to user object
     * @return to the user object
     */
    User createUser(User user);

    /**
     * service of update user
     * @param user to user object
     * @return to the user object
     */
    User updateUser(User user, Long userId);

    /**
     * service serve to all users
     */
    List<UserDTO> getAllUsers();

    /**
     * this method can be used to get user details
     * @param userId to user id
     */
    Optional<User> getUserDetailById(Long userId);

    /**
     * this method can be used to delete user details
     * @param userId to user id
     */
    void deleteUserDetailById(Long userId);

    /**
     * service serve to user dropdown list
     */
    List<DropDownDto> getUserDropDownList();
}

package com.ap_express_server.services_impl.user;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.user.User;
import com.ap_express_server.models.user.UserDTO;
import com.ap_express_server.repository.user.UserRepository;
import com.ap_express_server.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * this method can be used to implementation of user create service
     * save user data in user repository
     *
     * @return
     */
    @Override
    public User createUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        user.setCreatedBy(currentUserName);
       return userRepository.save(user);
    }

    /**
     * this method can be used to implementation of user update service
     * save user data in user repository
     */
    @Override
    public User updateUser(User updatedUser, Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setRoleId(updatedUser.getRoleId());
            user.setApprovalGroupId(updatedUser.getApprovalGroupId());
            user.setProPic(updatedUser.getProPic());

            // Set other properties as needed
            return userRepository.save(user);
        }
        return updatedUser;
    }

    /**
     * this method can be used to implementation of get all users service
     * get all users data in user repository
     */
    @Override
    public List<UserDTO> getAllUsers() {
       return userRepository.getUserList();
    }

    /**
     * this method can be used to get user details from db
     * @param userId to user id
     * @return to the repo
     */
    @Override
    public Optional<User> getUserDetailById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            byte[] proPicData = user.getProPic();
            if (proPicData != null) {
                String base64Encoded = Base64.getEncoder().encodeToString(proPicData);
                user.setProPicBase64(base64Encoded);
            }
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    /**
     * this method can be used to delete a user
     * @param userId to user id
     */
    @Override
    public void deleteUserDetailById(Integer userId) {
        userRepository.deleteById(userId);
    }

    /**
     * this method used to get user list as dropdown list
     * @return
     */
    @Override
    public List<DropDownDto> getUserDropDownList() {
        return userRepository.getUserDropDownList();
    }


}

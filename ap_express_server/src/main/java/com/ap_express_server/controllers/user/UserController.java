package com.ap_express_server.controllers.user;
import com.ap_express_server.models.apGroup.ApprovalGroupDto;
import com.ap_express_server.models.role.Role;
import com.ap_express_server.models.user.User;
import com.ap_express_server.models.user.UserDTO;
import com.ap_express_server.repository.appGroup.ApprovalGroupRepo;
import com.ap_express_server.repository.role.RoleRepository;
import com.ap_express_server.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    private final ApprovalGroupRepo approvalGroupRepo;

    public UserController(UserService userService, RoleRepository roleRepository, ApprovalGroupRepo approvalGroupRepo) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.approvalGroupRepo = approvalGroupRepo;
    }

    /**
     * this method can be used to hit create user api end point
     *
     * @param userDTO to user object
     * @return to the service
     */
    @PostMapping("user/create_user")
    public ResponseEntity<?> createUser(@RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture, @ModelAttribute UserDTO userDTO) throws IOException {
        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        if (userDTO.getId() == null) {
            userDTO.setId(0); // Set a default value of 0
        }
        user.setEmployeeNo(userDTO.getEmployeeNo());
        user.setRoleId(userDTO.getRoleId());
        user.setApprovalGroupId(userDTO.getApprovalGroupId());
        user.setCreatedDate(LocalDate.now());
        Optional<Role> optionalRole = roleRepository.findById(userDTO.getRoleId());
        Optional<ApprovalGroupDto> optionalGroup = approvalGroupRepo.findById(userDTO.getApprovalGroupId());
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            user.setRoleName(role.getName().name());
        }
        if(optionalGroup.isPresent()){
            ApprovalGroupDto approvalGroupDto = optionalGroup.get();
            user.setApprovalGroupName(approvalGroupDto.getName());
        }
        if (profilePicture != null) {
            user.setProPic(profilePicture.getBytes());
        }
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }


    /**
     * this method can be used to hit create user api end point
     *
     * @param user to user object
     * @return to the service
     */
    @PutMapping("user/update_user")
    public User updateUser(@RequestParam(value = "profilePicture", required = false)
                               MultipartFile profilePicture, @ModelAttribute User user) throws IOException {
        if (profilePicture != null) {
            user.setProPic(profilePicture.getBytes());
        }
        return userService.updateUser(user, user.getId());
    }


    /**
     * this method can be used to hit get all user service
     * @return to the service
     */
    @GetMapping("user/getAll")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * this method can be used to get user details
     *
     * @param userId to user id
     */
    @GetMapping("user/get_user_detail_by_id")
    public Optional<User> getUserDetailById(@RequestParam Integer userId) {
        return userService.getUserDetailById(userId);
    }


    /**
     * this method can be used to delete a user
     *
     * @param userId to user id
     */
    @DeleteMapping("user/delete_user_detail_by_id")
    public ResponseEntity<?> deleteUserById(@RequestParam Integer userId) {
        userService.deleteUserDetailById(userId);
        return ResponseEntity.ok().build();
    }
}

package com.ap_express_server.controllers.role;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.role.Role;
import com.ap_express_server.service.role.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class RoleController {

    private final RoleService  roleService;
    RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    /**
     * this method can be used to get all roles
     * @return to the service
     */
    @GetMapping("role/get_all_roles")
    public List<DropDownDto> getAllUsers() {
        return roleService.getAllRoles();
    }
}

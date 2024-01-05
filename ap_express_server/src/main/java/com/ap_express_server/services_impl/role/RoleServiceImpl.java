package com.ap_express_server.services_impl.role;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.repository.role.RoleRepository;
import com.ap_express_server.service.role.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * this method get all role from role_mst table
     * @return to role list
     */
    @Override
    public List<DropDownDto> getAllRoles() {
        return this.roleRepository.findAll().stream()
                .map(role -> new DropDownDto(role.getId(), role.getName().name()))
                .collect(Collectors.toList());
    }
}

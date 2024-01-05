package com.ap_express_server.service.role;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<DropDownDto> getAllRoles();
}

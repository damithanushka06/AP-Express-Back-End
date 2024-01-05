package com.ap_express_server.repository.role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ap_express_server.models.role.ERole;
import com.ap_express_server.models.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
  Optional<Role> findById(int id);
}

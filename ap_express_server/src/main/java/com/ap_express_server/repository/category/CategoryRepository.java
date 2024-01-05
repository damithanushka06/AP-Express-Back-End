package com.ap_express_server.repository.category;
import com.ap_express_server.models.common.Category;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Long>{

    @Query("SELECT new com.ap_express_server.models.dropdown.DropDownDto(cat.id, cat.name) FROM Category cat")
    public List<DropDownDto> getCategoryList();
}

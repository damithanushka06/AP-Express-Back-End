package com.ap_express_server.repository.accountType;
import com.ap_express_server.models.common.AccountType;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    /**
     * thisa method query from account_type table and get account type list
     * @return account type list
     */
    @Query("SELECT new com.ap_express_server.models.dropdown.DropDownDto(type.id, type.name) FROM AccountType type")
    public List<DropDownDto> getAccountTypeList();
}

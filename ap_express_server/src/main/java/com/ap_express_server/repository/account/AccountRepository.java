package com.ap_express_server.repository.account;
import com.ap_express_server.models.account.Account;
import com.ap_express_server.models.account.AccountDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * this method can be used to get all account list
     * @return to list of AccountDto
     */
    @Query("SELECT new com.ap_express_server.models.account.AccountDto(account.id, account.name, account.description," +
            " account.accountTypeId, account.parentAccountId, accountType.name, parentAccount.name, account.createdBy, account.createdDate)" +
            " FROM Account account" +
            " JOIN AccountType accountType ON accountType.id = account.accountTypeId" +
            " LEFT JOIN Account parentAccount ON parentAccount.id = account.parentAccountId")
    List<AccountDto> getAllAccountList();





    /**
     * this method can be used to get all account list as dropdown dto list
     * @return to list of DropDownDto
     */
    @Query("SELECT new com.ap_express_server.models.dropdown.DropDownDto(acc.id, acc.number) from Account acc")
    List<DropDownDto> getParentAccountList();
}

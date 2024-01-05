package com.ap_express_server.service.account;
import com.ap_express_server.models.account.Account;
import com.ap_express_server.models.account.AccountDto;
import com.ap_express_server.models.common.CommonResponse;
import com.ap_express_server.models.dropdown.DropDownDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {

    /**
     * this method can be used to create account
     * @param account to Account master obj
     * @return to account obj
     */
    Account createAccount(Account account);

    /**
     * this method can be used to get account type list
     * @return to Dropdown list
     */
    List<DropDownDto> getAccountTypeList();

    /**
     * this method can be used to get parent account list
     * @return to Dropdown list
     */
    List<DropDownDto> getParentAccountTypeList();

    /**
     * this method can be used to get all account list
     * @return to AccountDto
     */
    List<AccountDto> getAllAccountList();

    /**
     * this method can be used to get account detail by id
     * @param accId to account master id
     * @return to Account
     */
    Optional<Account> getAccountDetailById(Long accId);

    /**
     * this method can be used to delete account detail by id
     * @param accId to account master id
     */
    void deleteAccountDetailById(Long accId);

    /**
     * this method can be used to update account detail by id
     * @param account to account master obj
     * @return to Account
     */
    Account updateAccount(Account account);

    /**
     * this method can be used to get accounts as dropdown dto
     * @return accounts dropdown list
     */
    List<DropDownDto> getAccountDropDownList();

    /**
     * this method used for get account name by id
     * @param accId to account id
     * @return to account name of given id
     */
    CommonResponse getAccountNameByAccountId(@RequestParam Long accId);
}

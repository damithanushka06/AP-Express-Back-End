package com.ap_express_server.controllers.account;
import com.ap_express_server.constant.Notification.HttpUrlConstants;
import com.ap_express_server.models.account.Account;
import com.ap_express_server.models.account.AccountDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = HttpUrlConstants.FRONTEND_BASE_URL, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * this method can be used to create Account
     * @param account to Account model
     * @return to the service
     */
    @PostMapping("account/create_account")
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }


    /**
     * this method can be used for load category list
     */
    @GetMapping("account/get_account_type_list")
    public List<DropDownDto> getAccountTypeList(){
        return  accountService.getAccountTypeList();
    }

    /**
     * this method can be used for load parent account list
     */
    @GetMapping("account/get_parent_account_list")
    public List<DropDownDto> getParentAccountTypeList(){
        return  accountService.getParentAccountTypeList();
    }


    /**
     * this method can be used for load all Account list
     */
    @GetMapping("account/getAll")
    public List<AccountDto> getAccountList(){return accountService.getAllAccountList();}

    /**
     * this method can be used to get Account details by Account id
     * @param accId id to Account master id
     */
    @GetMapping("account/get_account_detail_by_id")
    public Optional<Account> getAccountDetailById(@RequestParam Integer accId) {
        return accountService.getAccountDetailById(accId);
    }


    /**
     * this method can be used to delete selected Account
     * @param accId to Account id
     */
    @DeleteMapping("account/delete_account_detail_by_id")
    public ResponseEntity<?> deleteAccountById(@RequestParam Integer accId) {
        accountService.deleteAccountDetailById(accId);
        return ResponseEntity.ok().build();
    }

    /**
     * this method can be used to update Account
     * @param account to Account model
     * @return to the service
     */
    @PutMapping("account/update_account")
    private Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

}

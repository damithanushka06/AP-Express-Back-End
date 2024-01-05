package com.ap_express_server.services_impl.account;
import com.ap_express_server.models.account.Account;
import com.ap_express_server.models.account.AccountDto;
import com.ap_express_server.models.common.CommonResponse;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.repository.account.AccountRepository;
import com.ap_express_server.repository.accountType.AccountTypeRepository;
import com.ap_express_server.service.account.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountTypeRepository accountTypeRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AccountTypeRepository accountTypeRepository) {
        this.accountRepository = accountRepository;
        this.accountTypeRepository = accountTypeRepository;
    }

    /**
     * this method can be used to create account
     * @param account to Account master obj
     * @return to Account
     */
    @Override
    public Account createAccount(Account account) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        account.setCreatedBy(currentUserName);
        account.setCreatedDate(LocalDate.now());
        return accountRepository.save(account);
    }

    /**
     * this method can be used to get account type list
     * @return to list of DropDownDto
     */
    @Override
    public List<DropDownDto> getAccountTypeList() {
        return this.accountTypeRepository.getAccountTypeList();
    }

    /**
     * this method can be used to get parent account list
     * @return to list of DropDownDto
     */
    @Override
    public List<DropDownDto> getParentAccountTypeList() {
        return accountRepository.getParentAccountList();
    }

    /**
     * this method can be used to get all accounts
     * @return to lis of AccountDto
     */
    @Override
    public List<AccountDto> getAllAccountList() {
        return accountRepository.getAllAccountList();
    }

    /**
     * this method can be used to get account detail by id
     * @param accId to account master id
     * @return to Account object
     */
    @Override
    public Optional<Account> getAccountDetailById(Long accId) {
        Optional<Account> account = accountRepository.findById(accId);
        if(account.isPresent()){
            Account acc = account.get();
            return Optional.of(acc);
        } else {
            return Optional.empty();
        }
    }

    /**
      this method can be used to delete account detail by id
     * @param accId to account master id
     */
    @Override
    public void deleteAccountDetailById(Long accId) {
      accountRepository.deleteById(accId);
    }

    /**
     * this method can be used to update account detail
     * @param account to account master obj
     * @return to Account
     */
    @Override
    public Account updateAccount(Account account) {
        Optional<Account> existAccount = accountRepository.findById(account.getId());
        if(existAccount.isPresent()){
            Account findAccount = existAccount.get();
            findAccount.setName(account.getName());
            findAccount.setDescription(account.getDescription());
            findAccount.setAccountTypeId(account.getAccountTypeId());
            findAccount.setParentAccountId(account.getParentAccountId());
            return accountRepository.save(findAccount);
        } else {
            throw new NoSuchElementException("Account not found with id: " + account.getId());
        }
    }
    /**
     * Retrieves the dropdown list of accounts.
     *
     * @return the list of dropdown items representing accounts
     */
    @Override
    public List<DropDownDto> getAccountDropDownList() {
        return accountRepository.getParentAccountList();
    }

    /**
     * Retrieves the account name by account ID.
     *
     * @param accId the ID of the account
     * @return the CommonResponse containing the account name
     * @throws NoSuchElementException if the account is not found
     */
    @Override
    public CommonResponse getAccountNameByAccountId(Long accId) {
        Optional<Account> account = accountRepository.findById(accId);
        if (account.isPresent()) {
            Account presentAccount = account.get();
            return new CommonResponse(presentAccount.getName());
        } else {
            throw new NoSuchElementException("Account not found with id: " + accId);
        }
    }

}

package com.ap_express_server.controllers.serve_dropdown_list;
import com.ap_express_server.exception.CustomException;
import com.ap_express_server.models.common.CommonResponse;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.service.account.AccountService;
import com.ap_express_server.service.appGroup.ApprovalGroupService;
import com.ap_express_server.service.bill.BillService;
import com.ap_express_server.service.item.ItemService;
import com.ap_express_server.service.user.UserService;
import com.ap_express_server.service.vendor.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class DropdownController {

    private final VendorService vendorService;

    private final ItemService itemService;

    private final AccountService accountService;

    private final ApprovalGroupService approvalGroupService;

    private final UserService userService;

    private final BillService billService;

    public DropdownController(VendorService vendorService, ItemService itemService, AccountService accountService,
                              ApprovalGroupService approvalGroupService, UserService userService, BillService billService) {
        this.vendorService = vendorService;
        this.itemService = itemService;
        this.accountService = accountService;
        this.approvalGroupService = approvalGroupService;
        this.userService = userService;
        this.billService = billService;
    }

    /**
     * Retrieves a list of users in a dropdown format.
     *
     * @return a list of DropDownDto objects containing user information
     * @throws CustomException if an error occurs while retrieving the user dropdown list
     */
    @GetMapping("dropdown/get_user_dropdown_list")
    public List<DropDownDto> getUserDropDownList() throws CustomException {
        return this.userService.getUserDropDownList();
    }

    /**
     * Retrieves a list of vendors in a dropdown format.
     *
     * @return a list of DropDownDto objects containing vendor information
     * @throws CustomException if an error occurs while retrieving the vendor dropdown list
     */
    @GetMapping("dropdown/get_vendor_dropdown_list")
    public List<DropDownDto> getVendorDropDownList() throws CustomException {
        return this.vendorService.getVendorDropDownList();
    }

    /**
     * Retrieves a list of items in a dropdown format.
     *
     * @return a list of DropDownDto objects containing item information
     * @throws CustomException if an error occurs while retrieving the item dropdown list
     */
    @GetMapping("dropdown/get_item_dropdown_list")
    public List<DropDownDto> getItemDropDownList() throws CustomException {
        return this.itemService.getItemDropDownList();
    }

    /**
     * Retrieves a list of accounts in a dropdown format.
     *
     * @return a list of DropDownDto objects containing account information
     * @throws CustomException if an error occurs while retrieving the account dropdown list
     */
    @GetMapping("dropdown/get_account_dropdown_list")
    public List<DropDownDto> getAccountDropDownList() throws CustomException {
        return this.accountService.getAccountDropDownList();
    }

    /**
     * Retrieves a list of approval groups in a dropdown format.
     *
     * @return a list of DropDownDto objects containing approval group information
     * @throws CustomException if an error occurs while retrieving the approval group dropdown list
     */
    @GetMapping("dropdown/get_approvalGroup_dropdown_list")
    public List<DropDownDto> getApprovalGroupDropDownList() throws CustomException {
        return this.approvalGroupService.getApprovalGroupDropDownList();
    }

    /**
     * Retrieves the account name by account ID.
     *
     * @param accId the ID of the account
     * @return the CommonResponse containing the account name
     */
    @GetMapping("account/get_name_by_id")
    public CommonResponse getItemNameById(@RequestParam Long accId) {
        return accountService.getAccountNameByAccountId(accId);
    }

    /**
     * Retrieves list of bills in a dropdown format
     * @param vendorId to vendor id
     * @return a list of DropDownDto objects containing bill id and name
     * @throws CustomException if an error occurs while retrieving the bill dropdown list
     */
    @GetMapping("dropdown/get_vendor_related_bill_list")
    public List<DropDownDto> getVendorRelatedBillDropDownList(@RequestParam Long vendorId) throws
            CustomException {
        return this.billService.getVendorRelatedActiveBillList(vendorId);
    }

}

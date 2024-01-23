package com.ap_express_server.services_impl.bill;

import com.ap_express_server.common_utitlity.*;
import com.ap_express_server.constant.Notification.HttpResponseMessage;
import com.ap_express_server.models.bill.*;
import com.ap_express_server.models.chart.ChartData;
import com.ap_express_server.models.common.WorkflowConfig;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.payment.PaymentMethod;
import com.ap_express_server.repository.bill.BillAccountInformationRepository;
import com.ap_express_server.repository.bill.BillAttachmentRepository;
import com.ap_express_server.repository.bill.BillItemInformationRepository;
import com.ap_express_server.repository.bill.BillRepository;
import com.ap_express_server.repository.payment.PaymentRepository;
import com.ap_express_server.repository.payment.PaymentTermRepository;
import com.ap_express_server.repository.vendor.VendorRepository;
import com.ap_express_server.repository.workflow.WorkFlowRepository;
import com.ap_express_server.service.bill.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    private final BillItemInformationRepository billItemRepository;

    private final BillAccountInformationRepository billAccountInformationRepository;

    private final BillAttachmentRepository billAttachmentRepository;

    private final WorkFlowRepository workFlowRepository;

    private final VendorRepository vendorRepository;

    private final PaymentRepository paymentRepository;

    private final PaymentTermRepository paymentTermRepository;

    public BillServiceImpl(BillRepository billRepository,
                           BillItemInformationRepository billItemRepository,
                           BillAccountInformationRepository billAccountInformationRepository,
                           BillAttachmentRepository billAttachmentRepository, WorkFlowRepository workFlowRepository,
                           VendorRepository vendorRepository, PaymentRepository paymentRepository,
                           PaymentTermRepository paymentTermRepository) {
        this.billRepository = billRepository;
        this.billItemRepository = billItemRepository;
        this.billAccountInformationRepository = billAccountInformationRepository;
        this.billAttachmentRepository = billAttachmentRepository;
        this.workFlowRepository = workFlowRepository;
        this.vendorRepository = vendorRepository;
        this.paymentRepository = paymentRepository;
        this.paymentTermRepository = paymentTermRepository;
    }

    /**
     * Endpoint to create a new Bill Master.
     *
     * @param billMaster The Bill Master object containing the information for the new bill.
     * @return The created Bill Master object.
     */
    @Override
    public ResponseEntity<Object> createBillMaster(BillMaster billMaster) throws CustomException, IOException {
        // Validate request object

        validateBillObject(billMaster);

        // Set initial status and workflow details
        billMaster.setStatus(Status.STATUS_PENDING);
        billMaster.setTotalLevels(billMaster.getWorkflowDetails().size());
        billMaster.setCurrentLevel(billMaster.getCurrentLevel() + 1);

        // Set created by and created date
        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        billMaster.setCreatedBy(createdBy);
        billMaster.setCreatedDate(LocalDate.now());

        // Save the bill master
        billRepository.save(billMaster);

        // Perform calculations for each Bill item
        float total = 0;
        List<BillItemInformation> billItems = billMaster.getBillItemInformation();
        float totalLineItemAmount = 0;
        for (BillItemInformation item : billItems) {
            int qty = item.getQty();
            float unitPrice = item.getUnitPrice();
            float lineTotal = qty * unitPrice;
            totalLineItemAmount += lineTotal;
            item.setLineTotal(lineTotal);
            item.setBillId(billMaster.getId());
            billItemRepository.save(item);
        }

        // Perform calculations for each Bill Account
        List<BillAccountInformation> billAccounts = billMaster.getBillAccountInformation();
        float totalLineAccountAmount = 0;
        for (BillAccountInformation account : billAccounts) {
            float lineAmount = account.getLineAmount();
            totalLineAccountAmount += lineAmount;
            account.setLineAmount(lineAmount);
            account.setBillId(billMaster.getId());
            billAccountInformationRepository.save(account);
        }

        // Calculate total amount
        total = (totalLineItemAmount + totalLineAccountAmount) - billMaster.getTaxAmount();
        billMaster.setItemTotal(totalLineItemAmount);
        billMaster.setAccountTotal(totalLineAccountAmount);
        billMaster.setBillAmount(total);

        // Save workflow related data
        List<WorkflowConfig> workflowConfigs = billMaster.getWorkflowDetails();
        for (WorkflowConfig workflow : workflowConfigs) {
            workflow.setDocumentTypeId(DocumentTypeId.BILL);
            workflow.setDocumentId(billMaster.getId());
            workflow.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            workflow.setCreatedDate(new Date());
            workFlowRepository.save(workflow);
        }
        billMaster.setUserId(workflowConfigs.get(0).getUserId());
        // Save Bill attachments
        List<MultipartFile> files = billMaster.getFiles();
        for (MultipartFile file : files) {
            // Perform file operations
            String fileName = file.getOriginalFilename();
            String filePath = LocalPath.BILL_FILE_UPLOAD_DER + fileName;

            // Save the file to the destination folder
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // Create and save the BillAdditionalAttachment entity
            BillAdditionalAttachment attachment = new BillAdditionalAttachment();
            attachment.setPoId(billMaster.getId());
            attachment.setFilePath(filePath);
            attachment.setStatus(Status.STATUS_ACTIVE);
            billAttachmentRepository.save(attachment);
        }

        // Save the updated bill master
        billRepository.save(billMaster);

        return ResponseEntity.ok().build();
    }


    /**
     * Validate Bill object
     *
     * @param billMaster to BillMaster
     * @throws CustomException an exception
     */
    void validateBillObject(BillMaster billMaster) throws CustomException {
        if (billMaster.getBillNo() == null) {
            throw new CustomException(HttpResponseMessage.INVOICE_DATE_CANNOT_BE_EMPTY, HttpStatus.MULTI_STATUS.value());
        }
        if (billMaster.getVendorId() == null) {
            throw new CustomException(HttpResponseMessage.VENDOR_CANNOT_BE_EMPTY, HttpStatus.MULTI_STATUS.value());
        }
        if (billMaster.getBillDate() == null) {
            throw new CustomException(HttpResponseMessage.BILL_DATE_CANNOT_BE_NULL, HttpStatus.MULTI_STATUS.value());
        }
    }


    /**
     * Retrieves the Bill Master detail by its ID.
     *
     * @param billId The ID of the Bill Master to retrieve.
     * @return An optional containing the Bill Master detail if found, or an empty optional if not found.
     */
    @Override
    public Optional<BillMaster> getBillMasterDetailById(Integer billId) {
        Optional<BillMaster> billMasterOptional = billRepository.getBillDetailById(billId);
        if (!billMasterOptional.isPresent()) {
            new ResponseEntity<>(HttpResponseMessage.REQUESTED_BILL_IS_NOT_AVAILABLE,
                    HttpStatus.valueOf(HttpStatus.MULTI_STATUS.value()));
        } else {
            BillMaster billMaster = billMasterOptional.get();
            billMaster.setBillAccountInformation(billAccountInformationRepository.findByBillId(billMaster.getId()));
            billMaster.setBillItemInformation(billItemRepository.findByBillId(billMaster.getId()));
            billMaster.setWorkflowDetails(workFlowRepository.findByDocumentIdAndDocumentTypeId(billMaster.getId(),
                    DocumentTypeId.BILL));
            billMaster.setVendorName(vendorRepository.findById(billMaster.getVendorId()).get().getName());
            billMaster.setPaymentTermName(paymentTermRepository.findPaymentTermById(billMaster.getTermId()).getName());
        }
        return billRepository.findById(billId);
    }

    /**
     * Retrieves all Bill Masters.
     *
     * @return A list of BillDto objects containing the information of all Bill Masters.
     */
    @Override
    public List<BillDto> getAllBillMasters() {
        return billRepository.getAllBills();
    }

    /**
     * Deletes a Bill Master by its ID.
     *
     * @param billId The ID of the Bill Master to delete.
     */
    @Override
    public void deleteBillMasterById(Integer billId) {
        Optional<BillMaster> billMaster = billRepository.findById(billId);
        if (billMaster.isPresent()) {
            BillMaster bill = billMaster.get();
            bill.setStatus(Status.STATUS_DELETED);
            billRepository.save(bill);
        }
    }

    /**
     * Updates a Bill Master.
     *
     * @param billMaster The updated Bill Master object.
     * @return The response entity indicating the success or failure of the update operation.
     */
    @Override
    public ResponseEntity<?> updateBillMaster(BillMaster billMaster) {

        Optional<BillMaster> existingBillMasterOptional = billRepository.findById(billMaster.getId());

        if (!existingBillMasterOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bill not found");
        } else {
            BillMaster billMst = existingBillMasterOptional.get();

            // Update bill details
            billMst.setBillNo(billMaster.getBillNo());
            billMst.setBillDate(billMaster.getBillDate());
            billMst.setBillAmount(billMaster.getBillAmount());
            billMst.setWorkflowDetails(billMaster.getWorkflowDetails());
            billMst.setVendorId(billMaster.getVendorId());
            billMst.setBillItemInformation(billMaster.getBillItemInformation());
            billMst.setBillAccountInformation(billMaster.getBillAccountInformation());

            // Save the updated bill master
            billRepository.save(billMst);

            return ResponseEntity.ok().build();
        }
    }

    /**
     * Retrieve active dropdown list
     *
     * @param vendorId to vendor id
     * @return list of active bill list as DropDownDto
     */
    @Override
    public List<DropDownDto> getVendorRelatedActiveBillList(Integer vendorId) {
        return this.billRepository.getVendorRelatedActiveBillList(vendorId);
    }

    /**
     * Retrieves a list of ApprovedBillChartData objects representing approved bills.
     *
     * @return List of ApprovedBillChartData.
     */
    @Override
    public List<ChartData> getApprovedBills() {
        List<Object[]> rawData = billRepository.getApprovedBillsRawData();
        List<ChartData> result = new ArrayList<>();
        for (Object[] row : rawData) {
            int month = Integer.parseInt(row[0].toString());
            int noOfBills = Integer.parseInt(row[1].toString());
            ChartData data = new ChartData(month, noOfBills);
            result.add(data);
        }
        return result;
    }

    /**
     * Approve bill/approve and finalize
     * @param billId to bill master id
     * @return ResponseEntity<Object>
     */
    @Override
    public ResponseEntity<Object> approveBill(Integer billId) {
        Optional<BillMaster> billMasterOptional = getBillMasterDetailById(billId);
        BillMaster billMaster = billMasterOptional.get();
        List <WorkflowConfig> workflowConfigs = billMaster.getWorkflowDetails();
        if(billMaster.getCurrentLevel() < billMaster.getTotalLevels()){
            billMaster.setCurrentLevel(billMaster.getCurrentLevel() + 1);
            billMaster.setUserId(workflowConfigs.get(billMaster.getCurrentLevel() - 1).getUserId());
        } else if(billMaster.getCurrentLevel() > billMaster.getTotalLevels()){
            return new ResponseEntity<>("Record is invalid", HttpStatus.MULTI_STATUS);
        } else {
            billMaster.setStatus(Status.STATUS_APPROVED);
        }
        billRepository.save(billMaster);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

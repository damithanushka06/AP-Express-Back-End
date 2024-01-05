package com.ap_express_server.services_impl.po;
import com.ap_express_server.common_utitlity.DocumentTypeId;
import com.ap_express_server.common_utitlity.LocalPath;
import com.ap_express_server.common_utitlity.Status;
import com.ap_express_server.models.chart.ChartData;
import com.ap_express_server.models.common.WorkflowConfig;
import com.ap_express_server.models.po.*;
import com.ap_express_server.models.vendor.Vendor;
import com.ap_express_server.repository.po.PoAccountInformationRepository;
import com.ap_express_server.repository.po.PoAttachmentRepository;
import com.ap_express_server.repository.po.PoItemInformationRepository;
import com.ap_express_server.repository.po.PoRepository;
import com.ap_express_server.repository.vendor.VendorRepository;
import com.ap_express_server.repository.workflow.WorkFlowRepository;
import com.ap_express_server.service.po.PoService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class PoServiceImpl implements PoService {

    private final PoRepository poRepository;

    private final PoItemInformationRepository poItemInformationRepository;

    private final PoAccountInformationRepository accountInformationRepository;

    private final PoAttachmentRepository poAttachmentRepository;

    private final WorkFlowRepository workFlowRepository;

    private final VendorRepository vendorRepository;

    public PoServiceImpl(PoRepository poRepository, PoItemInformationRepository poItemInformationRepository,
                         PoAccountInformationRepository accountInformationRepository, PoAttachmentRepository poAttachmentRepository, WorkFlowRepository workFlowRepository, VendorRepository vendorRepository) {
        this.poRepository = poRepository;
        this.poItemInformationRepository = poItemInformationRepository;
        this.accountInformationRepository = accountInformationRepository;
        this.poAttachmentRepository = poAttachmentRepository;
        this.workFlowRepository = workFlowRepository;
        this.vendorRepository = vendorRepository;
    }

    /**
     * Creates a new Purchase Order (PO) by saving the provided PO Master object.
     *
     * @param poMaster The PO Master object to be created.
     * @return The created PO Master object.
     */
    @Override
    public ResponseEntity<Object> createPo(PoMaster poMaster) throws IOException {
        // Save the provided PO Master object using the repository

        poMaster.setStatus(Status.STATUS_PENDING);
        poMaster.setTotalLevels(poMaster.getWorkflowDetails().size());
        poMaster.setCurrentLevel(poMaster.getCurrentLevel() + 1);

        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        poMaster.setCreatedBy(createdBy);
        poMaster.setCreatedDate(new Date());
        poRepository.save(poMaster);

        // Perform calculations for each PO item

        float total = 0;
        List<PoItemInformation> poItems = poMaster.getPoItemInformation();
        float totalLineItemAmount = 0;
        for (PoItemInformation item : poItems) {
            int qty = item.getQty();
            float unitPrice = item.getUnitPrice();
            float lineTotal = qty * unitPrice;
            totalLineItemAmount += lineTotal;
            item.setLineTotal(lineTotal);
            item.setPoId(poMaster.getId());
            poItemInformationRepository.save(item);
        }

        // Perform calculations for each PO Account

        List<PoAccountInformation> poAccounts = poMaster.getPoAccountInformation();
        float totalLineAccountAmount = 0;
        for (PoAccountInformation account : poAccounts) {
            float lineAmount = account.getLineAmount();
            totalLineAccountAmount += lineAmount;
            account.setLineAmount(lineAmount);
            account.setPoId(poMaster.getId());
            accountInformationRepository.save(account);
        }

        total = (totalLineItemAmount + totalLineAccountAmount) - poMaster.getTaxAmount();
        poMaster.setItemTotal(totalLineItemAmount);
        poMaster.setAccountTotal(totalLineAccountAmount);
        poMaster.setTotal(total);

        // Save workflow related data

        List<WorkflowConfig> workflowConfigs = poMaster.getWorkflowDetails();
        for (WorkflowConfig workflow : workflowConfigs) {
            workflow.setDocumentTypeId(DocumentTypeId.PO);
            workflow.setDocumentId(poMaster.getId());
            workflow.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            workflow.setCreatedDate(new Date());
            workFlowRepository.save(workflow);
        }

        // Save PO attachments

        List<MultipartFile> files = poMaster.getFiles();
        for (MultipartFile file : files) {
            // Perform file operations
            String fileName = file.getOriginalFilename();
            String filePath = LocalPath.PO_FILE_UPLOAD_DER + fileName;

            // Save the file to the destination folder
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // Create and save the PoAdditionalAttachment entity
            PoAdditionalAttachment attachment = new PoAdditionalAttachment();
            attachment.setPoId(poMaster.getId());
            attachment.setFilePath(filePath);
            attachment.setStatus(Status.STATUS_ACTIVE);
            poAttachmentRepository.save(attachment);
        }
        poRepository.save(poMaster);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves the Purchase Order detail by its ID.
     *
     * @param poId The ID of the Purchase Order to retrieve.
     * @return An optional containing the Purchase Order detail if found, or an empty optional if not found.
     */
    @Override
    public Optional<PoMaster> getPoDetailById(Long poId) {
        List<PoItemInformationDto> poItemInformationList = poItemInformationRepository.findByPoId(poId);
        List<PoAccountInformationDto> poAccountInformationList = accountInformationRepository.findByPoId(poId);
        List<WorkflowConfig> workflowConfigs = workFlowRepository.findByDocumentIdAndDocumentTypeId(poId, DocumentTypeId.PO);
        List<PoAdditionalAttachment> files = poAttachmentRepository.findByPoId(poId);
        Optional<PoMaster> poMaster = poRepository.getPoDetailById(poId);
        if (poMaster.isPresent()) {
            PoMaster poMst = poMaster.get();

            // Convert PoItemInformationDto to PoItemInformation
            List<PoItemInformation> convertedItemList = poItemInformationList.stream()
                    .map(this::convertToPoItemInformation)
                    .collect(Collectors.toList());

            // Convert PoItemInformationDto to PoItemInformation
            List<PoAccountInformation> convertedAccountList = poAccountInformationList.stream()
                    .map(this::convertToPoAccountInformation)
                    .collect(Collectors.toList());

            poMst.setPoItemInformation(convertedItemList);
            poMst.setPoAccountInformation(convertedAccountList);
            poMst.setWorkflowDetails(workflowConfigs);
            poMst.setAttachments(files);
        }
        return poMaster;
    }

    /**
     * Converts a PoItemInformationDto object to a PoItemInformation object.
     *
     * @param dto The PoItemInformationDto object to convert.
     * @return The converted PoItemInformation object.
     */
    private PoItemInformation convertToPoItemInformation(PoItemInformationDto dto) {
        PoItemInformation itemInformation = new PoItemInformation();
        itemInformation.setId(dto.getId());
        itemInformation.setPoId(dto.getPoId());
        itemInformation.setItemNo(dto.getItemNo());
        itemInformation.setQty(dto.getQty());
        itemInformation.setUnitPrice(dto.getUnitPrice());
        itemInformation.setLineTotal(dto.getLineTotal());
        itemInformation.setItemName(dto.getItemName());
        return itemInformation;
    }

    /**
     * Converts a PoAccountInformationDto object to a PoAccountInformation object.
     *
     * @param dto The PoAccountInformationDto object to convert.
     * @return The converted PoAccountInformation object.
     */
    private PoAccountInformation convertToPoAccountInformation(PoAccountInformationDto dto) {
        PoAccountInformation poAccountInformation = new PoAccountInformation();
        poAccountInformation.setId(dto.getId());
        poAccountInformation.setPoId(dto.getPoId());
        poAccountInformation.setAccountName(dto.getAccountName());
        poAccountInformation.setDescription(dto.getDescription());
        poAccountInformation.setLineAmount(dto.getLineAmount());
        poAccountInformation.setAccountId(dto.getAccountId());
        return poAccountInformation;
    }


    /**
     * Retrieves all pos
     * @return poDto list
     */
    @Override
    public List<PoDto> getAllPOs() {
        return poRepository.getAllPO();
    }

    /**
     * Delete a po record
     * @param poId to po master id
     */
    @Override
    public void deletePODetailById(Long poId) {
        Optional<PoMaster> poMaster = poRepository.getPoDetailById(poId);
        if(poMaster.isPresent()){
           PoMaster po = poMaster.get();
            po.setStatus(Status.STATUS_DELETED);
            poRepository.save(po);
        }
    }

    /**
     * Updates the information for an existing Purchase Order (PO) based on the provided PO Master object.
     *
     * @param poMaster The PO Master object containing the updated information.
     * @return A ResponseEntity indicating the result of the update operation.
     * @throws IOException If an error occurs during the update process.
     */
    @Override
    public ResponseEntity<Object> updatePo(PoMaster poMaster) throws IOException {
        // Step 1: Retrieve the existing PO Master object from the database
        Optional<PoMaster> existingPoMasterOptional = poRepository.findById(poMaster.getId());

        // Step 2: Check if the PO Master object exists
        if (!existingPoMasterOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PO not found");
        } else {
            // Step 3: Get the existing PO Master object
            PoMaster poMst = existingPoMasterOptional.get();

            // Step 4: Update the relevant fields of the existing PO Master object with the values from the poMaster parameter
            poMst.setDeliveryDate(poMaster.getDeliveryDate());
            poMst.setOrderDate(poMaster.getOrderDate());
            poMst.setTotal(poMaster.getTotal());
            poMst.setWorkflowDetails(poMaster.getWorkflowDetails());
            poMst.setVendorId(poMaster.getVendorId());
            poMst.setPoItemInformation(poMaster.getPoItemInformation());
            poMst.setPoAccountInformation(poMaster.getPoAccountInformation());

            // Step 5: Save the updated PO Master object back to the database
            poRepository.save(poMst);

            // Step 6: Return a ResponseEntity indicating the successful update
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Retrieves a list of ChartData objects representing approved pos.
     * @return List of ChartData.
     */
    @Override
    public List<ChartData> getApprovedPOs() {
        List<Object[]> rawData = poRepository.getApprovedPOsRawData();
        System.out.println(rawData.toString());
        List<ChartData> result = new ArrayList<>();
        for (Object[] row : rawData) {
            int month = Integer.parseInt(row[0].toString());
            int noOfPos = Integer.parseInt(row[1].toString());
            ChartData data = new ChartData(month, noOfPos);
            result.add(data);
        }
        return result;
    }

    /**
     * Downloads the Purchase Order (PO) report as a byte array based on the given PO ID.
     * @param poId The ID of the PO to download the report for.
     * @return The downloaded PO report as a byte array.
     */
    public byte[] downloadPoReport(Long poId) {
        Optional<PoMaster> existingPoMasterOptional = poRepository.findById(poId);

        if (existingPoMasterOptional.isPresent()) {
            PoMaster poMaster = existingPoMasterOptional.get();
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                generateReportToStream(poMaster, baos);
                return baos.toByteArray();
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Generates the PO report to the specified output stream.
     * @param poMaster The PO Master object used to generate the report.
     * @param outputStream The output stream to write the generated report.
     * @throws JRException If an error occurs during report generation.
     */
    private void generateReportToStream(PoMaster poMaster, ByteArrayOutputStream outputStream) throws JRException {
        // Compile the JRXML template to obtain a JasperReport object
        String jrxmlFilePath = "D:/MyProjects/Attachments/po_report/po_report.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilePath);

        //set additional data
        Long vendorId = (long) poMaster.getVendorId();
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);
        if(vendor.isPresent()){
            Vendor vendorMaster= vendor.get();
            poMaster.setVendorName(vendorMaster.getName());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDeliveryDate = dateFormat.format(poMaster.getDeliveryDate());
        String formattedOrderDate = dateFormat.format(poMaster.getOrderDate());
        poMaster.setDeliveryDateStr(formattedDeliveryDate);
        poMaster.setOrderDateStr(formattedOrderDate);


        // Create a map of parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("poNo", poMaster.getPoNo());
        parameters.put("vendorName", poMaster.getVendorName());
        parameters.put("deliveryDateStr", poMaster.getDeliveryDateStr());
        parameters.put("orderDateStr", poMaster.getOrderDateStr());

        // Create a data source with the poMaster object
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(poMaster));

        // Fill the report with data and produce a JasperPrint object
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
    }

}



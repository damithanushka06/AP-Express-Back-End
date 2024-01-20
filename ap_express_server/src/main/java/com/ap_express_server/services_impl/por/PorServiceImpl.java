package com.ap_express_server.services_impl.por;
import com.ap_express_server.common_utitlity.LocalPath;
import com.ap_express_server.common_utitlity.Status;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.po.PoItemInformationDto;
import com.ap_express_server.models.por.PORItemInformation;
import com.ap_express_server.models.por.PORMaster;
import com.ap_express_server.models.por.PorAdditionalAttachment;
import com.ap_express_server.models.por.PorDto;
import com.ap_express_server.repository.po.PoItemInformationRepository;
import com.ap_express_server.repository.por.PorAttachmentRepository;
import com.ap_express_server.repository.por.PorItemInformationRepository;
import com.ap_express_server.repository.por.PorRepository;
import com.ap_express_server.service.por.PorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PorServiceImpl implements PorService {

    private final PorRepository porRepository;

    private final PorItemInformationRepository porItemInformationRepository;

    private final PorAttachmentRepository porAttachmentRepository;

    private final PoItemInformationRepository poItemInformationRepository;

    public PorServiceImpl(PorRepository porRepository, PorItemInformationRepository porItemInformationRepository,
                          PorAttachmentRepository porAttachmentRepository, PoItemInformationRepository poItemInformationRepository) {
        this.porRepository = porRepository;
        this.porItemInformationRepository = porItemInformationRepository;
        this.porAttachmentRepository = porAttachmentRepository;
        this.poItemInformationRepository = poItemInformationRepository;
    }

    /**
     * Creates a new Purchase Order Receipt (POR) by saving the provided POR Master object.
     * @param porMaster The POR Master object to be created.
     * @return The created POR Master object.
     */
    @Override
    public PORMaster createPor(PORMaster porMaster) throws IOException {
        //Set created by and created date
        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        Date date = new Date();
        porMaster.setCreatedBy(createdBy);
        porMaster.setCreatedDate(date);

        porRepository.save(porMaster);

        List<PORItemInformation> porItemInformationList = porMaster.getPorItemInformationList();
        float totalAmount = 0;
        for (PORItemInformation porItemInformation : porItemInformationList) {
            if(porItemInformation.getLineAmount() != 0){
                float lineAmount = porItemInformation.getReceivedQty() * porItemInformation.getLineAmount();
                totalAmount += lineAmount;
                porItemInformation.setLineAmount(lineAmount);
                porItemInformation.setPorId(Math.toIntExact(porMaster.getId()));
                porItemInformation.setPoId(porMaster.getPoId());
                porItemInformationRepository.save(porItemInformation);
            }

        }
        porMaster.setTotalAmount(totalAmount);

        List<MultipartFile> files = porMaster.getFiles();
        for (MultipartFile file : files) {
            // Perform file operations
            String fileName = file.getOriginalFilename();
            String filePath = LocalPath.POR_FILE_UPLOAD_DER + fileName;

            // Save the file to the destination folder
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // Create and save the PorAdditionalAttachment entity
            PorAdditionalAttachment attachment = new PorAdditionalAttachment();
            attachment.setPoId(porMaster.getId());
            attachment.setFilePath(filePath);
            attachment.setStatus(Status.STATUS_ACTIVE);
            porAttachmentRepository.save(attachment);
        }
        return this.porRepository.save(porMaster);
    }


    /**
     * Retrieves all pors
     * @return porDto list
     */
    @Override
    public List<PorDto> getAllPORs() {
        return porRepository.getAllPOR();
    }

    /**
     * Delete a por record
     * @param porId to por master id
     */
    @Override
    public void deletePORDetailById(Integer porId) {
        Optional<PORMaster> porMaster = porRepository.findById(porId);
        if(porMaster.isPresent()){
            PORMaster por = porMaster.get();
            por.setStatus(Status.STATUS_DELETED);
            porRepository.save(por);
        }
    }

    /**
     * Retrieves the list of vendor-related PO dropdown options for the given vendor ID.
     * @param vendorId The ID of the vendor.
     * @return The list of vendor-related PO dropdown options.
     */
    @Override
    public List<DropDownDto> getVendorRelatedPoList(Integer vendorId) {
        // Call the porRepository's getVendorRelatedPoList method to retrieve vendor-related PO dropdown options

        // Return the list of vendor-related PO dropdown options
        return porRepository.getVendorRelatedPoList(vendorId);
    }

    /**
     * Retrieves the list of PORItemInformation related to a specific PO ID.
     *
     * @param poId The ID of the PO.
     * @return The list of PORItemInformation related to the given PO ID.
     */
    @Override
    public List<PoItemInformationDto> getPORelatedItemList(Integer poId) {
        return poItemInformationRepository.findByPoId(poId);
    }
}

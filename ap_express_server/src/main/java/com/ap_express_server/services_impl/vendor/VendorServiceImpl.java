package com.ap_express_server.services_impl.vendor;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.vendor.Vendor;
import com.ap_express_server.models.vendor.VendorDto;
import com.ap_express_server.repository.vendor.VendorRepository;
import com.ap_express_server.service.vendor.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    /**
     * this method used for save vendor details in vendor table
     * @param vendor to Vendor reference
     * @return to save function of repo
     */
    @Override
    public Vendor createVendor(Vendor vendor) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        vendor.setCreatedBy(user.getName());
        vendor.setCreatedAt(LocalDate.now());
        return vendorRepository.save(vendor);
    }

    /**
     * this method used for update vendor
     * @param vendor to Vendor reference
     * @return to save function of repo
     */
    @Override
    public Vendor updateVendor(Vendor vendor) {
        Optional<Vendor> existVendor = vendorRepository.findById(vendor.getId());
        if(existVendor.isPresent()){
            Vendor priviousVendor = existVendor.get();
            priviousVendor.setName(vendor.getName());
            priviousVendor.setAddress(vendor.getAddress());
            priviousVendor.setEmail(vendor.getEmail());
            priviousVendor.setContactNo(vendor.getContactNo());
            priviousVendor.setNotes(vendor.getNotes());
            priviousVendor.setPaymentTermId(vendor.getPaymentTermId());
            priviousVendor.setPaymentMethodId(vendor.getPaymentMethodId());
            return vendorRepository.save(priviousVendor);
        } else {
            throw new NoSuchElementException("Vendor not found with id: " + vendor.getId());
        }
    }

    /**
     * this method used for get all vendors
     * @return to save function of repo
     */
    @Override
    public List<VendorDto> getAllVendorList() {
        return vendorRepository.getAllVendorList();
    }

    /**
     * this service used to get vendor by id
     *
     * @return to the service impl
     */

    @Override
    public Optional<Vendor> getVendorById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if(vendor.isPresent()){
            return vendorRepository.findById(id);
        } else {
            return Optional.empty();
        }
    }

    /**
     * this service used to delete vendor by id
     * @return to the repo
     */
    @Override
    public ResponseEntity<?> deleteVendorById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if(vendor.isPresent()){
            vendorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public List<DropDownDto> getVendorDropDownList() {
        return vendorRepository.getVendorDropDownList();
    }
}

package com.ap_express_server.services_impl.payment;
import com.ap_express_server.common_utitlity.Status;
import com.ap_express_server.models.bill.BillDto;
import com.ap_express_server.models.bill.BillMaster;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.payment.Payment;
import com.ap_express_server.models.payment.PaymentDto;
import com.ap_express_server.repository.bill.BillRepository;
import com.ap_express_server.repository.payment.PaymentMethodRepository;
import com.ap_express_server.repository.payment.PaymentRepository;
import com.ap_express_server.repository.payment.PaymentTermRepository;
import com.ap_express_server.service.payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentTermRepository paymentTermRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentRepository paymentRepository;

    private final BillRepository billRepository;

    public PaymentServiceImpl(PaymentTermRepository paymentTermRepository, PaymentMethodRepository
            paymentMethodRepository, PaymentRepository paymentRepository, BillRepository billRepository) {
        this.paymentTermRepository = paymentTermRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentRepository = paymentRepository;
        this.billRepository = billRepository;
    }

    /**
     * Retrieves a list of payment terms for populating a dropdown.
     *
     * @return A list of DropDownDto objects representing payment terms.
     */
    @Override
    public List<DropDownDto> getPaymentTermList() {
        return paymentTermRepository.getPaymentTermList();
    }

    /**
     * Retrieves a list of payment methods for populating a dropdown.
     *
     * @return A list of DropDownDto objects representing payment methods.
     */
    @Override
    public List<DropDownDto> getPaymentMethodList() {
        return paymentMethodRepository.getPaymentMethodList();
    }

    @Override
    public ResponseEntity<Object> createPayment(Payment payment) {
        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        Date cretedDate = new Date();
        payment.setCreatedBy(createdBy);
        payment.setCreatedDate(cretedDate);
        Optional <BillMaster> billMaster = this.billRepository.getBillDetailById(payment.getBillId());
        if(billMaster.isPresent()){
            BillMaster bill = billMaster.get();
            bill.setBillBalance(payment.getBillBalance());
            bill.setPaymentAmount(payment.getAmount());
            billRepository.save(bill);
        }
        payment.setStatus(Status.STATUS_ACTIVE);
        paymentRepository.save(payment);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves the bill balance information for the specified bill ID.
     *
     * @param billId The ID of the bill to retrieve the balance information for.
     * @return An Optional containing the BillDto object with the payment amount and bill balance, or empty if not found.
     */
    @Override
    public Optional<BillDto> getBillBalance(Integer billId) {
        return this.billRepository.getBillBalance(billId);
    }

    /**
     * Retrieves all payment records from payment table
     * @return payment list
     */
    @Override
    public List<PaymentDto> getAllPaymentList() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public ResponseEntity<?> voidPayment(Integer paymentId) {
        Optional <Payment> payment = paymentRepository.findById(paymentId);
        if(payment.isPresent()){
            Payment pay = payment.get();
            pay.setStatus(Status.STATUS_DELETED);
            paymentRepository.save(pay);
        }
        return ResponseEntity.ok().build();
    }


}

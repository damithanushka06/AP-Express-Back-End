package com.ap_express_server.service.payment;
import com.ap_express_server.models.bill.BillDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.payment.Payment;
import com.ap_express_server.models.payment.PaymentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PaymentService {

    /**
     * Retrieves a list of payment terms for populating a dropdown.
     *
     * @return A list of DropDownDto objects representing payment terms.
     */
    List<DropDownDto> getPaymentTermList();

    /**
     * Retrieves a list of payment methods for populating a dropdown.
     *
     * @return A list of DropDownDto objects representing payment methods.
     */
    List<DropDownDto> getPaymentMethodList();

    /**
     * Creates a payment using the provided Payment object.
     *
     * @param payment The Payment object containing payment details.
     * @return A ResponseEntity representing the HTTP response for the payment creation.
     */
    ResponseEntity<Object> createPayment(Payment payment);

    /**
     * Getting bill balance amount of given bill id
     *
     * @param billId to bill master id
     * @return Optionals content if is not exist given id returning empty content
     */
    Optional<BillDto> getBillBalance(Integer billId);

    /**
     * Getting all payment list
     *
     * @return all payments
     */
    List<PaymentDto> getAllPaymentList();

    /**
     * Voids a payment with the specified payment ID.
     *
     * @param paymentId The ID of the payment to void.
     * @return A ResponseEntity representing the HTTP response for the void operation.
     */
    ResponseEntity<?> voidPayment(Integer paymentId);
}

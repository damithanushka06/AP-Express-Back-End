package com.ap_express_server.controllers.payment;
import com.ap_express_server.models.bill.BillDto;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.payment.Payment;
import com.ap_express_server.models.payment.PaymentDto;
import com.ap_express_server.service.payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * get payment term dropdown
     * @return to the service
     */
    @GetMapping("payment/get_payment_term_list")
    private List<DropDownDto> getPaymentTermList(){
        return paymentService.getPaymentTermList();
    }

    /**
     * get payment method dropdown
     * @return to the service
     */
    @GetMapping("payment/get_payment_method_list")
    private List<DropDownDto> getPaymentMethodList(){
        return paymentService.getPaymentMethodList();
    }

    /**
     * Endpoint for creating a payment.
     *
     * @param payment The Payment object containing payment details.
     * @return A ResponseEntity representing the HTTP response for the payment creation.
     * @throws IOException If an error occurs during payment processing.
     */
    @PostMapping("payment/create_payment")
    public ResponseEntity<Object> createPayment(@RequestBody Payment payment) throws IOException {
        return paymentService.createPayment(payment);
    }


    /**
     * Endpoint for retrieving the bill balance amount for a given bill ID.
     * @param billId The ID of the bill master.
     * @return An Optional containing the BillDto object with the payment amount and bill balance, or empty if not found.
     */
    @GetMapping("payment/get_bill_balance")
    private Optional<BillDto> getBillBalance(@RequestParam Long billId) {
        return paymentService.getBillBalance(billId);
    }

    /**
     * This method can be used to get payment list
     * @return all payment list
     */
    @GetMapping("payment/getAll")
    private List<PaymentDto> getPaymentList(){
        return paymentService.getAllPaymentList();
    }

    /**
     * this method can be used to void selected payment
     * @param paymentId to payment id
     * @return ResponseEntity to contains http status
     */
    @DeleteMapping("payment/void_payment")
    private ResponseEntity<?> voidPayment(@RequestParam Long paymentId){
        return paymentService.voidPayment(paymentId);
    }
}

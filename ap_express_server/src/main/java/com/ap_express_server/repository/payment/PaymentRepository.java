package com.ap_express_server.repository.payment;
import com.ap_express_server.models.payment.Payment;
import com.ap_express_server.models.payment.PaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Retrieves a list of PaymentDto objects containing payment details, vendor information, and bill information.
     * The query joins the Payment, Vendor, and BillMaster entities to gather the necessary data.
     *
     * @return A List of PaymentDto objects representing the payments with associated vendor and bill information.
     */
    @Query("select new com.ap_express_server.models.payment.PaymentDto(payment.id," +
            "payment.vendorId, payment.billId, DATE(payment.paymentDate), DATE(payment.createdDate)," +
            "payment.createdBy, payment.status, payment.referenceNo,payment.notes, payment.billBalance," +
            "payment.amount, ven.name, bill.billNo, case payment.status WHEN 'A' THEN 'Active'" +
            "            WHEN 'D' THEN 'Void'"+
            "            ELSE 'Unknown'END) from Payment payment join Vendor ven on ven.id = payment.vendorId " +
            "join BillMaster bill on bill.id = payment.billId")
    List<PaymentDto> getAllPayments();

}

package com.ap_express_server.repository.payment;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.payment.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {
    @Query("SELECT new com.ap_express_server.models.dropdown.DropDownDto(pTerm.id, pTerm.name) FROM PaymentTerm pTerm")
    List<DropDownDto> getPaymentTermList();
}

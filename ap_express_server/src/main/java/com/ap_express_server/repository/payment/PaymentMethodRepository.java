package com.ap_express_server.repository.payment;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.payment.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    @Query("SELECT new com.ap_express_server.models.dropdown.DropDownDto(pMethod.id, pMethod.name) FROM PaymentMethod pMethod")
    List<DropDownDto> getPaymentMethodList();
}

package mapper;

import dto.GuaranteeComponentsDto;
import dto.PaymentDto;
import model.GuaranteeComponents;
import model.Payment;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper {

    PaymentDto paymentToPaymentDto(Payment payment);

    Payment paymentDtoToPayment(PaymentDto paymentDto);
}

package mapper;

import dto.PaymentDto;
import dto.ProducerDto;
import model.Payment;
import model.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProducerMapper {

    @Mappings({
            @Mapping(source = "country", target = "countryDto"),
            @Mapping(source = "trade", target = "tradeDto")
    })
    ProducerDto producerToProducerDto(Producer producer);

    @Mappings({
            @Mapping(source = "countryDto", target = "country"),
            @Mapping(source = "tradeDto", target = "trade")
    })
    Producer producerDtoToProducer(ProducerDto producerDto);

}

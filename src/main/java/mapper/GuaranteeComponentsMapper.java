package mapper;

import dto.CustomerOrderDto;
import dto.GuaranteeComponentsDto;
import model.CustomerOrder;
import model.GuaranteeComponents;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface GuaranteeComponentsMapper {

    GuaranteeComponentsDto guaranteeComponentsToGuaranteeComponentsDto(GuaranteeComponents guaranteeComponents);

    GuaranteeComponents guaranteeComponentsDtoToGuaranteeComponents(GuaranteeComponentsDto guaranteeComponentsDto);

    //GuaranteeComponentsDto
}

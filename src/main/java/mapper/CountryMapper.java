package mapper;

import dto.CountryDto;
import model.Country;
import org.mapstruct.*;

@Mapper
public interface CountryMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    CountryDto countryToCountryDto(Country country);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    Country countryDtoToCountry(CountryDto countryDto);

}

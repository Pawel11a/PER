package service;

import dto.CategoryDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import map.ModelMappers;
import mapper.CategoryMapper;
import model.Category;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.mapstruct.factory.Mappers;
import repositories.CategoryRepository;
import repositories.impl.CategoryRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepositoryImpl categoryRepository;

    public void addCategory(CategoryDto categoryDto) {

        if (categoryDto == null) {
            throw new MyException("CategoryService - method addCategory categoryDto is null");
        } else {
            if (StringUtils.isEmpty(categoryDto.getName())) {
                throw new MyException("CategoryService - method addCategory name is empty");
            } else {
                if (!ValidateService.nameIsCorrect(categoryDto.getName())) {
                    throw new MyException("CategoryService - method addCategory, validCaseAndSpaces name is incorrect : " + categoryDto.getName() );
                }
            }
        }

        Category findByName = categoryRepository.findByName(categoryDto.getName());

        if (findByName == null) {

//            var mapperCategory = Mappers.getMapper(CategoryMapper.class);
//            Category category = mapperCategory.categoryDtoToCategory(categoryDto);
            Category category = ModelMappers.fromCountryDtoToCountry(categoryDto);


            categoryRepository.saveOrUpdate(category);
        } else {
            throw new MyException("CategoryService - method addCategory name : " + categoryDto.getName() + " is exists" );
        }


    }
}

/*
@RequiredArgsConstructor
public class AppService {

    private final CountryRepository countryRepository;
    private final CompanyRepository companyRepository;

    public void addCompanyWithCountry(CompanyDto companyDto) {

        if ( companyDto == null ) {
            throw new AppException("COMPANY OBJECT IS NULL");
        }

        if ( companyDto.getCountryDto() == null ) {
            throw new AppException("COUNTRY OBJECT IS NULL");
        }

        // COUNTRY
        // 1. kiedy id oraz name sa null to dajemy wyjatek
        // 2. kiedy id nie jest null to pobieramy po id i jak nie ma
        //    takiego kraju to wyjatek
        // 3. kiedy nie ma id ale jest name to pobieramy po name
        //    a jak nie ma to dodajemy kraj o takiej nazwie

        CountryDto countryDto = companyDto.getCountryDto();
        if ( countryDto.getId() == null && countryDto.getName() == null ) {
            throw new AppException("COUNTRY WITHOUT ID AND NAME");
        }

        Country country = null;
        if (countryDto.getId() != null) {
            country = countryRepository
                    .findById(countryDto.getId())
                    .orElse(null);
        }

        if (country == null) {
            country = countryRepository
                    .findByName(countryDto.getName())
                    .orElse(null);

            if (countryDto.getCapital() != null) {
                countryRepository.saveOrUpdate(ModelMappers.fromCountryDtoToCountry(countryDto));
                country = countryRepository
                        .findByName(countryDto.getName())
                        .orElseThrow(() -> new AppException("COUNTRY IS NOT CORRECT"));
            }
        }

        if (country == null) {
            throw new AppException("COUNTRY IS NOT CORRECT");
        }

        Company company = ModelMappers.fromCompanyDtoToCompany(companyDto);
        company.setCountry(country);
        companyRepository.saveOrUpdate(company);

    }

    public List<CompanyDto> findAllCompaniesFromCountry(String countryName) {

        if (countryName == null) {
            throw new AppException("COUNTRY NAME IS NULL");
        }

        return companyRepository.findAll()
                .stream()
                .filter(company -> company.getCountry().getName().equals(countryName))
                .map(ModelMappers::fromCompanyToCompanyDto)
                .collect(Collectors.toList());

    }

    public List<CompanyDto> findAllCompaniesFromCountryJpql(String countryName) {

        return companyRepository
                .findAllWithCountryName(countryName)
                .stream()
                .map(ModelMappers::fromCompanyToCompanyDto)
                .collect(Collectors.toList());

    }

    public List<TmpDto> findBy(){

        return companyRepository.findByCriteria();
    }

}
 */
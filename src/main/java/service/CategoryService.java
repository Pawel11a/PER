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
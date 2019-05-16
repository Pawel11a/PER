package mapper;

import dto.CategoryDto;
import model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ValueMappings;

@Mapper
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    CategoryDto categoryToCategoryDto(Category category);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    Category categoryDtoToCategory(CategoryDto categoryDto);

//    @ValueMappings({
//            @ValueMappings(source = "", target = "")
//    })
//    CategoryDtoType categoryTypeToCategoryDtoType(CategoryType categoryType);
}

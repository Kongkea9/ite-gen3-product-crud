package istad.co.product_api_simple_demo.mapper;


import istad.co.product_api_simple_demo.dto.category.CategoryRequest;
import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category mapFromCategoryResponseToCategory(CategoryRequest categoryRequest);
    CategoryResponse mapFromCategoryToCategoryResponse(Category category);

}

package istad.co.product_api_simple_demo.service.category;

import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.dto.category.CategoryRequest;
import istad.co.product_api_simple_demo.dto.category.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> finAllCategories();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest categoryRequest);
    boolean deleteCategory(Integer id);

}

package istad.co.product_api_simple_demo.service.category;

import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.dto.category.CategoryRequest;
import istad.co.product_api_simple_demo.dto.category.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Page<CategoryResponse> finAllCategories(Pageable pageable);
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest categoryRequest);
    void deleteCategory(Integer id);
    CategoryResponse findCategoryByName(String name);
    void softDeleteCategory(Integer id);

}

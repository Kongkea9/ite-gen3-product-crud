package istad.co.product_api_simple_demo.restControllers;


import istad.co.product_api_simple_demo.dto.category.CategoryRequest;
import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.dto.category.UpdateCategoryRequest;
import istad.co.product_api_simple_demo.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.finAllCategories();
    }


    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return categoryService.findCategoryById(id);
    }


    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }


    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@Valid @PathVariable Integer id, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return categoryService.updateCategory(id, updateCategoryRequest);
    }

    @DeleteMapping
    public void deleteCategory(@PathVariable Integer id){
           categoryService.deleteCategory(id);
    }



}

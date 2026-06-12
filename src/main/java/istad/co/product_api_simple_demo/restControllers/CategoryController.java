package istad.co.product_api_simple_demo.restControllers;


import istad.co.product_api_simple_demo.dto.category.CategoryRequest;
import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.dto.category.UpdateCategoryRequest;
import istad.co.product_api_simple_demo.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Page<CategoryResponse> getAllCategories(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return categoryService.finAllCategories(pageable);
    }


    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return categoryService.findCategoryById(id);
    }


    @GetMapping("/name/{name}")
    public CategoryResponse getCategoryByName(@PathVariable String name){
        return categoryService.findCategoryByName(name);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }


    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest, @PathVariable Integer id){
        return categoryService.updateCategory(id, updateCategoryRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id){

        categoryService.deleteCategory(id);

    }


    @PatchMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteCategory(@PathVariable Integer id){
        categoryService.softDeleteCategory(id);
    }





}

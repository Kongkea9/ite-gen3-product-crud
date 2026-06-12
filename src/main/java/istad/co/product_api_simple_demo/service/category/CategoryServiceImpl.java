package istad.co.product_api_simple_demo.service.category;

import istad.co.product_api_simple_demo.advisor.ResourceAlreadyExistsException;
import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.dto.category.CategoryRequest;
import istad.co.product_api_simple_demo.dto.category.UpdateCategoryRequest;
import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.entity.Category;

import istad.co.product_api_simple_demo.mapper.CategoryMapper;
import istad.co.product_api_simple_demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
//
//    @Override
//    public Page<ProductResponse> findAllProducts(Pageable pageable) {
//        return productRepository.findAll(pageable).map(productMapper::mapToResponse);
//    }


    @Override
    public Page<CategoryResponse> finAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::mapFromCategoryToCategoryResponse);
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        return categoryMapper.mapFromCategoryToCategoryResponse(categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found.")));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {


        if(categoryRepository.existsCategoryByName(categoryRequest.name())){
            throw new ResourceAlreadyExistsException("Category with name "+ categoryRequest.name()+ " already exists");
        }


        Category category = categoryMapper.mapFromCategoryResponseToCategory(categoryRequest);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        category.setIsDeleted(false);


        return categoryMapper.mapFromCategoryToCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {

        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found."));

        if (category == null) {
            throw new RuntimeException("Category with id " + id + " not found");
        }

        if (request.name() != null && !request.name().isBlank()) {
            category.setName(request.name());
        }

        if (request.description() != null && !request.description().isBlank()) {
            category.setDescription(request.description());
        }

        category.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.mapFromCategoryToCategoryResponse(
                categoryRepository.save(category)
        );
    }
    @Override
    public void deleteCategory(Integer id) {


        if(!categoryRepository.existsById(id)){
            throw new NoSuchElementException("Category with id " + id + " does not exist");
        }

        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public void softDeleteCategory(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found."));

        if (category.getIsDeleted() != null) {
            category.setIsDeleted(true);
        }

        categoryRepository.save(category);
    }
}

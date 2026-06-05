package istad.co.product_api_simple_demo.service.category;

import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.dto.category.CategoryRequest;
import istad.co.product_api_simple_demo.dto.category.UpdateCategoryRequest;
import istad.co.product_api_simple_demo.entity.Category;
import istad.co.product_api_simple_demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private static Integer nextId = 1000;

    CategoryResponse mapFromCategoryToCategoryResponse(Category category){
                     return new CategoryResponse(
                             category.getName(),
                             category.getDescription(),
                             category.getStatus(),
                             category.getCreatedAt(),
                             category.getUpdatedAt()
                     );
    }

    Category mapFromCategoryResponseToCategory(CategoryRequest categoryRequest){

        return Category
                .builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .status(categoryRequest.status())
                .build();

    }



    @Override
    public List<CategoryResponse> finAllCategories() {
        return categoryRepository.getAllCategories().stream()
                .map(this::mapFromCategoryToCategoryResponse).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        return mapFromCategoryToCategoryResponse(categoryRepository.getCategoryById(id));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = mapFromCategoryResponseToCategory(categoryRequest);
        category.setId(nextId++);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());


        return mapFromCategoryToCategoryResponse(categoryRepository.createCategory(category));
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest categoryRequest) {

        var category = categoryRepository.getCategoryById(id);
        if(category == null){
            log.info("Category with id {} not found", id);
        }

        if(categoryRequest.name() != null){
            category.setName(categoryRequest.name());
        }

        if(categoryRequest.description() != null){
            category.setDescription(categoryRequest.description());
        }

        if(categoryRequest.status() != null){
            category.setStatus(categoryRequest.status());
        }

        category.setUpdatedAt(LocalDateTime.now());

        return mapFromCategoryToCategoryResponse(categoryRepository.updateCategory(category));
    }

    @Override
    public boolean deleteCategory(Integer id) {
        return categoryRepository.deleteCategoryById(id);
    }
}

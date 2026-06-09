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
                             category.getId(),
                             category.getName(),
                             category.getDescription(),
                             category.getIsActive(),
                             category.getCreatedAt(),
                             category.getUpdatedAt()
                     );
    }

    Category mapFromCategoryResponseToCategory(CategoryRequest categoryRequest){

        return Category
                .builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .isActive(categoryRequest.isActive())
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
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {

        var category = categoryRepository.getCategoryById(id);

        if (category == null) {
            throw new RuntimeException("Category with id " + id + " not found");
        }

        if (request.name() != null && !request.name().isBlank()) {
            category.setName(request.name());
        }

        if (request.description() != null && !request.description().isBlank()) {
            category.setDescription(request.description());
        }

        if (request.isActive() != null) {
            category.setIsActive(request.isActive());
        }

        category.setUpdatedAt(LocalDateTime.now());

        return mapFromCategoryToCategoryResponse(
                categoryRepository.updateCategory(category)
        );
    }
    @Override
    public boolean deleteCategory(Integer id) {
        return categoryRepository.deleteCategoryById(id);
    }
}

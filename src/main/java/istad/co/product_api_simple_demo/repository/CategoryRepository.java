package istad.co.product_api_simple_demo.repository;


import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // derived query

    boolean existsCategoryByName(String name);

    CategoryResponse findCategoryByName(String name);


}

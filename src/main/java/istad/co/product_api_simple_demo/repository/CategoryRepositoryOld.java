package istad.co.product_api_simple_demo.repository;


import istad.co.product_api_simple_demo.entity.Category;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Repository
public class CategoryRepositoryOld {

    private List<Category> categories = new ArrayList<>(
//        List.of(new Category(1, "Drink","For drink",true, LocalDateTime.now(), LocalDateTime.now()))
    );


    public List<Category> getAllCategories(){
        return categories;
    }

    public Category getCategoryById(Integer id){
        return categories.stream().filter(e-> Objects.equals(e.getId(), id))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found"));
    }

    public Category createCategory(Category category){

           categories.add(category);
           return category;
    }

    public boolean deleteCategoryById(Integer id){
           return categories.removeIf(category -> Objects.equals(category.getId(), id));
    }

    public Category updateCategory(Category category) {

        for (int i = 0; i < categories.size(); i++) {
            if (Objects.equals(categories.get(i).getId(), category.getId())) {
                categories.set(i, category);
                return category;
            }
        }

        return null;
    }




}

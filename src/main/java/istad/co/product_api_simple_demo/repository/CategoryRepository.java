package istad.co.product_api_simple_demo.repository;


import istad.co.product_api_simple_demo.entity.Category;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CategoryRepository {

    private List<Category> categories = new ArrayList<>(
        List.of(new Category(1, "Drink","For drink","active", LocalDateTime.now(), LocalDateTime.now()))
    );


    public List<Category> getAllCategories(){
        return categories;
    }

    public Category getCategoryById(Integer id){
        return categories.stream().filter(e->e.getId() == id)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found"));
    }

    public Category createCategory(Category category){

           categories.add(category);
           return category;
    }

    public boolean deleteCategoryById(Integer id){
           return categories.removeIf(category -> category.getId() == id);
    }

    public Category updateCategory(Category category){

           return (Category) categories.stream().filter(c -> c.getId() == category.getId())
                   .map(i -> {
                       categories.set(i.getId(), category);
                       return category;
                   });

    }




}

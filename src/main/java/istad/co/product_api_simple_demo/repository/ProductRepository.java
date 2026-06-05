package istad.co.product_api_simple_demo.repository;


import istad.co.product_api_simple_demo.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Slf4j
public class ProductRepository {


    private List<Product> products = new ArrayList<>(
            List.of(new Product(1, "Coca", "Nice When Cool", 4.4f,1),
                    new Product(2, "Fanta", "Fanta Drink", 3.4f,3),
                    new Product(3, "String", "Unlimited Sweetness", 6.1f,2)
            ));



    public List<Product> getAllProducts(){
        return products;
    }



    public Product createProduct(Product product){
        products.add(product);
        return product;
    }


    public Product findProductById(Integer id){
        return products.stream().filter(p->p.getId() == id)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found"));  //NoSuchElementException
    }


    public boolean deleteProductById(Integer id){
        return products.removeIf(product -> product.getId() == id);
    }


    public Product updateProduct(Product updateProduct){
        for(int i = 0 ;i < products.size(); i++){
            var product = products.get(i);
            if(product.getId() == updateProduct.getId()){
                products.set(i, updateProduct);
                return updateProduct;
            }
        }

        return null;
    }
}

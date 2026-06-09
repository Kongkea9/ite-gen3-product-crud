package istad.co.product_api_simple_demo.repository;


import istad.co.product_api_simple_demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    boolean removeProductById(Integer id);
}

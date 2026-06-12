package istad.co.product_api_simple_demo.repository;


import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    boolean removeProductById(Integer id);

        Page<Product> searchProductByNameContainsIgnoreCase(String keyword, Pageable pageable);
}

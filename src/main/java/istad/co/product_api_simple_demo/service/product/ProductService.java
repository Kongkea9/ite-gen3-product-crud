package istad.co.product_api_simple_demo.service.product;

import istad.co.product_api_simple_demo.dto.product.ProductRequest;
import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.dto.product.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ProductService {

    ProductResponse createProduct(ProductRequest product);
    List<ProductResponse> findAllProducts();
    Page<ProductResponse> findAllProducts(Pageable pageable);
    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct(Integer id, UpdateProductRequest product);
    void deleteProduct(int id);
    Page<ProductResponse> searchProductByKeyword(String keyword, Pageable pageable);

}

package istad.co.product_api_simple_demo.service.product;

import istad.co.product_api_simple_demo.dto.product.ProductRequest;
import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.dto.product.UpdateProductRequest;

import java.util.List;
public interface ProductService {

    ProductResponse createProduct(ProductRequest product);
    List<ProductResponse> findAllProducts();
    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct(Integer id, UpdateProductRequest product);
    boolean deleteProduct(int id);

}

package istad.co.product_api_simple_demo.restControllers;


import istad.co.product_api_simple_demo.dto.product.ProductRequest;
import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.dto.product.UpdateProductRequest;
import istad.co.product_api_simple_demo.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;


    @GetMapping
    public List<ProductResponse> getAllProducts(){
       return  productService.findAllProducts();
    }

    @GetMapping("{id}")
    public ProductResponse getUserById(@PathVariable Integer id){
           return productService.findProductById(id);
    }

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }

    @PatchMapping("{id}")
    public ProductResponse updateProduct(@Valid @PathVariable Integer id,
                                         @RequestBody UpdateProductRequest productRequest){
        return productService.updateProduct(id, productRequest);
    }
}

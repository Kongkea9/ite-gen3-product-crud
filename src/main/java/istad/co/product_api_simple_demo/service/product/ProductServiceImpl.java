package istad.co.product_api_simple_demo.service.product;

import istad.co.product_api_simple_demo.dto.category.CategoryResponse;
import istad.co.product_api_simple_demo.dto.product.ProductRequest;
import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.dto.product.UpdateProductRequest;
import istad.co.product_api_simple_demo.entity.Category;
import istad.co.product_api_simple_demo.entity.Product;
import istad.co.product_api_simple_demo.mapper.ProductMapper;
import istad.co.product_api_simple_demo.repository.CategoryRepository;
import istad.co.product_api_simple_demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new NoSuchElementException("Category with id " + productRequest.categoryId() + " does not exist."));



        Product product = productMapper.mapToEntity(productRequest);
        product.setCategory(category);

        product.setUserId(1);

        return productMapper.mapToResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> findAllProducts() {

        return productRepository.findAll().stream()
                .map(productMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::mapToResponse);
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        var product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product with id = " + id + "not found"));
        if(product == null){
            log.info("Product with id {} not found", id);
            return null;
        }

        return productMapper.mapToResponse(product);

    }



    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest product) {


        var existProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product with id = " + id + "not found"));
        if(existProduct ==  null){
            log.info("Product with id {} not found", id);
        }

        if(product.name() != null){
            existProduct.setName(product.name());

        }

        if(product.price() != null){
            existProduct.setPrice(product.price());
        }

        if(product.description() != null){
            existProduct.setDescription(product.description());
        }

        productRepository.save(existProduct);
        return productMapper.mapToResponse(existProduct);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponse> searchProductByKeyword(String keyword, Pageable pageable) {
        return productRepository.searchProductByNameContainsIgnoreCase(keyword, pageable).map(productMapper::mapToResponse);
    }


}

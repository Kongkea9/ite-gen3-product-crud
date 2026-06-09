package istad.co.product_api_simple_demo.service.product;

import istad.co.product_api_simple_demo.dto.product.ProductRequest;
import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.dto.product.UpdateProductRequest;
import istad.co.product_api_simple_demo.entity.Product;
import istad.co.product_api_simple_demo.repository.ProductRepository;
import istad.co.product_api_simple_demo.repository.ProductRepositoryOld;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

//    private final ProductRepositoryOld productRepository;


    private final ProductRepository productRepository;
//    private static Integer nextId = 1000;


    private static ProductResponse mapToResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }


    private Product mapToEntity(ProductRequest productRequest){

        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();


    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapToEntity(productRequest);

        product.setUserId(1);

        return mapToResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> findAllProducts() {

        return productRepository.findAll().stream()
                .map(ProductServiceImpl::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        var product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product with id = " + id + "not found"));
        if(product == null){
            log.info("Product with id {} not found", id);
            return null;
        }

        return mapToResponse(product);

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
        return mapToResponse(existProduct);
    }

    @Override
    public boolean deleteProduct(int id) {
        return productRepository.removeProductById(id);
    }

}

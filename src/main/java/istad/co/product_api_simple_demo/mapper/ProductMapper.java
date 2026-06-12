package istad.co.product_api_simple_demo.mapper;


import istad.co.product_api_simple_demo.dto.product.ProductRequest;
import istad.co.product_api_simple_demo.dto.product.ProductResponse;
import istad.co.product_api_simple_demo.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper{

    ProductResponse mapToResponse(Product product);
    Product mapToEntity(ProductRequest productRequest);

}

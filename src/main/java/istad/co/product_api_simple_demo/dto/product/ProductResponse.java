package istad.co.product_api_simple_demo.dto.product;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Float price
) {
}

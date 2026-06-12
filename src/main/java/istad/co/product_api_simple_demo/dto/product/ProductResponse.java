package istad.co.product_api_simple_demo.dto.product;

import istad.co.product_api_simple_demo.dto.category.CategoryResponse;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Float price,
        CategoryResponse category
) {
}

package istad.co.product_api_simple_demo.dto.category;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryRequest(

        @NotNull(message = "Name is required")
        @Max(255)
        String name,


        @NotNull(message = "Description is required")
        @Max(255)
        String description,

        @NotNull(message = "Status is required")
        @Max(255)
        String status
) {
}

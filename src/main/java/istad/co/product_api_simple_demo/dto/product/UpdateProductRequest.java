package istad.co.product_api_simple_demo.dto.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateProductRequest(
        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "description is required")
        String description,

        @NotNull(message = "price is required")
        @Positive(message = "price must be positive")
        Float price
) { }

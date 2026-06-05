package istad.co.product_api_simple_demo.dto.category;

import java.time.LocalDateTime;

public record CategoryResponse(
        String name,
        String description,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt


) {
}

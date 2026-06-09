package istad.co.product_api_simple_demo.dto.category;

import java.time.LocalDateTime;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt


) {
}

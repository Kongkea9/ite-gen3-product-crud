package istad.co.product_api_simple_demo.dto;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse<T>(

        LocalDateTime timeStamp,
        String message,
        T errors,
        Integer status

) {
}









































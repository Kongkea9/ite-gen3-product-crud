package istad.co.product_api_simple_demo.entity;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Category {

    private Integer id;
    private String name;
    private String description;
    private  Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

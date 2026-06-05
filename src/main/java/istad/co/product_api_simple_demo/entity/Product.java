package istad.co.product_api_simple_demo.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product {

    private Integer id;
    private String name;
    private  String description;
    private Float price;
    private int userId; // user that create the product

}

package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private int productId;
    private double price;
    private String product_category;
    private String name;
   private String image;
   private String description;
   private String available;
   private int quantity;




}
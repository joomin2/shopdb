package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private int productid;
    private String productName;  // product_name
    private int productPrice;    // product_price
    private String productImg;    // product_img
    private int cateno;          // cateno
    private java.sql.Date productDate; // product_date
}

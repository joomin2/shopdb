package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    private int odetail_id;     // odetail_id
    private int price;         // price
    private int order_id;       // order_id
    private int product_id;     // product_id
    private int odetail_quantity;  // odetail_quantity
}

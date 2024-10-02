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
    private int odetailId;     // odetail_id
    private int price;         // price
    private int orderId;       // order_id
    private int productId;     // product_id
    private int odetailQuantity;  // odetail_quantity
}

package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    private int orderId;       // order_id
    private java.sql.Date orderDate;  // order_date
    private String status;     // status
    private int totalPrice;    // total_price
    private String userId;     // user_id
}

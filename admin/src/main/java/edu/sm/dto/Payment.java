package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private int paymentId;     // payment_id
    private java.sql.Date paymentDate; // payment_date
    private String paymentMethod;   // payment_method
    private int paymentPrice;   // payment_price
    private int orderId;        // order_id
}

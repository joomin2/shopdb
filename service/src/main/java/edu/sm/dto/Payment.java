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
    private int payment_id;     // payment_id
    private java.sql.Date payment_date; // payment_date
    private String payment_method;   // payment_method
    private int amount;   // amount
    private int order_id;        // order_id
}

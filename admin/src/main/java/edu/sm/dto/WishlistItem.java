package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistItem {
    private String userId;     // user_id
    private int productId;     // product_id
    private java.sql.Date createdAt;  // created_at
}
package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//database와 동일하게..

public class Wishlist {
    private String user_id;
    private int product_id;
    private LocalDate created_at;

}

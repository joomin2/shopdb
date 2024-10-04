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

public class Review {
    private int review_in;
    private String user_id;
    private int product_id;
    private String title;
    private String content;
    private String img;
    private int score;
    private LocalDate review_date;


}

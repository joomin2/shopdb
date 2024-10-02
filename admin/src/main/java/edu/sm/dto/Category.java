package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private int cateno;        // cateno
    private String cateno1;    // cateno1
    private String cateno2;    // cateno2
    private String catename;   // catename
}

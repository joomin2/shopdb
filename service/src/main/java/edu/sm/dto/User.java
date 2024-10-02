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

public class User {
    private String user_id;
    private String name;
    private String email;
    private String pwd;
    private String phoneno;
    private int gender;
    private LocalDate joined;
    private int age;
}

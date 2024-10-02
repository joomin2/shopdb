package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private int addId;         // add_ID
    private String addName;    // add_name
    private String address;    // address
    private String addDetail;  // add_detail
    private String userId;     // user_id
}

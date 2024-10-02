package edu.sm.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String userId; // 사용자 ID
    private String name; // 이름
    private String email; // 이메일
    private String pwd; // 비밀번호
    private String phoneno; // 전화번호
    private int gender; // 성별 (1: 여자, 0: 남자)
    private java.sql.Date joined; // 가입 날짜
    private int age; // 나이
}

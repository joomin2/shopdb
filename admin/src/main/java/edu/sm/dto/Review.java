package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private int reviewIn; // 리뷰 ID
    private String userId; // 사용자 ID
    private int productId; // 제품 ID
    private String title; // 제목
    private String content; // 내용
    private String img; // 이미지
    private int score; // 점수
    private Timestamp reviewDate; // 리뷰 날짜
}

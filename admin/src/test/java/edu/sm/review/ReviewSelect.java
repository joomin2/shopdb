package edu.sm.review;


import edu.sm.dto.Review;

import edu.sm.service.ReviewService;

import java.util.List;

import java.util.List;

public class ReviewSelect {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();
        List<Review> reviews = null;

        try {
            reviews = reviewService.get();  // 모든 리뷰 가져오기

            // 테이블의 헤더 출력
            System.out.printf("%-10s %-15s %-15s %-30s %-50s %-10s %-15s\n",
                    "Review ID", "User ID", "Product ID", "Title", "Content", "Score", "Review Date");
            System.out.println("-----------------------------------------------------------------------------------------------");

            // 각 리뷰 항목을 테이블 형식으로 출력
            for (Review review : reviews) {
                System.out.printf("%-10d %-15s %-15d %-30s %-50s %-10d %-15s\n",
                        review.getReview_in(),
                        review.getUser_id(),
                        review.getProduct_id(),
                        review.getTitle(),
                        review.getContent(),
                        review.getScore(),
                        review.getReview_date() != null ? review.getReview_date().toString() : "N/A"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


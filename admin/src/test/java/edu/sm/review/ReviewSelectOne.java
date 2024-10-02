package edu.sm.review;


import edu.sm.dto.Review;

import edu.sm.service.ReviewService;

import java.util.Scanner;

public class ReviewSelectOne {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();

        // Scanner로 사용자 ID 입력받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("리뷰를 조회할 리뷰번호를 입력하시오: ");
        String id = scanner.nextLine();  // 사용자로부터 ID 입력받기

        Review review = null;

        try {
            review = reviewService.get(id);  // 입력받은 ID로 리뷰 조회

            // 조회된 리뷰 데이터 출력
            if (review != null) {
                // 테이블 헤더 출력
                System.out.printf("%-10s %-15s %-15s %-30s %-50s %-10s %-15s\n",
                        "Review ID", "User ID", "Product ID", "Title", "Content", "Score", "Review Date");
                System.out.println("-----------------------------------------------------------------------------------------------");

                // 리뷰 항목 출력
                System.out.printf("%-10d %-15s %-15d %-30s %-50s %-10d %-15s\n",
                        review.getReview_in(),
                        review.getUser_id(),
                        review.getProduct_id(),
                        review.getTitle(),
                        review.getContent(),
                        review.getScore(),
                        review.getReview_date() != null ? review.getReview_date().toString() : "N/A"
                );
            } else {
                System.out.println("No review found for the given ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Scanner 자원 해제
        }
    }
}


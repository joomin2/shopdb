package edu.sm.review;




import edu.sm.dto.Review;

import edu.sm.service.ReviewService;

import java.util.Scanner;

public class ReviewUpdate {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();
        Scanner scanner = new Scanner(System.in);

        // 사용자로부터 리뷰 ID 입력받기
        System.out.print("변경할 리뷰번호를 입력하세요: ");
        int reviewId = scanner.nextInt();  // 리뷰 ID 입력받기

        // 사용자로부터 다른 리뷰 정보 입력받기
        System.out.print("변경할 상품id를 입력하세요: ");
        int productId = scanner.nextInt();  // 상품 ID 입력받기

        scanner.nextLine(); // consume newline character

        System.out.print("변경할 제목을 입력하세요: ");
        String title = scanner.nextLine();  // 제목 입력받기

        System.out.print("변경할 내용을 입력하세요t: ");
        String content = scanner.nextLine();  // 내용 입력받기

        System.out.print("변경할 이미지를 입력하세요: ");
        String img = scanner.nextLine();  // 이미지 파일명 입력받기

        System.out.print("변경할 점수를 입력해주세요(1-5): ");
        int score = scanner.nextInt();  // 점수 입력받기

        // Review 객체 생성
        Review review = Review.builder()
                .product_id(productId)
                .title(title)
                .content(content)
                .img(img)
                .score(score)
                .review_in(reviewId)  // 리뷰 ID 설정
                .build();

        try {
            reviewService.modify(review);  // 리뷰 업데이트
            System.out.println("Review updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Scanner 자원 해제
        }
    }
}


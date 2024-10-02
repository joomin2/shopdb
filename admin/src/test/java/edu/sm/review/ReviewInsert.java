package edu.sm.review;

import edu.sm.dao.ReviewDao;
import edu.sm.dto.Review;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class ReviewInsert {

    public static void main(String[] args) {
        // Scanner를 사용하여 사용자 입력 받기
        Scanner scanner = new Scanner(System.in);

        // 리뷰 정보를 입력 받기
        System.out.print("사용자 ID를 입력하세요: ");
        String userId = scanner.nextLine();

        System.out.print("제품 ID를 입력하세요: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // 줄바꿈 처리

        System.out.print("제목을 입력하세요: ");
        String title = scanner.nextLine();

        System.out.print("내용을 입력하세요: ");
        String content = scanner.nextLine();

        System.out.print("이미지 URL을 입력하세요: ");
        String img = scanner.nextLine();

        System.out.print("점수를 입력하세요 (1-5): ");
        int score = scanner.nextInt();

        // 리뷰 객체 생성
        Review review = Review.builder()
                .userId(userId)
                .productId(productId)
                .title(title)
                .content(content)
                .img(img)
                .score(score)
                .reviewDate(new Timestamp(System.currentTimeMillis())) // 현재 시간으로 설정
                .build();

        // 데이터베이스 연결 및 리뷰 등록
        Connection con = null;
        ReviewDao reviewDao = new ReviewDao();
        try {
            con = ConnectionPool.getConnection(); // 데이터베이스 연결
            reviewDao.insert(review, con); // 리뷰 등록
            System.out.println("리뷰가 성공적으로 등록되었습니다.");
        } catch (SQLException e) {
            System.out.println("리뷰 등록 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close(); // 연결 종료
                } catch (SQLException e) {
                    System.out.println("연결 종료 중 오류 발생: " + e.getMessage());
                }
            }
        }
    }
}

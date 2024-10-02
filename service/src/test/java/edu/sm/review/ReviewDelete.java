package edu.sm.review;


import edu.sm.service.ReviewService;

import java.util.Scanner;

public class ReviewDelete {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();
        Scanner scanner = new Scanner(System.in);

        // 사용자로부터 삭제할 ID 입력받기
        System.out.print("삭제할 리뷰번호를 입력해줘: ");
        String id = scanner.nextLine();  // 사용자로부터 ID 입력받기

        try {
            reviewService.remove(id);  // 입력받은 ID로 데이터 삭제
            System.out.println("Data with ID \"" + id + "\" has been successfully deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Scanner 자원 해제
        }
    }
}


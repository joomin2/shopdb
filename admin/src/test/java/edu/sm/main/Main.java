package edu.sm.main;

import edu.sm.product.ProductInsert;  // ProductInsert 클래스를 추가
import edu.sm.product.ProductSelect;  // ProductSelect 클래스 추가
import edu.sm.product.ProductSelectOne;  // ProductSelectOne 클래스 추가
import edu.sm.product.ProductUpdate; // ProductUpdate 클래스 추가
import edu.sm.product.ProductDelete; // ProductDelete 클래스 추가
import edu.sm.shipping.ShippingSelect;  // ShippingSelect 클래스 추가
import edu.sm.shipping.ShippingSelectOne;  // ShippingSelectOne 클래스 추가
import edu.sm.user.UserSelect;
import edu.sm.user.UserSelectOne;
import edu.sm.review.ReviewSelect; // ReviewSelect 클래스 추가
import edu.sm.review.ReviewSelectOne; // ReviewSelectOne 클래스 추가

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("=== 관리자 모드 ===");
            System.out.println("1. 회원 관리");
            System.out.println("2. 배송 관리");
            System.out.println("3. 상품 관리");
            System.out.println("4. 리뷰 관리");
            System.out.println("5. 통계 관리");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageMembers();  // 회원 관리
                    break;
                case 2:
                    manageDeliveries();  // 배송 관리
                    break;
                case 3:
                    manageProducts();  // 상품 관리
                    break;
                case 4:
                    manageReviews();  // 리뷰 관리
                    break;
                case 5:
                    manageStatistics();  // 통계 관리
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("관리자 모드를 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    // 회원 관리
    private static void manageMembers() {
        System.out.println("=== 회원 관리 ===");
        System.out.println("1. 전체 회원 조회");
        System.out.println("2. 상세 회원 조회");
        System.out.print("0. 뒤로가기\n선택: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1:
                UserSelect.main(null);  // 전체 회원 조회
                break;
            case 2:
                UserSelectOne.main(null);  // 특정 회원 조회
                break;
            case 0:
                System.out.println("이전 메뉴로 돌아갑니다.");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    // 배송 관리
    private static void manageDeliveries() {
        System.out.println("=== 배송 관리 ===");
        System.out.println("1. 모든 배송 조회");
        System.out.println("2. 특정 배송 조회");
        System.out.print("0. 뒤로가기\n선택: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1:
                ShippingSelect.main(null);  // 전체 배송 조회
                break;
            case 2:
                ShippingSelectOne.main(null);  // 특정 배송 조회
                break;
            case 0:
                System.out.println("이전 메뉴로 돌아갑니다.");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    // 상품 관리
    private static void manageProducts() {
        System.out.println("=== 상품 관리 ===");
        System.out.println("1. 모든 상품 조회");
        System.out.println("2. 특정 상품 조회");
        System.out.println("3. 상품 추가");
        System.out.println("4. 상품 수정");  // 상품 수정 추가
        System.out.println("5. 상품 삭제");  // 상품 삭제 추가
        System.out.print("0. 뒤로가기\n선택: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1:
                ProductSelect.main(null);  // 전체 상품 조회
                break;
            case 2:
                ProductSelectOne.main(null);  // 특정 상품 조회
                break;
            case 3:
                ProductInsert.main(null);  // 상품 추가
                break;
            case 4:
                ProductUpdate.main(null);  // 상품 수정
                break;
            case 5:
                ProductDelete.main(null);  // 상품 삭제
                break;
            case 0:
                System.out.println("이전 메뉴로 돌아갑니다.");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    // 리뷰 관리
    private static void manageReviews() {
        System.out.println("=== 리뷰 관리 ===");
        System.out.println("1. 모든 리뷰 조회");
        System.out.println("2. 특정 리뷰 조회");
        System.out.print("0. 뒤로가기\n선택: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1:
                ReviewSelect.main(null);  // 모든 리뷰 조회
                break;
            case 2:
                ReviewSelectOne.main(null);  // 특정 리뷰 조회
                break;
            case 0:
                System.out.println("이전 메뉴로 돌아갑니다.");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    // 통계 관리
    private static void manageStatistics() {
        boolean isStatsRunning = true;

        while (isStatsRunning) {
            System.out.println("=== 통계 관리 ===");
            System.out.println("1. 성별 통계 조회");
            System.out.println("2. 연령대 통계 조회");
            System.out.print("0. 뒤로가기\n선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 처리

            switch (choice) {
                case 1:
                    viewGenderStatistics();  // 성별 통계 조회
                    break;
                case 2:
                    viewAgeStatistics();  // 연령대 통계 조회
                    break;
                case 0:
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    isStatsRunning = false; // 통계 관리 종료
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    // 성별 통계 조회
    private static void viewGenderStatistics() {
        String query = "SELECT gender, COUNT(*) AS count FROM users GROUP BY gender";
        try (Connection conn = DatabaseConnection.getConnection(); // 데이터베이스 연결
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 성별 통계 ===");
            while (rs.next()) {
                String gender = rs.getInt("gender") == 0 ? "남성" : "여성"; // 0이면 남성, 1이면 여성
                int count = rs.getInt("count");
                System.out.println(gender + ": " + count + "명");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 연령대 통계 조회
    private static void viewAgeStatistics() {
        String query = "SELECT CASE " +
                "WHEN age BETWEEN 0 AND 19 THEN '0-19세' " +
                "WHEN age BETWEEN 20 AND 29 THEN '20-29세' " +
                "WHEN age BETWEEN 30 AND 39 THEN '30-39세' " +
                "WHEN age BETWEEN 40 AND 49 THEN '40-49세' " +
                "WHEN age BETWEEN 50 AND 59 THEN '50-59세' " +
                "ELSE '60세 이상' END AS age_group, " +
                "COUNT(*) AS count " +
                "FROM users GROUP BY age_group";

        try (Connection conn = DatabaseConnection.getConnection(); // 데이터베이스 연결
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 연령대 통계 ===");
            while (rs.next()) {
                String ageGroup = rs.getString("age_group");
                int count = rs.getInt("count");
                System.out.println(ageGroup + ": " + count + "명");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package edu.sm.statics;

import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement; // PreparedStatement 임포트
import java.sql.ResultSet; // ResultSet 임포트

public class CategoryPurchaseStatics {
    // 성별에 따른 카테고리별 구매 수를 조회하는 SQL 문장
    static final String selectCategoryPurchases =
            "SELECT c.catename AS category_name, " +
                    "SUM(CASE WHEN u.gender = 1 THEN 1 ELSE 0 END) AS female_purchase_count, " +
                    "SUM(CASE WHEN u.gender = 0 THEN 1 ELSE 0 END) AS male_purchase_count " +
                    "FROM user u " +
                    "JOIN orders o ON u.user_id = o.user_id " +
                    "JOIN orderdetail od ON o.order_id = od.order_id " +
                    "JOIN product p ON od.product_id = p.product_id " +
                    "JOIN category c ON p.cateno = c.cateno " +
                    "GROUP BY c.catename " +
                    "ORDER BY female_purchase_count DESC, male_purchase_count DESC;";

    // 카테고리별 구매 수 출력 메서드
    public void printCategoryPurchases() {
        try (Connection con = ConnectionPool.create().getConnection(); // ConnectionPool을 통해 연결 생성
             PreparedStatement pstmt = con.prepareStatement(selectCategoryPurchases); // PreparedStatement 생성
             ResultSet rs = pstmt.executeQuery()) { // 쿼리 실행

            // 결과 처리
            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                int femaleCount = rs.getInt("female_purchase_count");
                int maleCount = rs.getInt("male_purchase_count");
                System.out.println("카테고리: " + categoryName + ", 여성 구매 수: " + femaleCount + ", 남성 구매 수: " + maleCount);
            }
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }

    // 메인 메서드
    public static void main(String[] args) {
        CategoryPurchaseStatics categoryPurchaseStatics = new CategoryPurchaseStatics(); // CategoryPurchaseStatics 객체 생성
        categoryPurchaseStatics.printCategoryPurchases(); // 카테고리별 구매 수 출력
    }
}

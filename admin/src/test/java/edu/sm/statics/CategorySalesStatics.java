package edu.sm.statics;

import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement; // PreparedStatement 임포트
import java.sql.ResultSet; // ResultSet 임포트

public class CategorySalesStatics {
    // 카테고리별 판매 통계를 조회하는 SQL 문장
    static final String selectCategorySales = "SELECT c.catename, SUM(od.odetail_quantity) AS total_sold " +
            "FROM product p " +
            "JOIN category c ON p.cateno = c.cateno " +
            "JOIN orderdetail od ON p.product_id = od.product_id " +
            "GROUP BY c.catename " +
            "ORDER BY total_sold DESC;";

    // 카테고리별 판매 통계 출력 메서드
    public void printCategorySales() {
        try (Connection con = ConnectionPool.create().getConnection(); // ConnectionPool을 통해 연결 생성
             PreparedStatement pstmt = con.prepareStatement(selectCategorySales); // PreparedStatement 생성
             ResultSet rs = pstmt.executeQuery()) { // 쿼리 실행

            // 결과 처리
            while (rs.next()) {
                String categoryName = rs.getString("catename");
                int totalSold = rs.getInt("total_sold");
                System.out.println("카테고리: " + categoryName + ", 판매량: " + totalSold);
            }
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }

    // 메인 메서드
    public static void main(String[] args) {
        CategorySalesStatics categorySalesStatics = new CategorySalesStatics(); // CategorySalesStatics 객체 생성
        categorySalesStatics.printCategorySales(); // 카테고리별 판매 통계 출력
    }
}

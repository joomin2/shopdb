package edu.sm.statics;

import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement; // PreparedStatement 임포트
import java.sql.ResultSet; // ResultSet 임포트

public class MontlyStatics {
    // 월별 매출 통계를 조회하는 SQL 문장
    static final String selectMonthlySales = "SELECT DATE_FORMAT(order_date, '%Y-%m') AS month, SUM(total_price) AS total_sales FROM orders GROUP BY month ORDER BY month";

    // 월별 매출 통계 출력 메서드
    public void printMonthlySales() {
        try (Connection con = ConnectionPool.create().getConnection(); // ConnectionPool을 통해 연결 생성
             PreparedStatement pstmt = con.prepareStatement(selectMonthlySales); // PreparedStatement 생성
             ResultSet rs = pstmt.executeQuery()) { // 쿼리 실행

            // 결과 처리
            while (rs.next()) {
                String month = rs.getString("month");
                double totalSales = rs.getDouble("total_sales");
                System.out.println("월: " + month + ", 총 매출: " + totalSales);
            }
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }

    // 메인 메서드
    public static void main(String[] args) {
        MontlyStatics montlyStatics = new MontlyStatics(); // MontlyStatics 객체 생성
        montlyStatics.printMonthlySales(); // 월별 매출 통계 출력
    }
}

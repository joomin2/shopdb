package edu.sm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.sm.frame.ConnectionPool;

public class MonthlyStatisticsDao {
    // 월별 판매량 통계 조회 메소드
    public void viewMonthlySales() {
        String sql = "SELECT MONTH(order_date) AS month, SUM(amount) AS total_sales " +
                "FROM orders GROUP BY MONTH(order_date)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 월별 판매량 통계 ===");
            while (rs.next()) {
                int month = rs.getInt("month");
                double totalSales = rs.getDouble("total_sales");
                System.out.printf("월: %d, 총 판매량: %.2f\n", month, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

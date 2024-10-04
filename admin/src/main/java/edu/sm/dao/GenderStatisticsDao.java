package edu.sm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.sm.frame.ConnectionPool;

public class GenderStatisticsDao {
    // 성별에 따른 판매량 통계 조회 메소드
    public void viewSalesByGender() {
        String sql = "SELECT gender, SUM(amount) AS total_sales " +
                "FROM orders JOIN user ON orders.user_id = user.user_id GROUP BY gender";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 성별에 따른 판매량 통계 ===");
            while (rs.next()) {
                int gender = rs.getInt("gender");
                double totalSales = rs.getDouble("total_sales");
                System.out.printf("성별: %s, 총 판매량: %.2f\n", gender == 0 ? "남성" : "여성", totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

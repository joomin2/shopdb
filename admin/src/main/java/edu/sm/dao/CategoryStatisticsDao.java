package edu.sm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.sm.frame.ConnectionPool;

public class CategoryStatisticsDao {
    // 카테고리별 판매량 통계 조회 메소드
    public void viewSalesByCategory() {
        String sql = "SELECT category_id, SUM(amount) AS total_sales " +
                "FROM orders GROUP BY category_id";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 카테고리별 판매량 통계 ===");
            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                double totalSales = rs.getDouble("total_sales");
                System.out.printf("카테고리 ID: %d, 총 판매량: %.2f\n", categoryId, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

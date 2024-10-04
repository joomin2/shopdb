package edu.sm.dao;

import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgeStatisticsDao {

    public void viewSalesByAgeGroup() {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(Sql.SELECT_SALES_BY_AGE_GROUP);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 연령대별 판매량 통계 ===");
            while (rs.next()) {
                String ageGroup = rs.getString("age_group");
                double totalSales = rs.getDouble("total_sales");
                System.out.printf("%s: %.2f원%n", ageGroup, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package edu.sm.dao;

import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenderStatisticsDao {

    public void viewSalesByGender() {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(Sql.SELECT_SALES_BY_GENDER);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 성별에 따른 판매량 통계 ===");
            while (rs.next()) {
                String gender = rs.getString("gender");
                double totalSales = rs.getDouble("total_sales");
                System.out.printf("%s: %.2f원%n", gender, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

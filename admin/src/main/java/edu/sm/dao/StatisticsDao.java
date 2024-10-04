package edu.sm.dao;

import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Sql;  // Sql 클래스 임포트 추가

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsDao {
    public void getSalesByAgeGroup() {
        String sql = Sql.SalesbyAgeGroup;  // Sql 클래스에서 쿼리 가져오기

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 연령별 판매량 ===");
            while (rs.next()) {
                String ageGroup = rs.getString("age_group");
                int totalSales = rs.getInt("total_sales");
                System.out.printf("%s: %d\n", ageGroup, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSalesByGenderGroup() {
        String sql = Sql.SalesbyGenderGroup;  // Sql 클래스에서 쿼리 가져오기

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("=== 성별 판매량 ===");
            while (rs.next()) {
                String genderGroup = rs.getString("gender_group");
                int totalSales = rs.getInt("total_sales");
                System.out.printf("%s: %d\n", genderGroup, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

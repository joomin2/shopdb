package edu.sm.anal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesByCategory {
    // 데이터베이스 연결 정보
    private static final String URL = "jdbc:mysql://210.119.34.205:3306/shopdb?useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Seoul"; // your_database는 실제 데이터베이스 이름으로 바꿔주세요.
    private static final String USER = "smuser"; // 데이터베이스 사용자 이름
    private static final String PASSWORD = "111111"; // 데이터베이스 비밀번호

    public static void main(String[] args) {
        // SQL 쿼리
        String sql = "SELECT c.catename, SUM(od.odetail_quantity) AS total_sold " +
                "FROM product p " +
                "JOIN category c ON p.cateno = c.cateno " +
                "JOIN orderdetail od ON p.product_id = od.product_id " +
                "GROUP BY c.catename " +
                "ORDER BY total_sold DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // 결과 출력
            System.out.printf("%-30s %-15s\n", "Category Name", "Total Sold");
            System.out.println("-----------------------------------------------");

            while (rs.next()) {
                String categoryName = rs.getString("catename");
                int totalSold = rs.getInt("total_sold");
                System.out.printf("%-30s %-15d\n", categoryName, totalSold);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


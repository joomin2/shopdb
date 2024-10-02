package edu.sm.anal;

import java.sql.*;

public class SalesReport {
    // 데이터베이스 연결 정보
    private static final String URL = "jdbc:mysql://210.119.34.205:3306/shopdb?useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Seoul"; // your_database는 실제 데이터베이스 이름으로 바꿔주세요.
    private static final String USER = "smuser"; // 데이터베이스 사용자 이름
    private static final String PASSWORD = "111111"; // 데이터베이스 비밀번호

    public static void main(String[] args) {
        // SQL 쿼리
        String sql = "SELECT DATE_FORMAT(order_date, '%Y-%m') AS month, SUM(total_price) AS total_sales " +
                "FROM orders " +
                "GROUP BY month " +
                "ORDER BY month";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // 결과 출력
            System.out.printf("%-7s %-15s\n", "Month", "Total Sales");
            System.out.println("-------------------------");

            while (rs.next()) {
                String month = rs.getString("month");
                double totalSales = rs.getDouble("total_sales");
                System.out.printf("%-7s %-15.2f\n", month, totalSales);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

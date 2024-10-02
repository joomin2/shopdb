package edu.sm.orders;

import edu.sm.dao.OrdersDao;  // OrdersDao 임포트
import edu.sm.dto.Orders;  // Orders DTO 임포트
import edu.sm.frame.ConnectionPool;  // ConnectionPool 임포트
import java.sql.Connection;  // JDBC 연결을 위한 클래스 임포트
import java.sql.SQLException;  // SQLException 클래스 임포트
import java.util.List;  // List 임포트

public class OrdersSelect {
    public static void main(String[] args) {
        try {
            // ConnectionPool 초기화
            ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        // 데이터베이스에서 모든 주문 선택
        try (Connection con = ConnectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            OrdersDao ordersDao = new OrdersDao();
            List<Orders> ordersList = ordersDao.select(con); // 주문 선택 메서드 호출
            System.out.println("모든 주문 목록 (주문 ID 오름차순):");
            for (Orders order : ordersList) {
                System.out.println(order); // 각 주문 출력
            }
        } catch (SQLException e) {
            System.out.println("SQL 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("주문 선택 실패: " + e.getMessage());
        }
    }
}

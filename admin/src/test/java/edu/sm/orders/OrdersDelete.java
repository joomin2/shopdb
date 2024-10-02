package edu.sm.orders;

import edu.sm.dao.OrdersDao;  // OrdersDao 임포트
import edu.sm.frame.ConnectionPool;  // ConnectionPool 임포트
import java.sql.Connection;  // JDBC 연결을 위한 클래스 임포트
import java.sql.SQLException;  // SQLException 클래스 임포트

public class OrdersDelete {
    public static void main(String[] args) {
        int orderIdToDelete = 7;  // 삭제할 주문 ID

        try {
            // ConnectionPool 초기화
            ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        // 주문 삭제
        try (Connection con = ConnectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            OrdersDao ordersDao = new OrdersDao();
            ordersDao.delete(orderIdToDelete, con); // 주문 삭제 메서드 호출
            System.out.println("주문 삭제 성공: 주문 ID " + orderIdToDelete);
        } catch (SQLException e) {
            System.out.println("SQL 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("주문 삭제 실패: " + e.getMessage());
        }
    }
}

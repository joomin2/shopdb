package edu.sm.orders;

import edu.sm.dao.OrdersDao;  // OrdersDao 임포트
import edu.sm.dto.Orders;  // Orders DTO 임포트
import edu.sm.exception.DuplicatedIdException;  // 중복 ID 예외 처리 임포트
import edu.sm.frame.ConnectionPool;  // ConnectionPool 임포트
import java.sql.Connection;  // JDBC 연결을 위한 클래스 임포트
import java.sql.SQLException;  // SQLException 클래스 임포트
import java.sql.Date;  // java.sql.Date 클래스 임포트

public class OrdersInsert {
    public static void main(String[] args) {
        try {
            // ConnectionPool 초기화
            ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        // 하드코딩된 Orders 정보
        Orders newOrder = Orders.builder()
               // .orderId(21)  // 주문 ID (하드 코딩)
                .userId("eva23")  // 사용자 ID 수정
                .status("완료")  // 주문 상태 (하드 코딩)
                .totalPrice(50000)  // 총 가격 (하드 코딩)
                .orderDate(Date.valueOf("2023-09-30"))  // 주문 날짜 (하드 코딩)
                .build();

        // 데이터베이스에 주문 추가
        try (Connection con = ConnectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            OrdersDao ordersDao = new OrdersDao();
            ordersDao.insert(newOrder, con); // 주문 추가 메서드 호출
            System.out.println("주문 등록 성공: " + newOrder);
        } catch (DuplicatedIdException e) {
            System.out.println("주문 ID가 중복되었습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("주문 등록 실패: " + e.getMessage());
        }
    }
}

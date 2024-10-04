package edu.sm.orderdetail;

import edu.sm.dto.OrderDetail;
import edu.sm.service.OrderDetailService;
import edu.sm.exception.NotFoundException; // NotFoundException 예외 추가
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailGet {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();

        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        // 조회할 주문 세부 정보의 ID
        int odetailId = 5; // 예를 들어, 1번 ID의 주문 세부 정보 조회

        try (Connection con = connectionPool.getConnection()) {
            OrderDetail orderDetail = orderDetailService.get(odetailId); // 주문 세부 정보 조회
            if (orderDetail != null) {
                System.out.println("조회된 주문 세부 정보: " + orderDetail);
            } else {
                System.err.println("주문 세부 정보가 존재하지 않습니다. ID: " + odetailId);
            }
        } catch (NotFoundException e) {
            System.err.println("주문 세부 정보가 존재하지 않습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

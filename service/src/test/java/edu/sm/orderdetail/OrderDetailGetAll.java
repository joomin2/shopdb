package edu.sm.orderdetail;

import edu.sm.dto.OrderDetail;
import edu.sm.service.OrderDetailService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailGetAll {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();

        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        try (Connection con = connectionPool.getConnection()) {
            List<OrderDetail> orderDetails = orderDetailService.getAll(); // 모든 주문 세부 정보 조회
            if (orderDetails.isEmpty()) {
                System.out.println("주문 세부 정보가 없습니다.");
            } else {
                System.out.println("모든 주문 세부 정보:");
                for (OrderDetail orderDetail : orderDetails) {
                    System.out.println(orderDetail);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package edu.sm.service;

import edu.sm.dao.OrdersDao; // OrdersDao 임포트
import edu.sm.dto.MonthlySales;
import edu.sm.dto.Orders; // Orders DTO 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrdersService {
    private static OrdersDao ordersDao; // OrdersDao 객체 선언
    private static ConnectionPool cp; // ConnectionPool 객체 선언

    public OrdersService() {
        ordersDao = new OrdersDao(); // OrdersDao 객체 초기화
        try {
            cp = ConnectionPool.create(); // Connection pool을 이용한 Connection 준비
        } catch (SQLException e) {
            throw new RuntimeException("ConnectionPool 초기화 실패: " + e.getMessage(), e);
        }
    }

    // 주문 추가
    public Orders addOrder(Orders order) throws Exception {
        Connection con = null; // Connection 초기화
        try {
            con = cp.getConnection(); // Connection 획득
            return ordersDao.insert(order, con); // 주문 추가
        } catch (SQLException e) {
            throw new Exception("주문 추가 실패: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                con.close(); // Connection 종료
            }
        }
    }

    // 주문 업데이트
    public Orders updateOrder(Orders order) throws Exception {
        Connection con = null; // Connection 초기화
        try {
            con = cp.getConnection(); // Connection 획득
            return ordersDao.update(order, con); // 주문 업데이트
        } catch (SQLException e) {
            throw new Exception("주문 업데이트 실패: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                con.close(); // Connection 종료
            }
        }
    }

    // 특정 주문 조회
    public Orders getOrder(Integer orderId) throws Exception {
        Connection con = null; // Connection 초기화
        try {
            con = cp.getConnection(); // Connection 획득
            return ordersDao.select(orderId, con); // 특정 주문 조회
        } catch (SQLException e) {
            throw new Exception("주문 조회 실패: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                con.close(); // Connection 종료
            }
        }
    }

    // 모든 주문 조회
    public List<Orders> getAllOrders() throws Exception {
        Connection con = null; // Connection 초기화
        try {
            con = cp.getConnection(); // Connection 획득
            return ordersDao.select(con); // 모든 주문 조회
        } catch (SQLException e) {
            throw new Exception("모든 주문 조회 실패: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                con.close(); // Connection 종료
            }
        }
    }

    // 주문 삭제
    public boolean deleteOrder(Integer orderId) throws Exception {
        Connection con = null; // Connection 초기화
        try {
            con = cp.getConnection(); // Connection 획득
            return ordersDao.delete(orderId, con); // 주문 삭제
        } catch (SQLException e) {
            throw new Exception("주문 삭제 실패: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                con.close(); // Connection 종료
            }
        }
    }
}

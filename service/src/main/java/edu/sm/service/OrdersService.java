package edu.sm.service;

import edu.sm.dao.OrdersDao;
import edu.sm.dto.Orders;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.exception.NotFoundException;

import java.sql.Connection;
import java.util.List;

public class OrdersService {
    private final OrdersDao ordersDao; // OrdersDao 객체
    private final Connection connection; // JDBC 연결 객체

    // 생성자
    public OrdersService(OrdersDao ordersDao, Connection connection) {
        this.ordersDao = ordersDao; // OrdersDao 초기화
        this.connection = connection; // JDBC 연결 초기화
    }

    // 주문 추가 메서드
    public Orders addOrder(Orders orders) throws Exception {
        try {
            return ordersDao.insert(orders, connection); // 주문 추가
        } catch (DuplicatedIdException e) {
            throw e; // 중복된 주문 예외 처리
        } catch (Exception e) {
            throw new Exception("주문 추가 실패", e); // 일반 예외 처리
        }
    }

    // 주문 업데이트 메서드
    public Orders updateOrder(Orders orders) throws Exception {
        try {
            return ordersDao.update(orders, connection); // 주문 업데이트
        } catch (NotFoundException e) {
            throw e; // 찾지 못한 주문 예외 처리
        } catch (Exception e) {
            throw new Exception("주문 업데이트 실패", e); // 일반 예외 처리
        }
    }

    // 주문 삭제 메서드
    public Boolean removeOrder(Integer order_id) throws Exception {
        try {
            return ordersDao.delete(order_id, connection); // 주문 삭제
        } catch (Exception e) {
            throw new Exception("주문 삭제 실패", e); // 일반 예외 처리
        }
    }

    // 특정 주문 조회 메서드
    public Orders getOrder(Integer order_id) throws Exception {
        Orders order = ordersDao.select(order_id, connection); // 주문 조회
        if (order == null) {
            throw new NotFoundException("주문을 찾을 수 없습니다."); // 찾지 못한 경우 예외 발생
        }
        return order; // 조회한 주문 반환
    }

    // 모든 주문 조회 메서드
    public List<Orders> getAllOrders() throws Exception {
        return ordersDao.select(connection); // 모든 주문 조회
    }
}

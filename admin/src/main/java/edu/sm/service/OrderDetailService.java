package edu.sm.service;

import edu.sm.dao.OrderDetailDao; // OrderDetailDao 클래스 임포트
import edu.sm.dto.OrderDetail; // OrderDetail DTO 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 클래스 임포트
import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class OrderDetailService {
    private OrderDetailDao orderDetailDao; // OrderDetailDao 인스턴스 변수

    public OrderDetailService() {
        this.orderDetailDao = new OrderDetailDao(); // OrderDetailDao 초기화
    }

    // 주문 상세 추가 메서드
    public OrderDetail addOrderDetail(OrderDetail orderDetail) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.create(); // ConnectionPool 인스턴스 생성
        try (Connection con = connectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            return orderDetailDao.insert(orderDetail, con); // 주문 상세 추가
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
            throw e; // 예외를 다시 던짐
        }
    }

    // 주문 상세 업데이트 메서드
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.create(); // ConnectionPool 인스턴스 생성
        try (Connection con = connectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            return orderDetailDao.update(orderDetail, con); // 주문 상세 업데이트
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
            throw e; // 예외를 다시 던짐
        }
    }

    // 주문 상세 삭제 메서드
    public boolean deleteOrderDetail(Integer orderDetailId) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.create(); // ConnectionPool 인스턴스 생성
        try (Connection con = connectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            return orderDetailDao.delete(orderDetailId, con); // 주문 상세 삭제
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
            throw e; // 예외를 다시 던짐
        }
    }

    // 특정 주문 상세 조회 메서드
    public OrderDetail getOrderDetail(Integer orderDetailId) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.create(); // ConnectionPool 인스턴스 생성
        try (Connection con = connectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            return orderDetailDao.select(orderDetailId, con); // 주문 상세 조회
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
            throw e; // 예외를 다시 던짐
        }
    }

    // 모든 주문 상세 조회 메서드
    public List<OrderDetail> getAllOrderDetails() throws Exception {
        ConnectionPool connectionPool = ConnectionPool.create(); // ConnectionPool 인스턴스 생성
        try (Connection con = connectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            return orderDetailDao.select(con); // 모든 주문 상세 조회
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
            throw e; // 예외를 다시 던짐
        }
    }
}

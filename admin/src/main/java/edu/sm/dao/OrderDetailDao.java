package edu.sm.dao;

import edu.sm.dto.OrderDetail; // OrderDetail DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.*;
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class OrderDetailDao implements Dao<Integer, OrderDetail> {

    // 주문 세부 정보 추가 메서드
    @Override
    public OrderDetail insert(OrderDetail orderDetail, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.insertOrderDetail); // SQL 쿼리 준비
            ps.setInt(1, orderDetail.getPrice()); // 가격 설정
            ps.setInt(2, orderDetail.getOrderId()); // 주문 ID 설정
            ps.setInt(3, orderDetail.getProductId()); // 제품 ID 설정
            ps.setInt(4, orderDetail.getOdetailQuantity()); // 수량 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0002"); // 중복 ID 예외 발생
        } catch (Exception e) {
            throw e; // 다른 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return orderDetail; // 추가한 주문 세부 정보 반환
    }

    // 주문 세부 정보 업데이트 메서드
    @Override
    public OrderDetail update(OrderDetail orderDetail, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.updateOrderDetail); // SQL 쿼리 준비
            ps.setInt(1, orderDetail.getOdetailQuantity()); // 수량 설정
            ps.setInt(2, orderDetail.getPrice()); // 가격 설정
            ps.setInt(3, orderDetail.getOdetailId()); // 주문 세부 정보 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return orderDetail; // 업데이트한 주문 세부 정보 반환
    }

    // 주문 세부 정보 삭제 메서드
    @Override
    public Boolean delete(Integer orderDetailId, Connection con) throws Exception {
        Boolean isDeleted = false; // 삭제 여부 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.deleteOrderDetail); // SQL 쿼리 준비
            ps.setInt(1, orderDetailId); // 주문 세부 정보 ID 설정
            int result = ps.executeUpdate(); // 쿼리 실행
            if (result == 1) {
                isDeleted = true; // 삭제 성공
            }
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return isDeleted; // 삭제 여부 반환
    }

    // 특정 주문 세부 정보 조회 메서드
    @Override
    public OrderDetail select(Integer orderDetailId, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        OrderDetail orderDetail = null; // 주문 세부 정보 객체 초기화
        try {
            ps = con.prepareStatement(Sql.selectOneOrderDetail); // SQL 쿼리 준비
            ps.setInt(1, orderDetailId); // 주문 세부 정보 ID 설정
            rs = ps.executeQuery(); // 쿼리 실행
            if (rs.next()) { // 결과가 있을 경우
                orderDetail = new OrderDetail(); // 주문 세부 정보 객체 생성
                orderDetail.setOdetailId(rs.getInt("odetail_id")); // 주문 세부 정보 ID 설정
                orderDetail.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
                orderDetail.setProductId(rs.getInt("product_id")); // 제품 ID 설정
                orderDetail.setOdetailQuantity(rs.getInt("odetail_quantity")); // 수량 설정
                orderDetail.setPrice(rs.getInt("price")); // 가격 설정
            }
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if (rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return orderDetail; // 조회한 주문 세부 정보 반환
    }

    // 모든 주문 세부 정보 조회 메서드
    @Override
    public List<OrderDetail> select(Connection con) throws Exception {
        List<OrderDetail> orderDetailList = new ArrayList<>(); // 주문 세부 정보 리스트 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        try {
            ps = con.prepareStatement(Sql.selectOrderDetail); // SQL 쿼리 준비
            rs = ps.executeQuery(); // 쿼리 실행
            while (rs.next()) { // 결과가 있는 동안 반복
                OrderDetail orderDetail = new OrderDetail(); // 주문 세부 정보 객체 생성
                orderDetail.setOdetailId(rs.getInt("odetail_id")); // 주문 세부 정보 ID 설정
                orderDetail.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
                orderDetail.setProductId(rs.getInt("product_id")); // 제품 ID 설정
                orderDetail.setOdetailQuantity(rs.getInt("odetail_quantity")); // 수량 설정
                orderDetail.setPrice(rs.getInt("price")); // 가격 설정
                orderDetailList.add(orderDetail); // 리스트에 추가
            }
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if (rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return orderDetailList; // 주문 세부 정보 리스트 반환
    }
}

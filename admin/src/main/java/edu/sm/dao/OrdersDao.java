package edu.sm.dao;

import edu.sm.dto.Orders; // Orders DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.*;
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class OrdersDao implements Dao<Integer, Orders> {

    // 주문 추가 메서드
    @Override
    public Orders insert(Orders order, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.insertOrder); // SQL 쿼리 준비
            ps.setDate(1, order.getOrderDate()); // 주문 날짜 설정
            ps.setString(2, order.getStatus()); // 주문 상태 설정 (완료/취소)
            ps.setInt(3, order.getTotalPrice()); // 총 금액 설정
            ps.setString(4, order.getUserId()); // 사용자 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001"); // 중복 ID 예외 발생
        } catch (Exception e) {
            throw e; // 다른 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return order; // 추가한 주문 반환
    }

    // 주문 업데이트 메서드
    @Override
    public Orders update(Orders order, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.updateOrder); // SQL 쿼리 준비
            ps.setDate(1, order.getOrderDate()); // 주문 날짜 설정
            ps.setString(2, order.getStatus()); // 주문 상태 설정 (완료/취소)
            ps.setInt(3, order.getTotalPrice()); // 총 금액 설정
            ps.setInt(4, order.getOrderId()); // 주문 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return order; // 업데이트한 주문 반환
    }

    // 주문 삭제 메서드
    @Override
    public Boolean delete(Integer orderId, Connection con) throws Exception {
        Boolean isDeleted = false; // 삭제 여부 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.deleteOrder); // SQL 쿼리 준비
            ps.setInt(1, orderId); // 주문 ID 설정
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

    // 특정 주문 조회 메서드
    @Override
    public Orders select(Integer orderId, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        Orders order = null; // 주문 객체 초기화
        try {
            ps = con.prepareStatement(Sql.selectOneOrder); // SQL 쿼리 준비
            ps.setInt(1, orderId); // 주문 ID 설정
            rs = ps.executeQuery(); // 쿼리 실행
            if (rs.next()) { // 결과가 있을 경우
                order = new Orders(); // 주문 객체 생성
                order.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
                order.setOrderDate(rs.getDate("order_date")); // 주문 날짜 설정
                order.setStatus(rs.getString("status")); // 주문 상태 설정
                order.setTotalPrice(rs.getInt("total_price")); // 총 금액 설정
                order.setUserId(rs.getString("user_id")); // 사용자 ID 설정
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
        return order; // 조회한 주문 반환
    }

    // 모든 주문 조회 메서드
    @Override
    public List<Orders> select(Connection con) throws Exception {
        List<Orders> ordersList = new ArrayList<>(); // 주문 리스트 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        try {
            ps = con.prepareStatement(Sql.selectOrder); // SQL 쿼리 준비
            rs = ps.executeQuery(); // 쿼리 실행
            while (rs.next()) { // 결과가 있는 동안 반복
                Orders order = new Orders(); // 주문 객체 생성
                order.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
                order.setOrderDate(rs.getDate("order_date")); // 주문 날짜 설정
                order.setStatus(rs.getString("status")); // 주문 상태 설정
                order.setTotalPrice(rs.getInt("total_price")); // 총 금액 설정
                order.setUserId(rs.getString("user_id")); // 사용자 ID 설정
                ordersList.add(order); // 리스트에 추가
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
        return ordersList; // 주문 리스트 반환
    }
}

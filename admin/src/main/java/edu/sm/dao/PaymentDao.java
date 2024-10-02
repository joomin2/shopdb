package edu.sm.dao;

import edu.sm.dto.Payment; // Payment DTO 임포트
import edu.sm.dto.WishlistItem;
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.*;
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class PaymentDao implements Dao<Integer, Payment> {

    // 결제 추가 메서드
    @Override
    public Payment insert(Payment payment, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.insertPayment); // SQL 쿼리 준비
            ps.setString(1, String.valueOf(payment.getPaymentDate())); // 결제 날짜 설정
            ps.setString(2, payment.getPaymentMethod()); // 결제 방법 설정
            ps.setInt(3, payment.getPaymentPrice()); // 결제 금액 설정
            ps.setInt(4, payment.getOrderId()); // 주문 ID 설정
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
        return payment; // 추가한 결제 정보 반환
    }

    // 결제 업데이트 메서드
    @Override
    public Payment update(Payment payment, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.updatePayment); // SQL 쿼리 준비
            ps.setString(1, String.valueOf(payment.getPaymentDate())); // 결제 날짜 설정
            ps.setString(2, payment.getPaymentMethod()); // 결제 방법 설정
            ps.setInt(3, payment.getPaymentPrice()); // 결제 금액 설정
            ps.setInt(4, payment.getOrderId()); // 주문 ID 설정
            ps.setInt(5, payment.getPaymentId()); // 결제 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return payment; // 업데이트한 결제 정보 반환
    }

    // 결제 삭제 메서드
    @Override
    public Boolean delete(Integer paymentId, Connection con) throws Exception {
        Boolean isDeleted = false; // 삭제 여부 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.deletePayment); // SQL 쿼리 준비
            ps.setInt(1, paymentId); // 결제 ID 설정
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

    // 특정 결제 조회 메서드
    @Override
    public Payment select(Integer paymentId, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        Payment payment = null; // 결제 객체 초기화
        try {
            ps = con.prepareStatement(Sql.selectOnePayment); // SQL 쿼리 준비
            ps.setInt(1, paymentId); // 결제 ID 설정
            rs = ps.executeQuery(); // 쿼리 실행
            if (rs.next()) { // 결과가 있을 경우
                payment = new Payment(); // 결제 객체 생성
                payment.setPaymentId(rs.getInt("payment_id")); // 결제 ID 설정
                payment.setPaymentDate(Date.valueOf(rs.getString("payment_date"))); // 결제 날짜 설정
                payment.setPaymentMethod(rs.getString("payment_method")); // 결제 방법 설정
                payment.setPaymentPrice(rs.getInt("payment_price")); // 결제 금액 설정
                payment.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
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
        return payment; // 조회한 결제 정보 반환
    }

    // 모든 결제 조회 메서드
    @Override
    public List<Payment> select(Connection con) throws Exception {
        List<Payment> paymentList = new ArrayList<>(); // 결제 리스트 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        try {
            ps = con.prepareStatement(Sql.selectPayment); // SQL 쿼리 준비
            rs = ps.executeQuery(); // 쿼리 실행
            while (rs.next()) { // 결과가 있는 동안 반복
                Payment payment = new Payment(); // 결제 객체 생성
                payment.setPaymentId(rs.getInt("payment_id")); // 결제 ID 설정
                payment.setPaymentDate(Date.valueOf(rs.getString("payment_date"))); // 결제 날짜 설정
                payment.setPaymentMethod(rs.getString("payment_method")); // 결제 방법 설정
                payment.setPaymentPrice(rs.getInt("payment_price")); // 결제 금액 설정
                payment.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
                paymentList.add(payment); // 리스트에 추가
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
        return paymentList; // 결제 리스트 반환
    }
}

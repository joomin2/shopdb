package edu.sm.service;

import edu.sm.dao.PaymentDao; // PaymentDao 임포트
import edu.sm.dto.Payment; // Payment DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    private PaymentDao paymentDao; // PaymentDao 객체 선언

    public PaymentService() {
        this.paymentDao = new PaymentDao(); // PaymentDao 객체 초기화
    }

    // 결제 추가
    public Payment add(Payment payment, Connection con) throws Exception {
        try {
            return paymentDao.insert(payment, con); // 결제 추가 메서드 호출
        } catch (DuplicatedIdException e) {
            throw e; // 중복 ID 예외 발생
        } catch (SQLException e) {
            throw new Exception("결제 추가 실패", e); // SQL 예외 발생
        }
    }

    // 결제 업데이트
    public Payment update(Payment payment, Connection con) throws Exception {
        try {
            return paymentDao.update(payment, con); // 결제 업데이트 메서드 호출
        } catch (SQLException e) {
            throw new Exception("결제 업데이트 실패", e); // SQL 예외 발생
        }
    }

    // 결제 삭제
    public boolean delete(Integer paymentId, Connection con) throws Exception {
        try {
            return paymentDao.delete(paymentId, con); // 결제 삭제 메서드 호출
        } catch (SQLException e) {
            throw new Exception("결제 삭제 실패", e); // SQL 예외 발생
        }
    }

    // 특정 결제 조회
    public Payment get(Integer paymentId, Connection con) throws Exception {
        try {
            return paymentDao.select(paymentId, con); // 특정 결제 조회 메서드 호출
        } catch (SQLException e) {
            throw new Exception("결제 조회 실패", e); // SQL 예외 발생
        }
    }

    // 모든 결제 조회
    public List<Payment> getAll(Connection con) throws Exception {
        try {
            return paymentDao.select(con); // 모든 결제 조회 메서드 호출
        } catch (SQLException e) {
            throw new Exception("결제 목록 조회 실패", e); // SQL 예외 발생
        }
    }
    // payment_id 중복 체크 메서드
    public boolean existsById(int paymentId, Connection con) throws Exception {
        return paymentDao.existsById(paymentId, con); // PaymentDao의 existsById 메서드 호출
    }

}

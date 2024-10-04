package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.service.PaymentService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentGetAll {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        ConnectionPool connectionPool;

        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        try (Connection con = connectionPool.getConnection()) {
            List<Payment> payments = paymentService.getAll(con);
            if (payments.isEmpty()) {
                System.out.println("등록된 결제 정보가 없습니다.");
            } else {
                System.out.println("모든 결제 정보:");
                for (Payment payment : payments) {
                    System.out.println(payment);
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

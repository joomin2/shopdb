package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.service.PaymentService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PaymentGet {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        ConnectionPool connectionPool;

        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        // 조회할 결제 ID 입력 받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("조회할 결제 ID를 입력하세요: ");
        int paymentId = scanner.nextInt();

        try (Connection con = connectionPool.getConnection()) {
            Payment payment = paymentService.get(paymentId, con);
            if (payment != null) {
                System.out.println("조회된 결제 정보: " + payment);
            } else {
                System.out.println("결제 ID " + paymentId + "에 해당하는 결제를 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close(); // Scanner 자원 해제
        }
    }
}

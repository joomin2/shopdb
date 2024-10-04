package edu.sm.payment;

import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.PaymentService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PaymentDelete {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        ConnectionPool connectionPool;

        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        // 삭제할 결제 ID 입력 받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제할 결제 ID를 입력하세요: ");
        int paymentId = scanner.nextInt();

        try (Connection con = connectionPool.getConnection()) {
            boolean isDeleted = paymentService.delete(paymentId, con);
            if (isDeleted) {
                System.out.println("결제 ID " + paymentId + "가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("결제 ID " + paymentId + "를 찾을 수 없습니다.");
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

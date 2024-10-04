package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.exception.NotFoundException; // NotFoundException 예외 추가
import edu.sm.service.PaymentService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PaymentUpdate {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();

        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        // 사용자로부터 입력 받기 위한 스캐너
        Scanner scanner = new Scanner(System.in);

        // 결제 ID 입력 받기
        System.out.print("업데이트할 결제 ID 입력: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine(); // 줄 바꿈 처리

        // 새로운 결제 날짜 입력 받기
        System.out.print("새로운 결제 날짜 입력 (YYYY-MM-DD): ");
        String paymentDateInput = scanner.nextLine();

        // 새로운 결제 방법 입력 받기
        System.out.print("새로운 결제 방법 입력: ");
        String paymentMethod = scanner.nextLine();

        // 새로운 결제 금액 입력 받기
        System.out.print("새로운 결제 금액 입력: ");
        int paymentPrice = scanner.nextInt();

        // 새로운 주문 ID 입력 받기
        System.out.print("새로운 주문 ID 입력: ");
        int orderId = scanner.nextInt();

        // Payment 객체 생성
        Payment paymentToUpdate = Payment.builder()
                .payment_id(paymentId) // 업데이트할 결제 ID
                .payment_date(java.sql.Date.valueOf(paymentDateInput)) // 새로운 결제 날짜
                .payment_method(paymentMethod) // 새로운 결제 방법
                .payment_price(paymentPrice) // 새로운 결제 금액
                .order_id(orderId) // 주문 ID
                .build();

        try (Connection con = connectionPool.getConnection()) {
            Payment updatedPayment = paymentService.update(paymentToUpdate, con);
            System.out.println("업데이트된 결제 정보: " + updatedPayment);
        } catch (NotFoundException e) {
            System.err.println("결제 정보가 존재하지 않습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close(); // 스캐너 닫기
        }
    }
}

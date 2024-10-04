package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.PaymentService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PaymentInsert {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        ConnectionPool connectionPool;

        try {
            connectionPool = ConnectionPool.create(); // 연결 풀 초기화
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // 사용자로부터 입력 받기
        System.out.print("payment_id 입력: ");
        int paymentId = Integer.parseInt(scanner.nextLine());

        System.out.print("결제 날짜 입력 (YYYY-MM-DD 형식): ");
        String paymentDateInput = scanner.nextLine();
        java.sql.Date paymentDate = java.sql.Date.valueOf(paymentDateInput); // 결제 날짜 설정

        System.out.print("결제 방법 입력: ");
        String paymentMethod = scanner.nextLine();

        System.out.print("결제 금액 입력: ");
        int paymentPrice = Integer.parseInt(scanner.nextLine());

        System.out.print("주문 ID 입력: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        // Payment 객체 생성
        Payment newPayment = Payment.builder()
                .payment_id(paymentId) // 결제 ID 설정
                .payment_date(paymentDate) // 결제 날짜 설정
                .payment_method(paymentMethod) // 결제 방법 설정
                .payment_price(paymentPrice) // 결제 금액 설정
                .order_id(orderId) // 주문 ID 설정
                .build();

        try (Connection con = connectionPool.getConnection()) { // Connection 가져오기
            // payment_id 중복 체크
            if (paymentService.existsById(paymentId, con)) {
                throw new DuplicatedIdException("중복된 payment_id: " + paymentId);
            }

            Payment addedPayment = paymentService.add(newPayment, con); // 결제 추가
            System.out.println("추가된 결제: " + addedPayment); // 추가된 결제 정보 출력
        } catch (DuplicatedIdException e) {
            System.err.println("중복된 ID로 인한 예외 발생: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close(); // Scanner 닫기
        }
    }
}

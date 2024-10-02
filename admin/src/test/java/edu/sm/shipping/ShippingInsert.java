package edu.sm.shipping;

import edu.sm.dto.Shipping; // Shipping DTO 임포트
import edu.sm.service.ShippingService; // ShippingService 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트

public class ShippingInsert {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService(); // ShippingService 객체 생성
        Shipping shipping = new Shipping(); // Shipping DTO 객체 생성

        // 배송 정보를 하드코딩
        shipping.setShippingAdress("서울시 강남구 삼성로 123"); // 배송 주소 설정
        shipping.setShippingDate(java.sql.Date.valueOf("2024-09-30")); // 배송 날짜 설정
        shipping.setShippingStatus("배송 준비 중"); // 배송 상태 설정
        shipping.setOrderId(5); // 주문 ID 설정 (유효한 값으로 설정)

        try (Connection con = ConnectionPool.create().getConnection()) { // ConnectionPool을 통해 연결 생성
            shippingService.addShipping(shipping, con); // 배송 정보 추가
            System.out.println("배송 정보가 성공적으로 추가되었습니다: " + shipping);
        } catch (Exception e) {
            System.out.println("시스템 장애"); // 예외 발생 시 메시지 출력
            e.printStackTrace(); // 예외 스택 추적 출력
        }
    }
}

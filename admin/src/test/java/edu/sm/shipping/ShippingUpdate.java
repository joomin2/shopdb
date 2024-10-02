package edu.sm.shipping;

import edu.sm.dto.Shipping; // Shipping DTO 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import edu.sm.service.ShippingService; // ShippingService 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.Date; // SQL Date 클래스를 임포트

public class ShippingUpdate {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService(); // ShippingService 객체 생성

        int shippingId = 14; // 수정할 배송 ID 설정 (예시)
        // 수정할 배송 정보 하드코딩
        Shipping shipping = Shipping.builder()
                .shippingId(shippingId) // 배송 ID 설정
                .shippingAdress("서울 송파구") // 수정된 배송 주소
                .shippingDate(Date.valueOf("2024-10-01")) // 수정된 배송 날짜
                .shippingStatus("배송완료") // 수정된 배송 상태
                .orderId(5) // 주문 ID 설정 (예시)
                .build();

        try (Connection con = ConnectionPool.create().getConnection()) { // ConnectionPool을 통해 연결 생성
            shippingService.updateShipping(shipping, con); // 배송 정보 수정
            System.out.println("배송 정보 수정 성공: " + shipping);
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }
}

package edu.sm.shipping;

import edu.sm.dto.Shipping; // Shipping DTO 임포트
import edu.sm.service.ShippingService; // ShippingService 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트;

public class ShippingSelectOne {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService(); // ShippingService 객체 생성

        // 하드코딩된 배송 ID
        Integer shippingId = 4; // 조회할 배송 ID 설정 (예시)

        try (Connection con = ConnectionPool.create().getConnection()) { // ConnectionPool을 통해 연결 생성
            Shipping shipping = shippingService.getShipping(shippingId, con); // 특정 배송 정보 조회
            if (shipping != null) {
                System.out.println("배송 정보: " + shipping); // 배송 정보 출력
            } else {
                System.out.println("배송 정보를 찾을 수 없습니다."); // 배송 정보가 없는 경우 메시지 출력
            }
        } catch (Exception e) {
            System.out.println("시스템 장애"); // 예외 발생 시 메시지 출력
            e.printStackTrace(); // 예외 스택 추적 출력
        }
    }
}

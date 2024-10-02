package edu.sm.shipping;

import edu.sm.service.ShippingService; // ShippingService 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트

public class ShippingDelete {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService(); // ShippingService 객체 생성
        int shippingIdToDelete = 5; // 삭제할 배송 ID (하드코딩)

        try (Connection con = ConnectionPool.create().getConnection()) { // ConnectionPool을 통해 연결 생성
            boolean isDeleted = shippingService.removeShipping(shippingIdToDelete, con); // 배송 정보 삭제

            if (isDeleted) {
                System.out.println("배송 정보가 성공적으로 삭제되었습니다. 삭제된 배송 ID: " + shippingIdToDelete);
            } else {
                System.out.println("해당 ID의 배송 정보가 존재하지 않거나 삭제에 실패했습니다. 배송 ID: " + shippingIdToDelete);
            }
        } catch (Exception e) {
            System.out.println("시스템 장애"); // 예외 발생 시 메시지 출력
            e.printStackTrace(); // 예외 스택 추적 출력
        }
    }
}

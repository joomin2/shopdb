package edu.sm.shipping;

import edu.sm.dto.Shipping; // Shipping DTO 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import edu.sm.service.ShippingService; // ShippingService 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.util.List; // 리스트를 사용하기 위한 인터페이스 임포트

public class ShippingSelect {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService(); // ShippingService 객체 생성

        try (Connection con = ConnectionPool.create().getConnection()) { // ConnectionPool을 통해 연결 생성
            List<Shipping> shippingList = shippingService.getAllShippings(con); // 모든 배송 정보 조회
            if (!shippingList.isEmpty()) {
                for (Shipping shipping : shippingList) {
                    System.out.println("배송 정보: " + shipping);
                }
            } else {
                System.out.println("배송 정보가 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }
}

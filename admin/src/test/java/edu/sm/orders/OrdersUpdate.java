package edu.sm.orders;

import edu.sm.dto.Orders; // Orders DTO 임포트
import edu.sm.service.OrdersService; // OrdersService 임포트

import java.sql.Date;

public class OrdersUpdate {
    public static void main(String[] args) {
        OrdersService ordersService = new OrdersService(); // OrdersService 객체 생성
        Orders orderToUpdate = Orders.builder()
                .orderId(1) // 수정할 주문 ID
                .orderDate(Date.valueOf("2023-05-01")) // 수정할 주문 날짜
                .totalPrice(30000) // 수정할 총 금액, DTO에 맞게 수정
                .status("배송중") // 수정할 상태
                .userId("user123") // 수정할 사용자 ID 추가
                .build();

        try {
            Orders updatedOrder = ordersService.updateOrder(orderToUpdate); // 주문 업데이트
            System.out.println("주문 수정 성공: " + updatedOrder);
        } catch (Exception e) {
            System.out.println("주문 수정 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

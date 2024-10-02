package edu.sm.orders;

import edu.sm.dto.Orders;
import edu.sm.service.OrdersService;

public class OrderSelectOne {
    public static void main(String[] args) {
        OrdersService ordersService = new OrdersService();
        int orderIdToSelect = 18; // 조회할 주문 ID

        try {
            Orders order = ordersService.getOrder(orderIdToSelect); // 단일 주문 조회
            if (order != null) {
                System.out.println("주문 조회 성공: " + order);
            } else {
                System.out.println("주문 조회 실패: ID " + orderIdToSelect + "를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("주문 조회 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package edu.sm.orderdetail;

import edu.sm.dto.OrderDetail;
import edu.sm.service.OrderDetailService;
import edu.sm.exception.NotFoundException;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class OrderDetailUpdate {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();
        ConnectionPool connectionPool;

        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // 업데이트할 주문 세부 정보의 ID 입력 받기
        System.out.print("업데이트할 주문 세부 정보의 odetail_id 입력: ");
        int odetailId = Integer.parseInt(scanner.nextLine());

        // 기존 주문 세부 정보를 가져와서 수정
        OrderDetail existingOrderDetail;
        try {
            existingOrderDetail = orderDetailService.get(odetailId); // 주문 세부 정보 조회
            if (existingOrderDetail == null) {
                System.err.println("주문 세부 정보가 존재하지 않습니다. ID: " + odetailId);
                return;
            }
        } catch (Exception e) {
            System.err.println("주문 세부 정보 조회 중 오류 발생: " + e.getMessage());
            return;
        }

        // 기존 정보를 기반으로 새로운 정보로 업데이트
        System.out.print("새로운 가격 입력: ");
        int price = Integer.parseInt(scanner.nextLine());

        System.out.print("주문 ID 입력: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("제품 ID 입력: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("새로운 수량 입력: ");
        int odetailQuantity = Integer.parseInt(scanner.nextLine());

        OrderDetail updatedOrderDetail = OrderDetail.builder()
                .odetail_id(odetailId) // 기존 ID 유지
                .price(price) // 사용자 입력으로 새로운 가격 설정
                .order_id(orderId) // 사용자 입력으로 새로운 주문 ID 설정
                .product_id(productId) // 사용자 입력으로 새로운 제품 ID 설정
                .odetail_quantity(odetailQuantity) // 사용자 입력으로 새로운 수량 설정
                .build();

        try (Connection con = connectionPool.getConnection()) {
            OrderDetail result = orderDetailService.update(updatedOrderDetail); // 업데이트 메서드 호출
            System.out.println("업데이트된 주문 세부 정보: " + result);
        } catch (NotFoundException e) {
            System.err.println("주문 세부 정보가 존재하지 않습니다: " + e.getMessage());
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

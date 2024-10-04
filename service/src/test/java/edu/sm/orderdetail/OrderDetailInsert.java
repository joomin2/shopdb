package edu.sm.orderdetail;

import edu.sm.dto.OrderDetail;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.exception.NotFoundException; // 새로운 예외 추가
import edu.sm.service.OrderDetailService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class OrderDetailInsert {
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
        int odetailId = -1; // 초기화

        // 중복 ID 확인을 위한 루프
        boolean isUniqueId = false;
        while (!isUniqueId) {
            System.out.print("odetail_id 입력: ");
            odetailId = Integer.parseInt(scanner.nextLine());

            try (Connection con = connectionPool.getConnection()) {
                // ID 중복 여부 확인
                if (orderDetailService.existsById(odetailId, con)) {
                    System.err.println("ID가 이미 존재합니다. 다른 ID를 입력하세요.");
                } else {
                    isUniqueId = true; // 고유 ID 확인
                }
            } catch (SQLException e) {
                System.err.println("SQL 오류 발생: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("시스템 장애 발생: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // 나머지 값 입력 받기
        System.out.print("가격 입력: ");
        int price = Integer.parseInt(scanner.nextLine());

        System.out.print("주문 ID 입력: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("제품 ID 입력: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("수량 입력: ");
        int odetailQuantity = Integer.parseInt(scanner.nextLine());

        // 새로운 주문 세부 정보 객체 생성
        OrderDetail newOrderDetail = OrderDetail.builder()
                .odetail_id(odetailId) // 사용자 입력 값 사용
                .price(price) // 사용자 입력 값 사용
                .order_id(orderId) // 사용자 입력 값 사용
                .product_id(productId) // 사용자 입력 값 사용
                .odetail_quantity(odetailQuantity) // 사용자 입력 값 사용
                .build();

        try (Connection con = connectionPool.getConnection()) {
            OrderDetail addedOrderDetail = orderDetailService.add(newOrderDetail); // add 메서드 호출
            System.out.println("추가된 주문 세부 정보: " + addedOrderDetail);
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

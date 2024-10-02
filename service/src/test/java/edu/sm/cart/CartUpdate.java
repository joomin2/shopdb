package edu.sm.cart;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.service.CartService;

import java.sql.Connection;
import java.sql.DriverManager;

public class CartUpdate {
    public static void main(String[] args) {
        // Database connection variables
        String url = "jdbc:mysql://210.119.34.205/shopdb"; // 데이터베이스 URL
        String user = "smuser"; // 데이터베이스 사용자
        String password = "111111"; // 데이터베이스 비밀번호

        // 데이터베이스 연결 변수
        Connection connection = null;
        CartDao cartDao = new CartDao(); // CartDao 인스턴스 생성

        try {
            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);
            CartService cartService = new CartService(cartDao, connection); // CartService 생성

            // Cart 객체 생성
            Cart cartToUpdate = Cart.builder()
                    .quantity(3) // 수정할 수량 설정
                    .sbagId(1) // 업데이트할 장바구니 ID
                    .build();

            // 장바구니 업데이트 호출
            Cart updatedCart = cartService.updateCart(cartToUpdate);
            System.out.println("장바구니 수정 성공: " + updatedCart);
        } catch (Exception e) {
            System.out.println("장바구니 수정 실패: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 연결 해제
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

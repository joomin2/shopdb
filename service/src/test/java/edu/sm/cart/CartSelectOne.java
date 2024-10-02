package edu.sm.cart;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.service.CartService;

import java.sql.Connection;
import java.sql.DriverManager;

public class CartSelectOne {
    public static void main(String[] args) {
        // 데이터베이스 연결 설정
        String url = "jdbc:mysql://210.119.34.205/shopdb"; // 데이터베이스 URL
        String user = "smuser"; // 데이터베이스 사용자
        String password = "111111"; // 데이터베이스 비밀번호

        // 데이터베이스 연결 변수
        CartDao cartDao = new CartDao();
        Connection connection = null;

        int sbagIdToSelect = 38; // 조회할 장바구니 ID

        try {
            // 데이터베이스 연결
            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);
            CartService cartService = new CartService(cartDao, connection); // CartService 생성


            // 특정 장바구니 조회
            Cart cart = cartService.getCart(sbagIdToSelect); // getCartById 메서드가 존재해야 합니다.
            if (cart != null) {
                System.out.println("장바구니 조회 성공: " + cart);
            } else {
                System.out.println("장바구니 조회 실패: ID " + sbagIdToSelect + "를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("장바구니 조회 중 오류 발생: " + e.getMessage());
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

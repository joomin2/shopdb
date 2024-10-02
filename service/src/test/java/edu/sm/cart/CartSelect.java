package edu.sm.cart;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.service.CartService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class CartSelect {
    public static void main(String[] args) {
        // 데이터베이스 연결 설정
        String url = "jdbc:mysql://210.119.34.205/shopdb"; // 데이터베이스 URL
        String user = "smuser"; // 데이터베이스 사용자
        String password = "111111"; // 데이터베이스 비밀번호

        // 데이터베이스 연결 변수
        CartDao cartDao = new CartDao();
        Connection connection = null;


        try {
            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);
            CartService cartService = new CartService(cartDao, connection); // CartService 생성

            // 모든 장바구니 레코드 조회
            List<Cart> carts = cartService.getAllCarts(); // getAllCarts 메서드가 존재해야 합니다.
            for (Cart cart : carts) {
                System.out.println("장바구니 정보: " + cart);
            }
        } catch (Exception e) {
            System.out.println("장바구니 조회 실패: " + e.getMessage());
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

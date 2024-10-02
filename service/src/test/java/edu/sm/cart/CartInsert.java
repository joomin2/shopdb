package edu.sm.cart;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.CartService; // Assuming you have a CartService class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class CartInsert {
    public static void main(String[] args) {
        // Create a Cart instance
        Cart cart = Cart.builder()
                .createAt(new Date(System.currentTimeMillis()))// 현재 날짜 설정
                .quantity(9) // 수량 설정
                .productId(12) // 예시 제품 ID
                .userId("eva23") // 예시 사용자 ID
                .build();

        // Database connection variables
        String url = "jdbc:mysql://210.119.34.205/shopdb"; // 데이터베이스 URL
        String user = "smuser"; // 데이터베이스 사용자
        String password = "111111"; // 데이터베이스 비밀번호

        // CartDao 인스턴스 생성
        CartDao cartDao = new CartDao();

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            // 장바구니 추가 시도
            Cart addedCart = cartDao.insert(cart, con);
            System.out.println("장바구니 추가 성공: " + addedCart);
        }  catch (SQLException e) {
            System.out.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("장바구니 추가 실패: " + e.getMessage());
        }
    }
}

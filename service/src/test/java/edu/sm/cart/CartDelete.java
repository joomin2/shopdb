package edu.sm.cart;

import edu.sm.dao.CartDao;
import edu.sm.service.CartService;

import java.sql.Connection;
import java.sql.DriverManager;

public class CartDelete {
    public static void main(String[] args) {
        // 데이터베이스 연결 설정
        String url = "jdbc:mysql://210.119.34.205/shopdb"; // 데이터베이스 URL
        String user = "smuser"; // 데이터베이스 사용자
        String password = "111111"; // 데이터베이스 비밀번호

        // 데이터베이스 연결 변수
        CartDao cartDao = new CartDao();
        Connection connection = null;

        // 삭제할 장바구니 ID 하드코딩
        Integer sbagId = 1; // 테스트할 장바구니 ID

        try {
            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);

            // CartService 생성 후 connection 설정
            CartService cartService = new CartService(cartDao, connection); // CartService 생성
            // 장바구니 삭제 호출
            boolean isDeleted = cartService.removeCart(sbagId);
            if (isDeleted) {
                System.out.println("장바구니 삭제 성공: ID " + sbagId);
            } else {
                System.out.println("장바구니 삭제 실패: ID " + sbagId + "를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("장바구니 삭제 실패: " + e.getMessage());
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

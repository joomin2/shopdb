package edu.sm.wishlist;


import edu.sm.dto.User;
import edu.sm.dto.Wishlist;
import edu.sm.service.UserService;
import edu.sm.service.WishlistService;

import java.util.Scanner;

public class WishlistInsert {
    public static void main(String[] args) throws Exception {
        // Scanner로 사용자 입력 받기
        Scanner scanner = new Scanner(System.in);

        // 사용자로부터 user_id 입력받기
        System.out.print("유저id를 입력하시오: ");
        String userId = scanner.nextLine();

        // 사용자로부터 product_id 입력받기
        System.out.print("위시리스트에 넣을 상품 id를 입력하시오: ");
        int productId = scanner.nextInt();  // 숫자 입력받기

        // WishlistService 인스턴스 생성
        WishlistService wishlistService = new WishlistService();

        // 입력받은 데이터로 Wishlist 객체 생성
        Wishlist wishlist = Wishlist.builder()
                .user_id(userId)          // 입력받은 user_id 설정
                .product_id(productId)     // 입력받은 product_id 설정
                .build();

        // WishlistService를 이용해 데이터 삽입
        wishlistService.add(wishlist);

        // 자원 해제
        scanner.close();
    }
}


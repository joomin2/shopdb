package edu.sm.wishlist;

import edu.sm.dto.User;
import edu.sm.dto.Wishlist;
import edu.sm.service.UserService;
import edu.sm.service.WishlistService;

import java.util.Scanner;

public class WishlistSelectOne {
    public static void main(String[] args) {
        WishlistService wishlistService = new WishlistService();

        // 사용자로부터 ID 입력받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("위시리스트를 조회할 id를 입력하시오.: ");
        String id = scanner.nextLine();  // 입력받은 ID를 변수에 저장

        Wishlist wishlist = null;

        try {
            wishlist = wishlistService.get(id);  // 입력받은 ID로 데이터 조회

            // 조회된 데이터 출력
            if (wishlist != null) {
                System.out.println("User ID: " + wishlist.getUser_id());
                System.out.println("Product ID: " + wishlist.getProduct_id());
                System.out.println("Created At: " + (wishlist.getCreated_at() != null ? wishlist.getCreated_at().toString() : "N/A"));
            } else {
                System.out.println("No wishlist found for the given User ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Scanner 자원 해제
        }
    }
}


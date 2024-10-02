package edu.sm.wishlist;

import edu.sm.service.UserService;
import edu.sm.service.WishlistService;

import java.util.Scanner;

public class WishlistDelete {
    public static void main(String[] args) {
        WishlistService wishlistService = new WishlistService();

        // Scanner로 사용자 ID 입력받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("위시리스트에서 삭제할 id를 입력하시오: ");
        String id = scanner.nextLine();  // 사용자로부터 ID 입력받기

        try {
            wishlistService.remove(id);  // 입력받은 ID로 위시리스트 삭제
            System.out.println("Wishlist for User ID \"" + id + "\" has been successfully deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Scanner 자원 해제
        }
    }
}


package edu.sm.product;

import edu.sm.service.ProductService;

import java.util.Scanner;

public class ProductDelete {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("삭제할 제품 ID를 입력하세요: ");
        int productIdToDelete = scanner.nextInt();

        try {
            boolean isDeleted = productService.remove(productIdToDelete); // 삭제 메서드 호출
            if (isDeleted) {
                System.out.println("상품 삭제 성공: ID " + productIdToDelete);
            } else {
                System.out.println("상품 삭제 실패: ID " + productIdToDelete + "를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("상품 삭제 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

        scanner.close();
    }
}

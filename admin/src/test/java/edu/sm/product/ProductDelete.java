package edu.sm.product;

import edu.sm.service.ProductService;

public class ProductDelete {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        int productIdToDelete = 41; // 삭제할 제품 ID

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
    }
}

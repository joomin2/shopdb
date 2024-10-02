package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

public class ProductSelectOne {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        int productIdToSelect = 38; // 조회할 제품 ID

        try {
            Product product = productService.get(productIdToSelect); // 단일 제품 조회
            if (product != null) {
                System.out.println("상품 조회 성공: " + product);
            } else {
                System.out.println("상품 조회 실패: ID " + productIdToSelect + "를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("상품 조회 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

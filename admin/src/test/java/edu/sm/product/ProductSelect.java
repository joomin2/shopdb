package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductSelect {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        try {
            // 모든 제품 레코드를 조회
            List<Product> products = productService.get();
            for (Product product : products) {
                System.out.println("상품 정보: " + product);
            }
        } catch (Exception e) {
            System.out.println("상품 조회 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.sql.Date;

public class ProductUpdate {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product productToUpdate = Product.builder()
                //.productId(4) // 수정할 제품 ID
                .productName("수정된 제품") // 수정할 제품 이름
                .productPrice(15000) // 수정할 제품 가격
                .productImg("updated.jpg") // 수정할 제품 이미지
                .cateno(1) // 수정할 카테고리 번호
                .productDate(Date.valueOf("2022-03-01")) // 수정할 제품 날짜
                .build();

        try {
            Product updatedProduct = productService.modify(productToUpdate);
            System.out.println("상품 수정 성공: " + updatedProduct);
        } catch (Exception e) {
            System.out.println("상품 수정 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

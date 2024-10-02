package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.ProductService;

import java.sql.Date;

public class ProductInsert {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = Product.builder()
                .productName("joominitem2")
                .productPrice(10000)
                .productImg("joomintem.jpg")
                .cateno(2)
                .productDate(Date.valueOf("2022-03-01")) // java.sql.Date 사용
                .build();

        try {
            Product addedProduct = productService.add(product);
            System.out.println("상품 등록 성공: " + addedProduct);
        } catch (DuplicatedIdException e) {
            System.out.println("상품 등록 실패: ID가 중복되었습니다: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("상품 등록 실패: " + e.getMessage());
        }
    }
}

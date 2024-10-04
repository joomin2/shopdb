package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.sql.Date;
import java.util.Scanner;

public class ProductUpdate {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("수정할 제품 ID를 입력하세요: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        System.out.print("수정할 제품 이름을 입력하세요: ");
        String productName = scanner.nextLine();

        System.out.print("수정할 제품 가격을 입력하세요: ");
        int productPrice = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        System.out.print("수정할 제품 이미지를 입력하세요: ");
        String productImg = scanner.nextLine();

        System.out.print("수정할 카테고리 번호를 입력하세요: ");
        int cateno = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        System.out.print("수정할 제품 날짜를 입력하세요 (YYYY-MM-DD 형식): ");
        String productDateStr = scanner.nextLine();
        Date productDate = Date.valueOf(productDateStr);

        // 제품 수정 정보 생성
        Product productToUpdate = Product.builder()
               // .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productImg(productImg)
                .cateno(cateno)
                .productDate(productDate)
                .build();

        try {
            Product updatedProduct = productService.modify(productToUpdate);
            System.out.println("상품 수정 성공: " + updatedProduct);
        } catch (Exception e) {
            System.out.println("상품 수정 실패: " + e.getMessage());
            e.printStackTrace();
        }

        scanner.close();
    }
}

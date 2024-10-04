package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.ProductService;

import java.sql.Date;
import java.util.Scanner;

public class ProductInsert {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 상품 등록 ===");
        System.out.print("상품 이름을 입력하세요: ");
        String productName = scanner.nextLine();
        System.out.print("상품 가격을 입력하세요: ");
        int productPrice = scanner.nextInt();
        scanner.nextLine();  // 버퍼 비우기
        System.out.print("상품 이미지를 입력하세요: ");
        String productImg = scanner.nextLine();
        System.out.print("카테고리 번호를 입력하세요: ");
        int cateno = scanner.nextInt();
        scanner.nextLine();  // 버퍼 비우기
        System.out.print("상품 등록 날짜를 입력하세요 (YYYY-MM-DD): ");
        String productDateStr = scanner.nextLine();
        Date productDate = Date.valueOf(productDateStr);

        Product product = Product.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productImg(productImg)
                .cateno(cateno)
                .productDate(productDate)
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

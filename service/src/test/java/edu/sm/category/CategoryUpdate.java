package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.exception.NotFoundException;
import edu.sm.service.CategoryService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CategoryUpdate {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        ConnectionPool connectionPool;

        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // 업데이트할 카테고리 번호 입력 받기
        System.out.print("업데이트할 카테고리 번호 입력: ");
        int cateno = Integer.parseInt(scanner.nextLine());

        // 사용자로부터 카테고리 정보를 입력받기
        System.out.print("새로운 cateno1 입력: ");
        String cateno1 = scanner.nextLine();

        System.out.print("새로운 cateno2 입력: ");
        String cateno2 = scanner.nextLine();

        System.out.print("새로운 catename 입력: ");
        String catename = scanner.nextLine();

        // 업데이트할 카테고리 객체 생성
        Category updatedCategory = Category.builder()
                .cateno(cateno) // 업데이트할 카테고리 번호
                .cateno1(cateno1) // 사용자 입력 값 사용
                .cateno2(cateno2) // 사용자 입력 값 사용
                .catename(catename) // 사용자 입력 값 사용
                .build();

        try (Connection con = connectionPool.getConnection()) {
            Category result = categoryService.update(updatedCategory, con);
            System.out.println("업데이트된 카테고리: " + result);
        } catch (NotFoundException e) {
            System.err.println("카테고리를 찾을 수 없습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close(); // Scanner 닫기
        }
    }
}

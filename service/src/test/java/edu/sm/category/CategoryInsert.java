package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.CategoryService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CategoryInsert {
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

        // 사용자로부터 카테고리 정보를 입력받기
        System.out.print("cateno1 입력: ");
        String cateno1 = scanner.nextLine();

        System.out.print("cateno2 입력: ");
        String cateno2 = scanner.nextLine();

        System.out.print("catename 입력: ");
        String catename = scanner.nextLine();

        // cateno는 자동 생성되므로 설정하지 않음
        Category newCategory = Category.builder()
                .cateno1(cateno1) // 사용자 입력 값 사용
                .cateno2(cateno2) // 사용자 입력 값 사용
                .catename(catename) // 사용자 입력 값 사용
                .build();

        try (Connection con = connectionPool.getConnection()) {
            Category addedCategory = categoryService.add(newCategory, con);
            System.out.println("추가된 카테고리: " + addedCategory);
        } catch (DuplicatedIdException e) {
            System.err.println("중복된 ID로 인한 예외 발생: " + e.getMessage());
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

package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryGetAll {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        try (Connection con = connectionPool.getConnection()) {
            List<Category> categoryList = categoryService.getAll(con);
            System.out.println("전체 카테고리:");
            for (Category category : categoryList) {
                System.out.println(category); // 각 카테고리를 한 줄씩 출력
            }
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

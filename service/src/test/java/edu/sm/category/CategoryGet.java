package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.exception.NotFoundException;
import edu.sm.service.CategoryService;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class CategoryGet {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        int categoryId = 1; // 조회할 카테고리 ID

        try (Connection con = connectionPool.getConnection()) {
            Category retrievedCategory = categoryService.getById(categoryId, con);
            System.out.println("가져온 카테고리: " + retrievedCategory);
        } catch (NotFoundException e) {
            System.err.println("카테고리를 찾을 수 없습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

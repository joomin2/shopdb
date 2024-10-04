package edu.sm.category;

import edu.sm.service.CategoryService;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class CategoryDelete {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("연결 풀 초기화 오류: " + e.getMessage());
            return;
        }

        Integer catenoToDelete = 41; // 삭제할 카테고리 번호

        try (Connection con = connectionPool.getConnection()) {
            boolean isDeleted = categoryService.delete(catenoToDelete, con); // 카테고리 삭제
            if (isDeleted) {
                System.out.println("카테고리 번호 " + catenoToDelete + "가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("카테고리 번호 " + catenoToDelete + "를 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            System.err.println("SQL 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("시스템 장애 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package edu.sm.service;

import edu.sm.dao.CategoryDao; // CategoryDao 임포트
import edu.sm.dto.Category; // Category DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.exception.NotFoundException; // 찾을 수 없는 예외 처리 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.SQLException; // SQL 예외 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class CategoryService {

    private CategoryDao categoryDao; // CategoryDao 인스턴스

    // 생성자
    public CategoryService() {
        this.categoryDao = new CategoryDao(); // CategoryDao 초기화
    }

    // 카테고리 추가 메서드
    public Category addCategory(Category category, Connection con) throws Exception {
        try {
            return categoryDao.insert(category, con); // 카테고리 추가
        } catch (DuplicatedIdException e) {
            throw new DuplicatedIdException("카테고리 추가 중 중복된 ID 발생"); // 중복 ID 예외 발생
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }

    // 카테고리 업데이트 메서드
    public Category updateCategory(Category category, Connection con) throws Exception {
        try {
            return categoryDao.update(category, con); // 카테고리 업데이트
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }

    // 카테고리 삭제 메서드
    public boolean deleteCategory(Integer cateno, Connection con) throws Exception {
        try {
            return categoryDao.delete(cateno, con); // 카테고리 삭제
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }

    // 특정 카테고리 조회 메서드
    public Category getCategory(Integer cateno, Connection con) throws Exception {
        try {
            Category category = categoryDao.select(cateno, con); // 카테고리 조회
            if (category == null) {
                throw new NotFoundException("카테고리를 찾을 수 없습니다."); // 카테고리가 없을 경우 예외 처리
            }
            return category;
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }

    // 모든 카테고리 조회 메서드
    public List<Category> getAllCategories(Connection con) throws Exception {
        try {
            return categoryDao.select(con); // 모든 카테고리 조회
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }
}

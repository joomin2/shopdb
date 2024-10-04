package edu.sm.service;

import edu.sm.dao.CategoryDao; // CategoryDao 임포트
import edu.sm.dto.Category; // Category DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.exception.NotFoundException; // 찾을 수 없는 예외 처리 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement;
import java.sql.SQLException; // SQL 예외 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class CategoryService {

    private CategoryDao categoryDao; // CategoryDao 인스턴스

    // 생성자
    public CategoryService() {
        this.categoryDao = new CategoryDao(); // CategoryDao 초기화
    }

    // 카테고리 추가 메서드
    public Category add(Category category, Connection con) throws Exception {
        // 중복 체크
        Category existingCategory = categoryDao.select(category.getCateno(), con);
        if (existingCategory != null) {
            throw new DuplicatedIdException("카테고리 ID가 중복됩니다: " + category.getCateno());
        }

        try {
            return categoryDao.insert(category, con); // 카테고리 추가
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }


    // 카테고리 업데이트 메서드
    public Category update(Category category, Connection con) throws Exception {
        // 카테고리가 존재하는지 확인
        Category existingCategory = categoryDao.select(category.getCateno(), con);
        if (existingCategory == null) {
            throw new NotFoundException("업데이트할 카테고리를 찾을 수 없습니다: " + category.getCateno());
        }

        try {
            return categoryDao.update(category, con); // 카테고리 업데이트
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }


    // 카테고리 삭제 메서드
    public boolean delete(Integer cateno, Connection con) throws Exception {
        try {
            return categoryDao.delete(cateno, con); // 카테고리 삭제
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }

    // 특정 카테고리 조회 메서드
    public Category getById(Integer cateno, Connection con) throws Exception {
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
    public List<Category> getAll(Connection con) throws Exception {
        try {
            return categoryDao.select(con); // 모든 카테고리 조회
        } catch (SQLException e) {
            throw new Exception("SQL 오류 발생: " + e.getMessage()); // SQL 오류 처리
        }
    }


}

// CategoryDao.java
package edu.sm.dao;

import edu.sm.dto.Category; // Category DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement; // PreparedStatement를 위한 클래스 임포트
import java.sql.ResultSet; // 결과 집합을 위한 클래스 임포트
import java.sql.SQLIntegrityConstraintViolationException; // SQL 무결성 제약 위반 예외 클래스 임포트
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class CategoryDao implements Dao<Integer, Category> {

    // 카테고리 추가 메서드
    @Override
    public Category insert(Category category, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.insertCategory); // SQL 쿼리 준비
            ps.setString(1, category.getCatename()); // 카테고리 이름 설정
            ps.setString(2, category.getCateno1()); // 카테고리1 설정
            ps.setString(3, category.getCateno2()); // 카테고리2 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch(SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001"); // 중복 ID 예외 발생
        } catch(Exception e) {
            throw e; // 다른 예외 발생
        } finally {
            if(ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return category; // 추가한 카테고리 반환
    }

    // 카테고리 업데이트 메서드
    @Override
    public Category update(Category category, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.updateCategory); // SQL 쿼리 준비
            ps.setString(1, category.getCatename()); // 카테고리 이름 설정
            ps.setString(2, category.getCateno1()); // 카테고리1 설정
            ps.setString(3, category.getCateno2()); // 카테고리2 설정
            ps.setInt(4, category.getCateno()); // 카테고리 번호 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch(Exception e) {
            throw e; // 예외 발생
        } finally {
            if(ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return category; // 업데이트한 카테고리 반환
    }

    // 카테고리 삭제 메서드
    @Override
    public Boolean delete(Integer cateno, Connection con) throws Exception {
        Boolean isDeleted = false; // 삭제 여부 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.deleteCategory); // SQL 쿼리 준비
            ps.setInt(1, cateno); // 카테고리 번호 설정
            int result = ps.executeUpdate(); // 쿼리 실행
            if(result == 1) {
                isDeleted = true; // 삭제 성공
            }
        } catch(Exception e) {
            throw e; // 예외 발생
        } finally {
            if(ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return isDeleted; // 삭제 여부 반환
    }

    // 특정 카테고리 조회 메서드
    @Override
    public Category select(Integer cateno, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        Category category = null; // 카테고리 객체 초기화
        try {
            ps = con.prepareStatement(Sql.selectOneCategory); // SQL 쿼리 준비
            ps.setInt(1, cateno); // 카테고리 번호 설정
            rs = ps.executeQuery(); // 쿼리 실행
            if(rs.next()) { // 결과가 있을 경우
                category = new Category(); // 카테고리 객체 생성
                category.setCateno(rs.getInt("cateno")); // 카테고리 번호 설정
                category.setCateno1(rs.getString("cateno1")); // 카테고리1 설정
                category.setCateno2(rs.getString("cateno2")); // 카테고리2 설정
                category.setCatename(rs.getString("catename")); // 카테고리 이름 설정
            }
        } catch(Exception e) {
            throw e; // 예외 발생
        } finally {
            if(ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if(rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return category; // 조회한 카테고리 반환
    }

    // 모든 카테고리 조회 메서드
    @Override
    public List<Category> select(Connection con) throws Exception {
        List<Category> categoryList = new ArrayList<>(); // 카테고리 리스트 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        try {
            ps = con.prepareStatement(Sql.selectCategory); // SQL 쿼리 준비
            rs = ps.executeQuery(); // 쿼리 실행
            while(rs.next()) { // 결과가 있는 동안 반복
                Category category = new Category(); // 카테고리 객체 생성
                category.setCateno(rs.getInt("cateno")); // 카테고리 번호 설정
                category.setCateno1(rs.getString("cateno1")); // 카테고리1 설정
                category.setCateno2(rs.getString("cateno2")); // 카테고리2 설정
                category.setCatename(rs.getString("catename")); // 카테고리 이름 설정
                categoryList.add(category); // 리스트에 추가
            }
        } catch(Exception e) {
            throw e; // 예외 발생
        } finally {
            if(ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if(rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return categoryList; // 카테고리 리스트 반환
    }
}

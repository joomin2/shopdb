package edu.sm.dao;

import edu.sm.dto.Product; // Product DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.*;
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class ProductDao implements Dao<Integer, Product> {

    // 제품 추가 메서드
    @Override
    public Product insert(Product product, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.insertProduct, Statement.RETURN_GENERATED_KEYS); // SQL 쿼리 준비
            ps.setString(1, product.getProductName()); // 제품 이름 설정
            ps.setInt(2, product.getProductPrice()); // 제품 가격 설정
            ps.setString(3, product.getProductImg()); // 제품 이미지 설정
            ps.setInt(4, product.getCateno()); // 카테고리 번호 설정
            ps.setDate(5, product.getProductDate()); // 제품 등록 날짜 설정
            ps.executeUpdate(); // 쿼리 실행

            // 추가된 제품 ID 가져오기
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                // productId 설정 부분 삭제
                // product.setProductId(generatedKeys.getInt(1)); // 제품 ID 설정
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // 23000은 무결성 제약 조건 위반
                throw new DuplicatedIdException("EX0001"); // 중복 ID 예외 발생
            } else {
                throw e; // 다른 예외 발생
            }
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return product; // 추가한 제품 정보 반환
    }

    // 제품 업데이트 메서드
    @Override
    public Product update(Product product, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.updateProduct); // SQL 쿼리 준비
            ps.setString(1, product.getProductName()); // 제품 이름 설정
            ps.setInt(2, product.getProductPrice()); // 제품 가격 설정
            ps.setString(3, product.getProductImg()); // 제품 이미지 설정
            ps.setInt(4, product.getCateno()); // 카테고리 번호 설정
            ps.setDate(5, product.getProductDate()); // 제품 등록 날짜 설정
            // productId 설정 부분 삭제
            // ps.setInt(6, product.getProductId()); // 제품 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return product; // 업데이트한 제품 정보 반환
    }

    @Override
    public Boolean delete(Integer integer, Connection conn) throws Exception {
        return false;
    }

    // 특정 제품 조회 메서드
    @Override
    public Product select(Integer productId, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        Product product = null; // 제품 객체 초기화
        try {
            ps = con.prepareStatement(Sql.selectOneProduct); // SQL 쿼리 준비
            ps.setInt(1, productId); // 제품 ID 설정
            rs = ps.executeQuery(); // 쿼리 실행
            if (rs.next()) { // 결과가 있을 경우
                product = new Product(); // 제품 객체 생성
                // productId 설정 부분 삭제
                // product.setProductId(rs.getInt("product_id")); // 제품 ID 설정
                product.setProductName(rs.getString("product_name")); // 제품 이름 설정
                product.setProductPrice(rs.getInt("product_price")); // 제품 가격 설정
                product.setProductImg(rs.getString("product_img")); // 제품 이미지 설정
                product.setCateno(rs.getInt("cateno")); // 카테고리 번호 설정
                product.setProductDate(rs.getDate("product_date")); // 제품 등록 날짜 설정
            } else {
                throw new Exception("Product not found with ID: " + productId); // 제품이 없을 경우 예외 처리
            }
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if (rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return product; // 조회한 제품 정보 반환
    }

    // 모든 제품 조회 메서드
    @Override
    public List<Product> select(Connection con) throws Exception {
        List<Product> productList = new ArrayList<>(); // 제품 리스트 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        try {
            ps = con.prepareStatement(Sql.selectProduct); // SQL 쿼리 준비
            rs = ps.executeQuery(); // 쿼리 실행
            while (rs.next()) { // 결과가 있는 동안 반복
                Product product = new Product(); // 제품 객체 생성
                // productId 설정 부분 삭제
                // product.setProductId(rs.getInt("product_id")); // 제품 ID 설정
                product.setProductName(rs.getString("product_name")); // 제품 이름 설정
                product.setProductPrice(rs.getInt("product_price")); // 제품 가격 설정
                product.setProductImg(rs.getString("product_img")); // 제품 이미지 설정
                product.setCateno(rs.getInt("cateno")); // 카테고리 번호 설정
                product.setProductDate(rs.getDate("product_date")); // 제품 등록 날짜 설정
                productList.add(product); // 리스트에 추가
            }
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if (rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return productList; // 제품 리스트 반환
    }
}

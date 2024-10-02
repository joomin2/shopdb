package edu.sm.dao;

import edu.sm.dto.Cart;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements Dao<Integer, Cart> { // K는 Integer, V는 Cart로 설정

    // 장바구니 추가 메서드
    @Override
    public Cart insert(Cart cart, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.insertCart); // SQL 쿼리 준비
            ps.setDate(1, new Date(cart.getCreateAt().getTime())); // 생성 날짜 설정
            ps.setInt(2, cart.getQuantity()); // 수량 설정
            ps.setInt(3, cart.getProductId()); // 제품 ID 설정
            ps.setString(4, cart.getUserId()); // 사용자 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        }  // 중복 ID 예외 발생
         catch (Exception e) {
            throw e; // 다른 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return cart; // 추가한 장바구니 반환
    }

    // 장바구니 업데이트 메서드
    @Override
    public Cart update(Cart cart, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.updateCart); // SQL 쿼리 준비
            ps.setInt(1, cart.getQuantity());
           ps.setInt(2, cart.getSbagId()); // 장바구니 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return cart; // 업데이트한 장바구니 반환
    }

    // 장바구니 삭제 메서드
    @Override
    public boolean delete(Integer sbagId, Connection con) throws Exception {
        boolean isDeleted = false; // 삭제 여부 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.deleteCart); // SQL 쿼리 준비
            ps.setInt(1, sbagId); // 장바구니 ID 설정
            int result = ps.executeUpdate(); // 쿼리 실행
            isDeleted = (result == 1); // 삭제 성공 여부 설정
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return isDeleted; // 삭제 여부 반환
    }

    // 특정 장바구니 조회 메서드
    @Override
    public Cart select(Integer sbagId, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        Cart cart = null; // 장바구니 객체 초기화
        try {
            ps = con.prepareStatement(Sql.selectOneCart); // SQL 쿼리 준비
            ps.setInt(1, sbagId); // 장바구니 ID 설정
            rs = ps.executeQuery(); // 쿼리 실행
            if (rs.next()) { // 결과가 있을 경우
                cart = new Cart(); // 장바구니 객체 생성
                cart.setSbagId(rs.getInt("sbag_id"));
                cart.setCreateAt(rs.getDate("created_at")); // 생성 날짜 설정
                cart.setQuantity(rs.getInt("quantity")); // 수량 설정
                cart.setProductId(rs.getInt("product_id")); // 제품 ID 설정
                cart.setUserId(rs.getString("user_id")); // 사용자 ID 설정
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
        return cart; // 조회한 장바구니 반환
    }

    // 모든 장바구니 조회 메서드
    @Override
    public List<Cart> select(Connection con) throws Exception {
        List<Cart> cartList = new ArrayList<>(); // 장바구니 리스트 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        try {
            ps = con.prepareStatement(Sql.selectCart); // SQL 쿼리 준비
            rs = ps.executeQuery(); // 쿼리 실행
            while (rs.next()) { // 결과가 있는 동안 반복
                Cart cart = new Cart(); // 장바구니 객체 생성
//                cart.setSbagId(rs.getInt("sbag_id")); // 장바구니 ID 설정
                cart.setCreateAt(rs.getDate("created_at")); // 생성 날짜 설정
                cart.setQuantity(rs.getInt("quantity")); // 수량 설정
                cart.setProductId(rs.getInt("product_id")); // 제품 ID 설정
                cart.setUserId(rs.getString("user_id")); // 사용자 ID 설정
                cartList.add(cart); // 리스트에 추가
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
        return cartList; // 장바구니 리스트 반환
    }
}

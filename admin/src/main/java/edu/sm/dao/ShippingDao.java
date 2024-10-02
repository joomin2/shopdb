package edu.sm.dao;

import edu.sm.dto.Shipping; // Shipping DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement; // PreparedStatement를 위한 클래스 임포트
import java.sql.ResultSet; // 결과 집합을 위한 클래스 임포트
import java.sql.SQLIntegrityConstraintViolationException; // SQL 무결성 제약 위반 예외 클래스 임포트
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class ShippingDao implements Dao<Integer, Shipping> {

    // 배송 정보 추가 메서드
    @Override
    public Shipping insert(Shipping shipping, Connection con) throws Exception {
        String sql = Sql.insertShipping; // SQL 쿼리 가져오기
        try (PreparedStatement ps = con.prepareStatement(sql)) { // try-with-resources 사용
            ps.setString(1, shipping.getShippingAdress()); // 배송 주소 설정
            ps.setDate(2, shipping.getShippingDate()); // 배송 날짜 설정
            ps.setString(3, shipping.getShippingStatus()); // 배송 상태 설정
            ps.setInt(4, shipping.getOrderId()); // 주문 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001"); // 중복 ID 예외 발생
        }
        return shipping; // 추가한 배송 정보 반환
    }

    // 주문 ID 유효성 검사 메서드
    public Boolean isOrderIdValid(Integer orderId, Connection con) throws Exception {
        String sql = "SELECT COUNT(*) FROM orders WHERE order_id = ?"; // 주문 ID 확인 SQL 쿼리
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderId); // 주문 ID 설정
            try (ResultSet rs = ps.executeQuery()) { // 결과 집합 실행
                if (rs.next()) {
                    return rs.getInt(1) > 0; // 주문 ID 존재 여부 반환
                }
            }
        }
        return false; // 주문 ID가 존재하지 않으면 false 반환
    }

    // 배송 정보 업데이트 메서드
    @Override
    public Shipping update(Shipping shipping, Connection con) throws Exception {
        String sql = Sql.updateShipping; // SQL 쿼리 가져오기
        try (PreparedStatement ps = con.prepareStatement(sql)) { // try-with-resources 사용
            ps.setString(1, shipping.getShippingAdress()); // 배송 주소 설정
            ps.setDate(2, shipping.getShippingDate()); // 배송 날짜 설정
            ps.setString(3, shipping.getShippingStatus()); // 배송 상태 설정
            ps.setInt(4, shipping.getShippingId()); // 배송 ID 설정
            ps.executeUpdate(); // 쿼리 실행
        }
        return shipping; // 업데이트한 배송 정보 반환
    }

    // 배송 정보 삭제 메서드
    @Override
    public Boolean delete(Integer shippingId, Connection con) throws Exception {
        String sql = Sql.deleteShipping; // SQL 쿼리 가져오기
        try (PreparedStatement ps = con.prepareStatement(sql)) { // try-with-resources 사용
            ps.setInt(1, shippingId); // 배송 ID 설정
            return ps.executeUpdate() == 1; // 삭제 성공 여부 반환
        }
    }

    // 특정 배송 정보 조회 메서드
    @Override
    public Shipping select(Integer shippingId, Connection con) throws Exception {
        String sql = Sql.selectOneShipping; // SQL 쿼리 가져오기
        try (PreparedStatement ps = con.prepareStatement(sql)) { // try-with-resources 사용
            ps.setInt(1, shippingId); // 배송 ID 설정
            try (ResultSet rs = ps.executeQuery()) { // ResultSet을 자동으로 닫기
                if (rs.next()) { // 결과가 있을 경우
                    Shipping shipping = new Shipping(); // Shipping 객체 생성
                    shipping.setShippingId(rs.getInt("shipping_id")); // 배송 ID 설정
                    shipping.setShippingAdress(rs.getString("shipping_adress")); // 배송 주소 설정
                    shipping.setShippingDate(rs.getDate("shipping_date")); // 배송 날짜 설정
                    shipping.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
                    shipping.setShippingStatus(rs.getString("shipping_status")); // 배송 상태 설정
                    return shipping; // 조회한 배송 정보 반환
                }
            }
        }
        return null; // 결과가 없는 경우 null 반환
    }

    // 모든 배송 정보 조회 메서드
    @Override
    public List<Shipping> select(Connection con) throws Exception {
        List<Shipping> shippingList = new ArrayList<>(); // 배송 리스트 초기화
        String sql = Sql.selectShipping; // SQL 쿼리 가져오기
        try (PreparedStatement ps = con.prepareStatement(sql); // try-with-resources 사용
             ResultSet rs = ps.executeQuery()) { // ResultSet을 자동으로 닫기
            while (rs.next()) { // 결과가 있는 동안 반복
                Shipping shipping = new Shipping(); // Shipping 객체 생성
                shipping.setShippingId(rs.getInt("shipping_id")); // 배송 ID 설정
                shipping.setShippingAdress(rs.getString("shipping_adress")); // 배송 주소 설정
                shipping.setShippingDate(rs.getDate("shipping_date")); // 배송 날짜 설정
                shipping.setOrderId(rs.getInt("order_id")); // 주문 ID 설정
                shipping.setShippingStatus(rs.getString("shipping_status")); // 배송 상태 설정
                shippingList.add(shipping); // 리스트에 추가
            }
        }
        return shippingList; // 전체 배송 리스트 반환
    }
}

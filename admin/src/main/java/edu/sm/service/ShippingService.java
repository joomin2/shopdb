package edu.sm.service;

import edu.sm.dao.ShippingDao; // ShippingDao 임포트
import edu.sm.dto.Shipping; // Shipping DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class ShippingService {
    private ShippingDao shippingDao; // ShippingDao 객체

    // 생성자: ShippingDao 초기화
    public ShippingService() {
        this.shippingDao = new ShippingDao(); // ShippingDao 초기화
    }

    // 배송 정보 추가 메서드
    public Shipping addShipping(Shipping shipping, Connection con) throws Exception {
        return shippingDao.insert(shipping, con); // 배송 정보 추가
    }

    // 배송 정보 업데이트 메서드
    public Shipping updateShipping(Shipping shipping, Connection con) throws Exception {
        return shippingDao.update(shipping, con); // 배송 정보 업데이트
    }

    // 배송 정보 삭제 메서드
    public boolean removeShipping(Integer shippingId, Connection con) throws Exception {
        return shippingDao.delete(shippingId, con); // 배송 정보 삭제
    }

    // 특정 배송 정보 조회 메서드
    public Shipping getShipping(Integer shippingId, Connection con) throws Exception {
        return shippingDao.select(shippingId, con); // 특정 배송 정보 조회
    }

    // 모든 배송 정보 조회 메서드
    public List<Shipping> getAllShippings(Connection con) throws Exception {
        return shippingDao.select(con); // 모든 배송 정보 조회
    }
}

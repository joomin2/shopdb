package edu.sm.address;

import edu.sm.dao.AddressDao; // AddressDao 임포트
import edu.sm.dto.Address; // Address DTO 임포트
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.AddressService;
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.SQLException; // SQLException 클래스 임포트


public class AddressInsert {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        Address address = Address.builder()
                .addName("박주민 집")
                .address("인천 서구 가좌동 한신휴아파트")
                .addDetail("105동")
                .userId("bob1")
                .build();
        try {
            // AddressService 인스턴스를 통해 add 메서드 호출
            Address addedAddress = addressService.add(address);
            System.out.println("고객 등록 성공: " + addedAddress);
        } catch (DuplicatedIdException e) {
            System.out.println("ID가 중복되었습니다");
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }
}

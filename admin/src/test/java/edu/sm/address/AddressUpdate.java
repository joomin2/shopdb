package edu.sm.address;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;

public class AddressUpdate {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        int addressIdToUpdate = 7; // 수정할 Address ID
        Address updatedAddress = Address.builder()
                .addName("박주민 집 수정")
                .address("인천 서구 가좌동 수정 주소")
                .addDetail("205동") // 수정된 상세 주소
                .userId("bob1") // 수정할 사용자 ID
                .build();

        try {
            Address address = addressService.modify(updatedAddress); // 업데이트 메서드 호출
            System.out.println("주소 수정 성공: " + address);
        } catch (Exception e) {
            System.out.println("주소 수정 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

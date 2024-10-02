package edu.sm.address;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;

public class AddressSelectOne {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        int searchAddressId = 5; // 조회할 Address ID
        try {
            // 특정 Address ID에 대한 레코드 조회
            Address address = addressService.get(searchAddressId);
            if (address != null) {
                System.out.println("조회된 주소: " + address);
            } else {
                System.out.println("해당 ID에 대한 주소가 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("주소 조회 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

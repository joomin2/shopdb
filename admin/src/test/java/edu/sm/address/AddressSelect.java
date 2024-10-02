package edu.sm.address;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;

import java.util.List;

public class AddressSelect {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        try {
            // 모든 Address 레코드를 조회
            List<Address> addresses = addressService.get();
            for (Address address : addresses) {
                System.out.println("주소 정보: " + address);
            }
        } catch (Exception e) {
            System.out.println("주소 조회 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

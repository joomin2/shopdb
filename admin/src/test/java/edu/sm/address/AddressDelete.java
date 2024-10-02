package edu.sm.address;

import edu.sm.service.AddressService;

public class AddressDelete {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        int addressIdToDelete = 8; // 삭제할 Address ID
        try {
            boolean isDeleted = addressService.remove(addressIdToDelete); // 삭제 메서드 호출
            if (isDeleted) {
                System.out.println("주소 삭제 성공: ID " + addressIdToDelete);
            } else {
                System.out.println("주소 삭제 실패: ID " + addressIdToDelete + "를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("주소 삭제 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

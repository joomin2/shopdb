package edu.sm.user;

import edu.sm.dao.UserDao;
import edu.sm.dto.User;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;

public class UserUpdate {
    public static void main(String[] args) {
        try {
            ConnectionPool.create(); // ConnectionPool 초기화
        } catch (Exception e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        // 하드코딩된 값으로 사용자 정보 설정
        String userId = "bob01";  // 수정할 사용자 ID
        String email = "newemail@example.com";  // 새로운 이메일
        String password = "newpassword123";  // 새로운 비밀번호
        String phoneno = "010-1234-5678";  // 새로운 전화번호

        // User DTO 객체 생성 및 수정할 정보 설정
        User updateUser = new User();
        updateUser.setUserId(userId);
        updateUser.setEmail(email);
        updateUser.setPwd(password);
        updateUser.setPhoneno(phoneno);

        // 데이터베이스에서 사용자 정보 수정
        try (Connection con = ConnectionPool.getConnection()) {
            UserDao userDao = new UserDao();
            userDao.update(updateUser, con); // 사용자 수정 메서드 호출
            System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            System.err.println("사용자 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}

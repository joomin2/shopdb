package edu.sm.user;

import edu.sm.dao.UserDao; // UserDao 임포트
import edu.sm.dto.User; // User DTO 임포트
import edu.sm.frame.ConnectionPool; // ConnectionPool 임포트
import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.SQLException; // SQLException 클래스 임포트
import java.sql.Date; // java.sql.Date 클래스 임포트

public class UserInsert {
    public static void main(String[] args) {
        try {
            // ConnectionPool 초기화
            ConnectionPool.create();
        } catch (SQLException e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        // 하드코딩된 User 정보
        User user = User.builder()
                .userId("user1003")
                .name("홍길동")
                .email("hong1001@gmail.com")
                .pwd("12345")
                .phoneno("010-1234-5678")
                .gender(0)
                .joined(Date.valueOf("2015-01-12")) // 하드코딩된 날짜로 가입일 설정
                .age(30)
                .build();

        // 데이터베이스에 사용자 추가
        try (Connection con = ConnectionPool.getConnection()) { // 연결 풀에서 연결 가져오기
            UserDao userDao = new UserDao();
            userDao.insert(user, con); // 사용자 추가 메서드 호출
            System.out.println("회원가입이 완료되었습니다: " + user);
        } catch (Exception e) {
            System.err.println("회원가입 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}

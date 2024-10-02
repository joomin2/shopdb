package edu.sm.user;

import edu.sm.dao.UserDao;
import edu.sm.dto.User;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.util.Scanner;

public class UserSelectOne {
    public static void main(String[] args) {
        try {
            ConnectionPool.create(); // ConnectionPool 초기화
        } catch (Exception e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();

        System.out.print("조회할 사용자 ID를 입력하세요: ");
        String userId = scanner.nextLine();

        // 데이터베이스에서 특정 사용자 조회
        try (Connection con = ConnectionPool.getConnection()) {
            User user = userDao.select(userId, con); // 특정 사용자 조회 메서드 호출
            if (user != null) {
                System.out.println("사용자 정보:");
                System.out.println("ID: " + user.getUserId());
                System.out.println("이름: " + user.getName());
                System.out.println("이메일: " + user.getEmail());
                System.out.println("전화번호: " + user.getPhoneno());
                System.out.println("성별: " + (user.getGender() == 0 ? "남성" : "여성"));
                System.out.println("가입 날짜: " + user.getJoined());
                System.out.println("나이: " + user.getAge());
            } else {
                System.out.println("해당 ID의 사용자가 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.err.println("사용자 조회 중 오류가 발생했습니다: " + e.getMessage());
        }

        scanner.close(); // Scanner 닫기
    }
}

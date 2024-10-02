package edu.sm.user;

import edu.sm.dao.UserDao;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.util.Scanner;

public class UserDelete {
    public static void main(String[] args) {
        try {
            ConnectionPool.create(); // ConnectionPool 초기화
        } catch (Exception e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();

        System.out.print("삭제할 사용자 ID를 입력하세요: ");
        String userId = scanner.nextLine();

        // 데이터베이스에서 사용자 삭제
        try (Connection con = ConnectionPool.getConnection()) {
            boolean success = userDao.delete(userId, con); // 사용자 삭제 메서드 호출
            if (success) {
                System.out.println("사용자가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("해당 ID의 사용자가 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.err.println("사용자 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }

        scanner.close(); // Scanner 닫기
    }
}

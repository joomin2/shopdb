package edu.sm.user;

import edu.sm.dao.UserDao;
import edu.sm.dto.User;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.util.List;

public class UserSelect {
    public static void main(String[] args) {
        try {
            ConnectionPool.create(); // ConnectionPool 초기화
        } catch (Exception e) {
            System.err.println("ConnectionPool 초기화 중 오류 발생: " + e.getMessage());
            return; // 초기화 실패 시 프로그램 종료
        }

        UserDao userDao = new UserDao();

        // 데이터베이스에서 모든 사용자 조회
        try (Connection con = ConnectionPool.getConnection()) {
            List<User> users = userDao.select(con); // 모든 사용자 조회 메서드 호출
            System.out.println("전체 사용자 조회 결과:");
            for (User user : users) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.err.println("사용자 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}

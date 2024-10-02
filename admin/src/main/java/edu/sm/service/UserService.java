package edu.sm.service;

import edu.sm.dao.UserDao; // UserDao 임포트
import edu.sm.dto.User; // User DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.SQLException; // SQL 예외 처리 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class UserService {

    private UserDao userDao; // UserDao 객체 선언

    public UserService() {
        userDao = new UserDao(); // UserDao 초기화
    }

    // 사용자 추가 메서드
    public User addUser(User user, Connection con) throws Exception {
        try {
            return userDao.insert(user, con); // UserDao의 insert 메서드 호출
        } catch (DuplicatedIdException e) {
            throw new DuplicatedIdException("사용자 ID가 이미 존재합니다."); // 중복 ID 예외 발생
        } catch (SQLException e) {
            throw new SQLException("사용자 추가 중 오류 발생."); // SQL 예외 처리
        }
    }

    // 사용자 업데이트 메서드
    public User updateUser(User user, Connection con) throws Exception {
        try {
            return userDao.update(user, con); // UserDao의 update 메서드 호출
        } catch (SQLException e) {
            throw new SQLException("사용자 업데이트 중 오류 발생: " + e.getMessage()); // SQL 예외 처리
        }
    }

    // 사용자 삭제 메서드
    public boolean deleteUser(String userId, Connection con) throws Exception {
        try {
            return userDao.delete(userId, con); // UserDao의 delete 메서드 호출
        } catch (SQLException e) {
            throw new SQLException("사용자 삭제 중 오류 발생."); // SQL 예외 처리
        }
    }

    // 특정 사용자 조회 메서드
    public User getUser(String userId, Connection con) throws Exception {
        try {
            return userDao.select(userId, con); // UserDao의 select 메서드 호출
        } catch (SQLException e) {
            throw new SQLException("사용자 조회 중 오류 발생."); // SQL 예외 처리
        }
    }

    // 모든 사용자 조회 메서드
    public List<User> getAllUsers(Connection con) throws Exception {
        try {
            return userDao.select(con); // UserDao의 select 메서드 호출
        } catch (SQLException e) {
            throw new SQLException("모든 사용자 조회 중 오류 발생."); // SQL 예외 처리
        }
    }
}

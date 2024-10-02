package edu.sm.dao;

import edu.sm.dto.User; // User DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement; // PreparedStatement를 위한 클래스 임포트
import java.sql.ResultSet; // 결과 집합을 위한 클래스 임포트
import java.sql.SQLIntegrityConstraintViolationException; // SQL 무결성 제약 위반 예외 클래스 임포트
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class UserDao implements Dao<String, User> {

    // 사용자 정보 추가 메서드
    @Override
    public User insert(User user, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.insertUser); // SQL 쿼리 준비
            ps.setString(1, user.getUserId()); // 사용자 ID 설정
            ps.setString(2, user.getName()); // 이름 설정
            ps.setString(3, user.getEmail()); // 이메일 설정
            ps.setString(4, user.getPwd()); // 비밀번호 설정
            ps.setString(5, user.getPhoneno()); // 전화번호 설정
            ps.setInt(6, user.getGender()); // 성별 설정 (1: 여자, 0: 남자)
            ps.setDate(7, user.getJoined()); // 가입 날짜 설정
            ps.setInt(8, user.getAge()); // 나이 설정
            ps.executeUpdate(); // 쿼리 실행
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001"); // 중복 ID 예외 발생
        } catch (Exception e) {
            throw e; // 다른 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return user; // 추가한 사용자 정보 반환
    }

    // 사용자 정보 업데이트 메서드
    @Override
    public User update(User user, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateUser);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPwd());
            ps.setString(4, user.getPhoneno());
            ps.setInt(5, user.getGender());
            ps.setInt(6, user.getAge());
            ps.setString(7, user.getUserId()); // 사용자 ID 설정 (WHERE 조건)
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return user;
    }

    // 사용자 정보 삭제 메서드
    @Override
    public Boolean delete(String userId, Connection con) throws Exception {
        Boolean isDeleted = false; // 삭제 여부 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        try {
            ps = con.prepareStatement(Sql.deleteUser); // SQL 쿼리 준비
            ps.setString(1, userId); // 사용자 ID 설정
            int result = ps.executeUpdate(); // 쿼리 실행
            isDeleted = result == 1; // 삭제 성공 여부 설정
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
        }
        return isDeleted; // 삭제 여부 반환
    }

    // 특정 사용자 정보 조회 메서드
    @Override
    public User select(String userId, Connection con) throws Exception {
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        User user = null; // User 객체 초기화
        try {
            ps = con.prepareStatement(Sql.selectOneUser); // SQL 쿼리 준비
            ps.setString(1, userId); // 사용자 ID 설정
            rs = ps.executeQuery(); // 쿼리 실행
            if (rs.next()) { // 결과가 있을 경우
                user = new User(); // User 객체 생성
                user.setUserId(rs.getString("user_id")); // 사용자 ID 설정
                user.setName(rs.getString("name")); // 이름 설정
                user.setEmail(rs.getString("email")); // 이메일 설정
                user.setPwd(rs.getString("pwd")); // 비밀번호 설정
                user.setPhoneno(rs.getString("phoneno")); // 전화번호 설정
                user.setGender(rs.getInt("gender")); // 성별 설정
                user.setJoined(rs.getDate("joined")); // 가입 날짜 설정
                user.setAge(rs.getInt("age")); // 나이 설정
            }
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if (rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return user; // 조회한 사용자 정보 반환
    }

    // 모든 사용자 정보 조회 메서드
    @Override
    public List<User> select(Connection con) throws Exception {
        List<User> userList = new ArrayList<>(); // 사용자 리스트 초기화
        PreparedStatement ps = null; // PreparedStatement 선언
        ResultSet rs = null; // ResultSet 선언
        try {
            ps = con.prepareStatement(Sql.selectUser); // SQL 쿼리 준비
            rs = ps.executeQuery(); // 쿼리 실행
            while (rs.next()) { // 결과가 있는 동안 반복
                User user = new User(); // User 객체 생성
                user.setUserId(rs.getString("user_id")); // 사용자 ID 설정
                user.setName(rs.getString("name")); // 이름 설정
                user.setEmail(rs.getString("email")); // 이메일 설정
                user.setPwd(rs.getString("pwd")); // 비밀번호 설정
                user.setPhoneno(rs.getString("phoneno")); // 전화번호 설정
                user.setGender(rs.getInt("gender")); // 성별 설정
                user.setJoined(rs.getDate("joined")); // 가입 날짜 설정
                user.setAge(rs.getInt("age")); // 나이 설정
                userList.add(user); // 리스트에 추가
            }
        } catch (Exception e) {
            throw e; // 예외 발생
        } finally {
            if (ps != null) {
                ps.close(); // PreparedStatement 닫기
            }
            if (rs != null) {
                rs.close(); // ResultSet 닫기
            }
        }
        return userList; // 사용자 리스트 반환
    }
}

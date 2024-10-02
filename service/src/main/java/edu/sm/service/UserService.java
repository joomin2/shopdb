package edu.sm.service;

import edu.sm.dao.UserDao;
import edu.sm.dto.User;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService implements MService<String, User> {

    UserDao dao;
    //Service에서 dao
    ConnectionPool cp;

    public UserService() {
        dao = new UserDao();
        try {
            cp = ConnectionPool.create();//connection pool을 이용한 connection도 준비 완료.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public  User add(User user) throws Exception {//회원가입하면, 회사database에 내 정보 들어가고, 그에대한 문자가 나한테 오는 구조.
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(user, con);// 예외 EX0001이 여기로 날라옴.

            con.commit();//두개가 정상이면 commit을 한다.
            System.out.println("Send SMS to:" + user.getUser_id());
        }
        catch (Exception e) {//Exception 발생하면 return은 안됨.
            con.rollback();
            throw e;
        }finally {

            cp.releaseConnection(con);

        }


        return user;
    }

    @Override
    public User modify(User user) throws Exception {
        Connection con = cp.getConnection();
        try{
            dao.update(user, con);
            System.out.println("Send SMS to:" + user.getUser_id());
        }catch (Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return user;
    }

    @Override
    public Boolean remove(String s) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try{
            result = dao.delete(s, con);
            System.out.println("Send SMS to:" + s);
        }catch (Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public User get(String s) throws Exception {
        Connection con = cp.getConnection();
        User result = null;
        try{
            result = dao.select(s, con);

        }catch (Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<User> get() throws Exception { //select는 굳이 try~catch 필요x
        Connection con = cp.getConnection();
        List<User> result = null;

        try{
            result = dao.select(con);

        }catch (Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public User login(String userId, String password) throws Exception {
        Connection con = cp.getConnection();
        User user = null;
        try {
            user = dao.select(userId, con);
            if (user == null || !user.getPwd().equals(password)) {
                throw new Exception("Login failed: Invalid credentials");
            }
        } finally {
            cp.releaseConnection(con);
        }
        return user;
    }
}

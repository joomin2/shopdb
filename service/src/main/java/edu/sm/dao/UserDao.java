package edu.sm.dao;

import edu.sm.dto.User;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<String, User> {


    @Override
    public User insert(User user, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertUser);
            ps.setString(1, user.getUser_id());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPwd());
            ps.setString(5, user.getPhoneno());
            ps.setInt(6, user.getGender());
            ps.setInt(7, user.getAge());
            ps.executeUpdate();//여기서 Exception 나면 던지고 말아서 아래 close가 안됨.
//            ps.close();
            //connection 은 service 쪽에서 관리하기 때문에 ps만 close
        }catch (SQLIntegrityConstraintViolationException e){//중복일대 Exception을 설정해줌.
            throw new DuplicatedIdException("EX0001");


        }catch (Exception e) {
            throw e;

        }finally {
            if(ps != null){
                ps.close();
            }
        }

        return user;
    }

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
            ps.setString(7, user.getUser_id());
            ps.executeUpdate();
        }catch(Exception e) {
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }
        }
        return user;
    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        Boolean b = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteUser);
            ps.setString(1, s);
            int result = ps.executeUpdate();

            if(result ==1){
                b = true;
            }

            //connection 은 service 쪽에서 관리하기 때문에 ps만 close
        }catch (Exception e) {
            throw e;

        }finally {
            if(ps != null){
                ps.close();
            }
        }
        return b;
    }

    @Override
    public User select(String s, Connection con) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            ps = con.prepareStatement(Sql.selectOneUser);
            ps.setString(1, s);
            rs = ps.executeQuery();
            rs.next();
            user = new User();
            user.setUser_id(rs.getString("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPwd(rs.getString("pwd"));
            user.setPhoneno(rs.getString("phoneno"));
            user.setGender(rs.getInt("gender"));
            user.setJoined(rs.getDate("joined").toLocalDate());
            user.setAge(rs.getInt("age"));
        }catch(Exception e) {
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        return user ;
    }

    @Override
    public List<User> select(Connection con) throws Exception {
        List<User> users = new ArrayList<User>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectUser);
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPwd(rs.getString("pwd"));
                user.setPhoneno(rs.getString("phoneno"));
                user.setGender(rs.getInt("gender"));
                user.setJoined(rs.getDate("joined").toLocalDate());
                user.setAge(rs.getInt("age"));
                users.add(user);
            }

        }catch(Exception e) {
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        return users;
    }
}
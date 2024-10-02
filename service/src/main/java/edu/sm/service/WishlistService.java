package edu.sm.service;


import edu.sm.dao.WishlistDao;
import edu.sm.dto.Wishlist;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WishlistService implements MService<String, Wishlist> {

    WishlistDao dao;
    //Service에서 dao
    ConnectionPool cp;

    public WishlistService() {
        dao = new WishlistDao();
        try {
            cp = ConnectionPool.create();//connection pool을 이용한 connection도 준비 완료.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public  Wishlist add(Wishlist wishlist) throws Exception {//회원가입하면, 회사database에 내 정보 들어가고, 그에대한 문자가 나한테 오는 구조.
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(wishlist, con);// 예외 EX0001이 여기로 날라옴.

            con.commit();//두개가 정상이면 commit을 한다.
            System.out.println("Send SMS to:" + wishlist.getUser_id());
        }
        catch (Exception e) {//Exception 발생하면 return은 안됨.
            con.rollback();
            throw e;
        }finally {

            cp.releaseConnection(con);

        }


        return wishlist;
    }

    @Override
    public Wishlist modify(Wishlist wishlist) throws Exception {
        Connection con = cp.getConnection();
        try{
            dao.update(wishlist, con);
            System.out.println("Send SMS to:" + wishlist.getUser_id());
        }catch (Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return wishlist;
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
    public Wishlist get(String s) throws Exception {
        Connection con = cp.getConnection();
        Wishlist result = null;
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
    public List<Wishlist> get() throws Exception { //select는 굳이 try~catch 필요x
        Connection con = cp.getConnection();
        List<Wishlist> result = null;

        try{
            result = dao.select(con);

        }catch (Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}

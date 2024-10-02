package edu.sm.service;


import edu.sm.dao.ReviewDao;
import edu.sm.dto.Review;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReviewService implements MService<String, Review> {

    ReviewDao dao;
    //Service에서 dao
    ConnectionPool cp;

    public ReviewService() {
        dao = new ReviewDao();
        try {
            cp = ConnectionPool.create();//connection pool을 이용한 connection도 준비 완료.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Review add(Review review) throws Exception {//회원가입하면, 회사database에 내 정보 들어가고, 그에대한 문자가 나한테 오는 구조.
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(review, con);// 예외 EX0001이 여기로 날라옴.

            con.commit();//두개가 정상이면 commit을 한다.
            System.out.println("Send SMS to:" + review.getReview_in());
        }
        catch (Exception e) {//Exception 발생하면 return은 안됨.
            con.rollback();
            throw e;
        }finally {

            cp.releaseConnection(con);

        }


        return review;
    }

    @Override
    public Review modify(Review review) throws Exception {
        Connection con = cp.getConnection();
        try{
            dao.update(review, con);
            System.out.println("Send SMS to:" + review.getReview_in());
        }catch (Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return review;
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
    public Review get(String s) throws Exception {
        Connection con = cp.getConnection();
        Review result = null;
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
    public List<Review> get() throws Exception { //select는 굳이 try~catch 필요x
        Connection con = cp.getConnection();
        List<Review> result = null;

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

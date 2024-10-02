package edu.sm.dao;

import edu.sm.dto.Review;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements Dao<String, Review> {


    @Override
    public Review insert(Review review, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertReview);

            ps.setString(1, review.getUser_id());
            ps.setInt(2,review.getProduct_id());
            ps.setString(3,review.getTitle());
            ps.setString(4,review.getContent());
            ps.setString(5,review.getImg());
            ps.setInt(6,review.getScore());


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

        return review;
    }

    @Override
    public Review update(Review review, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateReview);

            ps.setInt(1,review.getProduct_id());
            ps.setString(2,review.getTitle());
            ps.setString(3,review.getContent());
            ps.setString(4,review.getImg());
            ps.setInt(5,review.getScore());
            ps.setInt(6,review.getReview_in());

            ps.executeUpdate();
        }catch(Exception e) {
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }
        }
        return review;
    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        Boolean b = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteReview);
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
    public Review select(String s, Connection con) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Review review = null;
        try {
            ps = con.prepareStatement(Sql.selectOneReview);
            ps.setString(1, s);
            rs = ps.executeQuery();
            rs.next();
            review = new Review();
            review.setReview_in(rs.getInt("review_in"));
            review.setUser_id(rs.getString("user_id"));
            review.setProduct_id(rs.getInt("product_id"));
            review.setTitle(rs.getString("title"));
            review.setContent(rs.getString("content"));
            review.setImg(rs.getString("img"));
            review.setScore(rs.getInt("score"));
            review.setReview_date(rs.getDate("review_date").toLocalDate());


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
        return review ;
    }

    @Override
    public List<Review> select(Connection con) throws Exception {
        List<Review> reviews = new ArrayList<Review>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectReview);
            rs = ps.executeQuery();
            while(rs.next()){
                Review review = new Review();
                review.setReview_in(rs.getInt("review_in"));
                review.setUser_id(rs.getString("user_id"));
                review.setProduct_id(rs.getInt("product_id"));
                review.setTitle(rs.getString("title"));
                review.setContent(rs.getString("content"));
                review.setImg(rs.getString("img"));
                review.setScore(rs.getInt("score"));
                review.setReview_date(rs.getDate("review_date").toLocalDate());

                reviews.add(review);
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
        return reviews;
    }
}
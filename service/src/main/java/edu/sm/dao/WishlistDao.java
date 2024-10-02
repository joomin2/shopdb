package edu.sm.dao;

import edu.sm.dto.Wishlist;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao implements Dao<String, Wishlist> {


    @Override
    public Wishlist insert(Wishlist wishlist, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertWishlist);
            ps.setString(1, wishlist.getUser_id());
            ps.setInt(2, wishlist.getProduct_id());

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

        return wishlist;
    }

    @Override
    public Wishlist update(Wishlist wishlist, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateWishlist);
            ps.setInt(1, wishlist.getProduct_id());
            ps.setString(2, wishlist.getUser_id());

            ps.executeUpdate();
        }catch(Exception e) {
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }
        }
        return wishlist;
    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        Boolean b = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteWishlist);
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
    public Wishlist select(String s, Connection con) throws Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Wishlist wishlist = null;
        try {
            ps = con.prepareStatement(Sql.selectOneWishlist);
            ps.setString(1, s);
            rs = ps.executeQuery();
            rs.next();
            wishlist = new Wishlist();
            wishlist.setUser_id(rs.getString("user_id"));
            wishlist.setProduct_id(rs.getInt("product_id"));
            wishlist.setCreated_at(rs.getDate("created_at").toLocalDate());


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
        return wishlist ;
    }

    @Override
    public List<Wishlist> select(Connection con) throws Exception {
        List<Wishlist> wishlists = new ArrayList<Wishlist>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectWishlist);
            rs = ps.executeQuery();
            while(rs.next()){
                Wishlist wishlist = new Wishlist();
                wishlist.setUser_id(rs.getString("user_id"));
                wishlist.setProduct_id(rs.getInt("product_id"));
                wishlist.setCreated_at(rs.getDate("created_at").toLocalDate());

                wishlists.add(wishlist);
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
        return wishlists;
    }
}
package edu.sm.dao;

import edu.sm.dto.WishlistItem;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql; // SQL 쿼리 상수를 사용하기 위해 추가

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao implements Dao<String, WishlistItem> {

    @Override
    public WishlistItem insert(WishlistItem item, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertWishlistItem); // 수정된 SQL 쿼리 사용
            ps.setString(1, item.getUserId());
            ps.setInt(2, item.getProductId());
            ps.setDate(3, item.getCreatedAt()); // createdAt 추가
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return item;
    }

    @Override
    public WishlistItem update(WishlistItem item, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateWishlistItem); // 수정된 SQL 쿼리 사용
            ps.setDate(1, item.getCreatedAt()); // createdAt 추가
            ps.setString(2, item.getUserId());
            ps.setInt(3, item.getProductId());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return item;
    }

    @Override
    public Boolean delete(String userId, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteWishlistItem); // 수정된 SQL 쿼리 사용
            ps.setString(1, userId);
            ps.setInt(2, -1); // product_id는 임시로 설정 (상황에 맞게 수정 필요)
            int result = ps.executeUpdate();
            return result == 1; // 삭제 성공 여부 반환
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public WishlistItem select(String userId, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        WishlistItem item = null;
        try {
            ps = con.prepareStatement(Sql.selectOneWishlistItem); // 수정된 SQL 쿼리 사용
            ps.setString(1, userId);
            ps.setInt(2, -1); // product_id는 임시로 설정 (상황에 맞게 수정 필요)
            rs = ps.executeQuery();
            if (rs.next()) {
                item = new WishlistItem();
                item.setUserId(rs.getString("user_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setCreatedAt(rs.getDate("created_at")); // createdAt 설정
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return item;
    }

    @Override
    public List<WishlistItem> select(Connection con) throws Exception {
        List<WishlistItem> items = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectWishlistItem); // 수정된 SQL 쿼리 사용
            rs = ps.executeQuery();
            while (rs.next()) {
                WishlistItem item = new WishlistItem();
                item.setUserId(rs.getString("user_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setCreatedAt(rs.getDate("created_at")); // createdAt 설정
                items.add(item);
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return items;
    }
}

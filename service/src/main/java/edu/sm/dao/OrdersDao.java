package edu.sm.dao;

import edu.sm.dto.Orders;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao implements Dao<Integer, Orders> {

    // Insert an order into the database
    @Override
    public  Orders insert(Orders order, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertOrder);
            ps.setDate(1, new Date(order.getOrder_date().getTime())); // order_date
            ps.setString(2, order.getStatus()); // status
            ps.setInt(3, order.getTotal_price()); // total_price
            ps.setInt(4, order.getUser_id()); // user_id

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return order;
    }

    // Update an existing order in the database
    @Override
    public Orders update(Orders order, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateOrder);
            ps.setDate(1, new java.sql.Date(order.getOrder_date().getTime())); // order_date
            ps.setString(2, order.getStatus()); // status
            ps.setInt(3, order.getTotal_price()); // total_price
            ps.setInt(4, order.getOrder_id()); // order_id

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return order;
    }

    // Delete an order from the database
    @Override
    public Boolean delete(Integer order_id, Connection con) throws Exception {
        Boolean deleted = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteOrder);
            ps.setInt(1, order_id);
            int result = ps.executeUpdate();
            if (result == 1) {
                deleted = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return deleted;
    }

    // Select a specific order by its ID
    @Override
    public  Orders select(Integer order_id, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Orders order = null;
        try {
            ps = con.prepareStatement(Sql.selectOneOrder);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                order = new Orders();
                order.setOrder_id(rs.getInt("order_id"));
                order.setOrder_date(rs.getDate("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotal_price(rs.getInt("total_price"));
                order.setUser_id(rs.getInt("user_id"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return order;
    }

    // Select all orders from the database
    @Override
    public List<Orders> select(Connection con) throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectOrder);
            rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrder_id(rs.getInt("order_id"));
                order.setOrder_date(rs.getDate("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotal_price(rs.getInt("total_price"));
                order.setUser_id(rs.getInt("user_id"));
                ordersList.add(order);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return ordersList;
    }
}

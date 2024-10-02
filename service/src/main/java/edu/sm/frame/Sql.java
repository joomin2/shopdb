package edu.sm.frame;

public class Sql {
    //Sql 문만 관리

    public static String insertCust = "INSERT INTO cust VALUES(?,?,?)";
    public static String selectCust = "SELECT * FROM cust";
    public static String selectOneCust = "SELECT * FROM cust WHERE id = ?";
    public static String deleteCust = "DELETE FROM cust WHERE id = ?";
    public static String updateCust = "UPDATE cust SET pwd = ?,name = ? WHERE id = ?";

    public static String insertProduct = "INSERT INTO product VALUES(0,?,?,sysdate())";
    public static String selectProduct = "SELECT * FROM product";
    public static String selectOneProduct = "SELECT * FROM product WHERE id = ?";
    public static String deleteProduct = "DELETE FROM product WHERE id = ?";
    public static String updateProduct = "UPDATE product SET name = ?,price = ?,regdate = sysdate()  WHERE id = ?";

    public static String insertUser = "INSERT INTO user VALUES(?,?,?,?,?,?,sysdate(),?)";
    public static String selectUser = "SELECT * FROM user";
    public static String selectOneUser = "SELECT * FROM user WHERE user_id = ?";
    public static String deleteUser= "DELETE FROM user WHERE user_id = ?";
    public static String updateUser = "UPDATE user SET name = ?,email = ?,pwd=?,phoneno=?,gender=?,age=?  WHERE user_id = ?";

    public static String insertWishlist = "INSERT INTO wishlist_item VALUES(?,?,SYSDATE())";
    public static String selectWishlist = "SELECT * FROM wishlist_item";
    public static String selectOneWishlist = "SELECT * FROM wishlist_item WHERE user_id = ?";
    public static String deleteWishlist= "DELETE FROM wishlist_item WHERE user_id = ?";
    public static String updateWishlist = "UPDATE wishlist_item SET product_id = ?  WHERE user_id = ?";

    public static String insertReview = "INSERT INTO review VALUES(?,?,?,?,?,?,SYSDATE())";
    public static String selectReview = "SELECT * FROM review";
    public static String selectOneReview = "SELECT * FROM review WHERE review_in = ?";
    public static String deleteReview= "DELETE FROM review WHERE review_in = ?";
    public static String updateReview = "UPDATE review SET product_id = ?,title = ?, content = ?,img = ?,score = ? ,review_date = SYSDATE() WHERE review_in = ?";

    public static String insertCart = "INSERT INTO cart VALUES(SYSDATE(),?,?,?)";
    public static String selectCart = "SELECT * FROM cart";
    public static String selectOneCart = "SELECT * FROM cart WHERE sbag_id = ?";
    public static String deleteCart= "DELETE FROM cart WHERE sbag_id = ?";
    public static String updateCart = "UPDATE cart SET created_at = SYSDATE(),quantity = ?, product_id = ? WHERE sbag_id = ?";




}

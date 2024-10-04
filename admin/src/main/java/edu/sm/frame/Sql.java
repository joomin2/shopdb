package edu.sm.frame;

public class Sql {
    // Address 테이블 쿼리
    public static final String insertAddress = "INSERT INTO address (add_name, address, add_detail, user_id) VALUES (?, ?, ?, ?)";
    public static final String updateAddress = "UPDATE address SET add_name = ?, address = ?, add_detail = ? WHERE add_id = ?";
    public static final String deleteAddress = "DELETE FROM address WHERE add_id = ?";
    public static final String selectOneAddress = "SELECT * FROM address WHERE add_id = ?";
    public static final String selectAddress = "SELECT * FROM address";

    // Cart 테이블 쿼리
    public static final String insertCart = "INSERT INTO cart (create_at, quantity, product_id, user_id) VALUES (?, ?, ?, ?)";
    public static final String updateCart = "UPDATE cart SET quantity = ? WHERE sbag_id = ?";
    public static final String deleteCart = "DELETE FROM cart WHERE sbag_id = ?";
    public static final String selectOneCart = "SELECT * FROM cart WHERE sbag_id = ?";
    public static final String selectCart = "SELECT * FROM cart";

    // Category 테이블 쿼리
    public static final String insertCategory = "INSERT INTO category (cateno, cateno1, cateno2, catename) VALUES (?, ?, ?, ?)";
    public static final String updateCategory = "UPDATE category SET cateno1 = ?, cateno2 = ?, catename = ? WHERE cateno = ?";
    public static final String deleteCategory = "DELETE FROM category WHERE cateno = ?";
    public static final String selectOneCategory = "SELECT * FROM category WHERE cateno = ?";
    public static final String selectCategory = "SELECT * FROM category";

    // OrderDetail 테이블 쿼리
    public static final String insertOrderDetail = "INSERT INTO order_detail (price, order_id, product_id, odetail_quantity) VALUES (?, ?, ?, ?)";
    public static final String updateOrderDetail = "UPDATE order_detail SET odetail_quantity = ?, price = ? WHERE odetail_id = ?";
    public static final String deleteOrderDetail = "DELETE FROM order_detail WHERE odetail_id = ?";
    public static final String selectOneOrderDetail = "SELECT * FROM order_detail WHERE odetail_id = ?";
    public static final String selectOrderDetail = "SELECT * FROM order_detail";
    public static final String existsById = "SELECT COUNT(*) FROM orderdetail WHERE odetail_id = ?";


    // Orders 테이블 쿼리
    public static final String insertOrder = "INSERT INTO orders (order_date, status, total_price, user_id) VALUES (?, ?, ?, ?)";
    public static final String updateOrder = "UPDATE orders SET order_date = ?, status = ?, total_price = ? WHERE order_id = ?";
    public static final String deleteOrder = "DELETE FROM orders WHERE order_id = ?";
    public static final String selectOneOrder = "SELECT * FROM orders WHERE order_id = ?";
    public static final String selectOrder = "SELECT * FROM orders";



    // Payment 테이블 쿼리
    public static final String insertPayment = "INSERT INTO payment (payment_id, payment_date, payment_method, amount, order_id) VALUES (?, ?, ?, ?, ?)";
    public static final String updatePayment = "UPDATE payment SET amount = ? WHERE payment_id = ?";
    public static final String deletePayment = "DELETE FROM payment WHERE payment_id = ?";
    public static final String selectOnePayment = "SELECT * FROM payment WHERE payment_id = ?";
    public static final String selectPayment = "SELECT * FROM payment";
    public static final String EXISTS_PAYMENT_ID = "SELECT COUNT(*) FROM payment WHERE payment_id = ?";


    // Product 테이블 쿼리
    public static final String insertProduct = "INSERT INTO product (product_name, product_price, product_img, cateno, product_date) VALUES (?, ?, ?, ?, ?)";
    public static final String updateProduct = "UPDATE product SET product_name = ?, product_price = ?, product_img = ?, cateno = ?, product_date = ? WHERE product_id = ?";

    public static final String deleteProduct = "DELETE FROM product WHERE product_id = ?";
    public static final String selectOneProduct = "SELECT * FROM product WHERE product_id = ?";
    public static final String selectProduct = "SELECT * FROM product";


    // Review 테이블 쿼리
    public static final String insertReview = "INSERT INTO review (review_in, user_id, product_id, title, content, img, score, review_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String updateReview = "UPDATE review SET title = ?, content = ?, img = ?, score = ? WHERE review_in = ?";
    public static final String deleteReview = "DELETE FROM review WHERE review_in = ?";
    public static final String selectOneReview = "SELECT * FROM review WHERE review_in = ?";
    public static final String selectReview = "SELECT * FROM review";

    // Shipping 테이블 쿼리
    public static final String insertShipping = "INSERT INTO shipping (shipping_adress, shipping_date, shipping_status, order_id) VALUES (?, ?, ?, ?)";
    public static final String updateShipping = "UPDATE shipping SET shipping_adress = ?, shipping_date = ?, shipping_status = ? WHERE shipping_id = ?";
    public static final String deleteShipping = "DELETE FROM shipping WHERE shipping_id = ?";
    public static final String selectOneShipping = "SELECT * FROM shipping WHERE shipping_id = ?";
    public static final String selectShipping = "SELECT * FROM shipping";
    // User 테이블 쿼리
    public static final String insertUser = "INSERT INTO user (user_id, name, email, pwd, phoneno, gender, joined, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String updateUser = "UPDATE user SET name = ?, email = ?, pwd = ?, phoneno = ?, gender = ?, age = ? WHERE user_id = ?";
    public static final String deleteUser = "DELETE FROM user WHERE user_id = ?";
    public static final String selectOneUser = "SELECT * FROM user WHERE user_id = ?";
    public static final String selectUser = "SELECT * FROM user";

    // WishlistItem 테이블 쿼리
    public static final String insertWishlistItem = "INSERT INTO wishlist_item (user_id, product_id, created_at) VALUES (?, ?, ?)";
    public static final String updateWishlistItem = "UPDATE wishlist_item SET created_at = ? WHERE user_id = ? AND product_id = ?";
    public static final String deleteWishlistItem = "DELETE FROM wishlist_item WHERE user_id = ? AND product_id = ?";
    public static final String selectOneWishlistItem = "SELECT * FROM wishlist_item WHERE user_id = ? AND product_id = ?";
    public static final String selectWishlistItem = "SELECT * FROM wishlist_item";

    public static final String SELECT_SALES_BY_AGE_GROUP = "SELECT CASE " + "WHEN u.age BETWEEN 0 AND 19 THEN '0-19세' " + "WHEN u.age BETWEEN 20 AND 29 THEN '20-29세' " + "WHEN u.age BETWEEN 30 AND 39 THEN '30-39세' " +"WHEN u.age BETWEEN 40 AND 49 THEN '40-49세' " +"WHEN u.age BETWEEN 50 AND 59 THEN '50-59세' " + "ELSE '60세 이상' END AS age_group, " + "SUM(o.total_price) AS total_sales " + "FROM user u " + "JOIN orders o ON u.user_id = o.user_id " +"GROUP BY age_group";

    public static final String SELECT_SALES_BY_GENDER = "SELECT CASE " + "WHEN u.gender = 0 THEN '남성' " + "ELSE '여성' END AS gender, " + "SUM(o.total_price) AS total_sales " + "FROM user u " + "JOIN orders o ON u.user_id = o.user_id " + "GROUP BY gender";

    public static final String selectMonthlySales = "SELECT DATE_FORMAT(order_date, '%Y-%m') AS month, SUM(total_price) AS total_sales FROM orders GROUP BY month ORDER BY month";
}

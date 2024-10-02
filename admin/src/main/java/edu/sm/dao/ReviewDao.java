package edu.sm.dao;

import edu.sm.dto.Review;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {

    // 리뷰 추가
    public void insert(Review review, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(Sql.insertReview)) {
            pstmt.setInt(1, review.getReviewIn());
            pstmt.setString(2, review.getUserId());
            pstmt.setInt(3, review.getProductId());
            pstmt.setString(4, review.getTitle());
            pstmt.setString(5, review.getContent());
            pstmt.setString(6, review.getImg());
            pstmt.setInt(7, review.getScore());
            pstmt.setTimestamp(8, review.getReviewDate());
            pstmt.executeUpdate();
        }
    }

    // 특정 리뷰 조회
    public Review select(int reviewId, Connection con) throws SQLException {
        Review review = null;
        try (PreparedStatement pstmt = con.prepareStatement(Sql.selectOneReview)) {
            pstmt.setInt(1, reviewId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                review = new Review(
                        rs.getInt("review_in"),
                        rs.getString("user_id"),
                        rs.getInt("product_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("img"),
                        rs.getInt("score"),
                        rs.getTimestamp("review_date")
                );
            }
        }
        return review;
    }

    // 모든 리뷰 조회
    public List<Review> selectAll(Connection con) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(Sql.selectReview)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Review review = new Review(
                        rs.getInt("review_in"),
                        rs.getString("user_id"),
                        rs.getInt("product_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("img"),
                        rs.getInt("score"),
                        rs.getTimestamp("review_date")
                );
                reviews.add(review);
            }
        }
        return reviews;
    }

    // 리뷰 수정
    public void update(Review review, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(Sql.updateReview)) {
            pstmt.setString(1, review.getTitle());
            pstmt.setString(2, review.getContent());
            pstmt.setString(3, review.getImg());
            pstmt.setInt(4, review.getScore());
            pstmt.setInt(5, review.getReviewIn());
            pstmt.executeUpdate();
        }
    }

    // 리뷰 삭제
    public void delete(int reviewId, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(Sql.deleteReview)) {
            pstmt.setInt(1, reviewId);
            pstmt.executeUpdate();
        }
    }
}

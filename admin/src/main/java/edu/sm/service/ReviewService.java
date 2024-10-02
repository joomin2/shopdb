package edu.sm.service;

import edu.sm.dao.ReviewDao;
import edu.sm.dto.Review;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    private ReviewDao reviewDao; // DAO 객체

    public ReviewService() {
        this.reviewDao = new ReviewDao(); // ReviewDao 초기화
    }

    // 리뷰 추가 메서드
    public void addReview(Review review, Connection con) throws SQLException {
        try {
            con.setAutoCommit(false); // 트랜잭션 시작
            reviewDao.insert(review, con); // 리뷰 삽입
            con.commit(); // 트랜잭션 커밋
        } catch (SQLException e) {
            con.rollback(); // 트랜잭션 롤백
            throw e; // 예외 발생
        }
    }

    // 특정 리뷰 조회 메서드
    public Review getReview(int reviewId, Connection con) throws SQLException {
        return reviewDao.select(reviewId, con); // 리뷰 조회
    }

    // 모든 리뷰 조회 메서드
    public List<Review> getAllReviews(Connection con) throws SQLException {
        return reviewDao.selectAll(con); // 모든 리뷰 조회
    }

    // 리뷰 수정 메서드
    public void updateReview(Review review, Connection con) throws SQLException {
        try {
            con.setAutoCommit(false); // 트랜잭션 시작
            reviewDao.update(review, con); // 리뷰 수정
            con.commit(); // 트랜잭션 커밋
        } catch (SQLException e) {
            con.rollback(); // 트랜잭션 롤백
            throw e; // 예외 발생
        }
    }

    // 리뷰 삭제 메서드
    public void deleteReview(int reviewId, Connection con) throws SQLException {
        try {
            con.setAutoCommit(false); // 트랜잭션 시작
            reviewDao.delete(reviewId, con); // 리뷰 삭제
            con.commit(); // 트랜잭션 커밋
        } catch (SQLException e) {
            con.rollback(); // 트랜잭션 롤백
            throw e; // 예외 발생
        }
    }
}

package edu.sm.service;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.exception.NotFoundException;

import java.sql.Connection;
import java.util.List;

public class CartService {
    private final CartDao cartDao; // CartDao 객체
    private final Connection connection; // JDBC 연결 객체

    // 생성자
    public CartService(CartDao cartDao, Connection connection) {
        this.cartDao = cartDao; // CartDao 초기화
        this.connection = connection; // JDBC 연결 초기화
    }

    // 장바구니 추가 메서드
    public Cart addCart(Cart cart) throws Exception {
        try {
            return cartDao.insert(cart, connection); // 장바구니 추가
        } catch (DuplicatedIdException e) {
            throw e; // 중복 ID 예외 처리
        } catch (Exception e) {
            throw new Exception("장바구니 추가 실패", e); // 일반 예외 처리
        }
    }

    // 장바구니 업데이트 메서드
    public Cart updateCart(Cart cart) throws Exception {
        try {
            return cartDao.update(cart, connection); // 장바구니 업데이트
        } catch (NotFoundException e) {
            throw e; // 찾지 못한 예외 처리
        } catch (Exception e) {
            throw new Exception("장바구니 업데이트 실패", e); // 일반 예외 처리
        }
    }

    // 장바구니 삭제 메서드
    public Boolean removeCart(Integer sbagId) throws Exception {
        try {
            return cartDao.delete(sbagId, connection); // 장바구니 삭제
        } catch (Exception e) {
            throw new Exception("장바구니 삭제 실패", e); // 일반 예외 처리
        }
    }

    // 특정 장바구니 조회 메서드
    public Cart getCart(Integer sbagId) throws Exception {
        Cart cart = cartDao.select(sbagId, connection); // 장바구니 조회
        if (cart == null) {
            throw new NotFoundException("장바구니를 찾을 수 없습니다."); // 찾지 못한 경우 예외 발생
        }
        return cart; // 조회한 장바구니 반환
    }

    // 모든 장바구니 조회 메서드
    public List<Cart> getAllCarts() throws Exception {
        return cartDao.select(connection); // 모든 장바구니 조회
    }
}

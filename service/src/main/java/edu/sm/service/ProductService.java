package edu.sm.service;

import edu.sm.dao.ProductDao;
import edu.sm.dto.Product;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService implements MService<Integer, Product> {

    private static ProductDao productDao; // ProductDao 객체 선언
    private static ConnectionPool cp; // ConnectionPool 객체 선언

    public ProductService() {
        productDao = new ProductDao(); // ProductDao 객체 초기화
        try {
            cp = ConnectionPool.create(); // Connection pool을 이용한 Connection 준비
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product add(Product product) throws Exception {
        Connection con = cp.getConnection();
        con.setAutoCommit(false); // 자동 커밋 비활성화

        try {
            Product addedProduct = productDao.insert(product, con); // 제품 추가
            con.commit(); // 모든 작업이 정상적으로 수행되면 커밋
            System.out.println("제품 추가됨: " + addedProduct);
            return addedProduct; // 추가된 제품 반환

        } catch (SQLException e) {
            con.rollback(); // 롤백
            throw new Exception("DB 오류 발생: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }
    }

    @Override
    public Product modify(Product product) throws Exception {
        Connection con = cp.getConnection();

        try {
            productDao.update(product, con); // 제품 업데이트
            System.out.println("제품 수정됨: " + product);
        } catch (SQLException e) {
            throw new Exception("제품 수정 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return product; // 수정된 제품 반환
    }

    @Override
    public Boolean remove(Integer productId) throws Exception {
        Connection con = cp.getConnection();
        Boolean result;

        try {
            result = productDao.delete(productId, con); // 제품 삭제
            System.out.println("제품 삭제됨: " + productId);
        } catch (SQLException e) {
            throw new Exception("제품 삭제 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return result; // 삭제 결과 반환
    }

    @Override
    public Product get(Integer productId) throws Exception {
        Connection con = cp.getConnection();
        Product product;

        try {
            product = productDao.select(productId, con); // 단일 제품 조회
            System.out.println("제품 조회됨: " + product);
        } catch (SQLException e) {
            throw new Exception("제품 조회 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return product; // 조회된 제품 반환
    }

    @Override
    public List<Product> get() throws Exception {
        Connection con = cp.getConnection();
        List<Product> products;

        try {
            products = productDao.select(con); // 모든 제품 조회
            System.out.println("모든 제품 조회됨.");
        } catch (SQLException e) {
            throw new Exception("제품 조회 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return products; // 조회된 제품 목록 반환
    }
}

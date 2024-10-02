package edu.sm.service;

import edu.sm.dao.AddressDao;
import edu.sm.dto.Address;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressService implements MService<Integer, Address> {

    private static AddressDao addressDao;
    private static ConnectionPool cp;

    public AddressService() {
        addressDao = new AddressDao();
        try {
            cp = ConnectionPool.create(); // Connection pool을 이용한 Connection 준비
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address add(Address address) throws Exception {
        Connection con = cp.getConnection();
        con.setAutoCommit(false); // 자동 커밋 비활성화

        try {
            addressDao.insert(address, con); // 주소 추가
            con.commit(); // 모든 작업이 정상적으로 수행되면 커밋
            System.out.println("주소 추가됨: " + address);
        } catch (DuplicatedIdException e) {
            con.rollback(); // 롤백
            throw new DuplicatedIdException("ID가 중복되었습니다: " + e.getMessage());
        } catch (SQLException e) {
            con.rollback(); // 롤백
            throw new Exception("DB 오류 발생: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return address;
    }

    @Override
    public Address modify(Address address) throws Exception {
        Connection con = cp.getConnection();

        try {
            addressDao.update(address, con);
            System.out.println("주소 수정됨: " + address);
        } catch (SQLException e) {
            throw new Exception("주소 수정 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return address;
    }

    @Override
    public Boolean remove(Integer addressId) throws Exception {
        Connection con = cp.getConnection();
        Boolean result;

        try {
            result = addressDao.delete(addressId, con);
            System.out.println("주소 삭제됨: " + addressId);
        } catch (SQLException e) {
            throw new Exception("주소 삭제 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return result;
    }

    @Override
    public Address get(Integer addressId) throws Exception {
        Connection con = cp.getConnection();
        Address address;

        try {
            address = addressDao.select(addressId, con); // 단일 조회
            System.out.println("주소 조회됨: " + address);
        } catch (SQLException e) {
            throw new Exception("주소 조회 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return address;
    }

    @Override
    public List<Address> get() throws Exception {
        Connection con = cp.getConnection();
        List<Address> addresses;

        try {
            addresses = addressDao.select(con); // 모든 주소 조회
            System.out.println("모든 주소 조회됨.");
        } catch (SQLException e) {
            throw new Exception("주소 조회 실패: " + e.getMessage());
        } finally {
            con.close(); // 연결 종료
        }

        return addresses;
    }
}

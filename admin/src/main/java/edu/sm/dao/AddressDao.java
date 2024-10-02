package edu.sm.dao;

import edu.sm.dto.Address; // Address DTO 임포트
import edu.sm.exception.DuplicatedIdException; // 중복 ID 예외 처리 클래스 임포트
import edu.sm.frame.Dao; // Dao 인터페이스 임포트
import edu.sm.frame.Sql; // SQL 쿼리 정의를 위한 클래스 임포트

import java.sql.Connection; // JDBC 연결을 위한 클래스 임포트
import java.sql.PreparedStatement; // PreparedStatement를 위한 클래스 임포트
import java.sql.ResultSet; // 결과 집합을 위한 클래스 임포트
import java.sql.SQLIntegrityConstraintViolationException; // SQL 무결성 제약 위반 예외 클래스 임포트
import java.util.ArrayList; // 리스트를 사용하기 위한 ArrayList 클래스 임포트
import java.util.List; // 리스트를 위한 인터페이스 임포트

public class AddressDao implements Dao<Integer, Address> {

    // 주소 추가 메서드
    @Override
    public Address insert(Address address, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            // insertAddress에 add_id를 제외한 쿼리 사용
            ps = con.prepareStatement(Sql.insertAddress);
            ps.setString(1, address.getAddName());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getAddDetail());
            ps.setString(4, address.getUserId());
            ps.executeUpdate();
        } catch(SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001");
        } catch(Exception e) {
            throw e;
        } finally {
            if(ps != null) {
                ps.close();
            }
        }
        return address;
    }

    // 주소 업데이트 메서드
    @Override
    public Address update(Address address, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateAddress);
            ps.setString(1, address.getAddName());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getAddDetail());
            ps.setInt(4, address.getAddId()); // add_id를 마지막에 설정
            ps.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally {
            if(ps != null) {
                ps.close();
            }
        }
        return address;
    }

    // 주소 삭제 메서드 (주소 ID로 삭제)
    @Override
    public Boolean delete(Integer addId, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteAddress);
            ps.setInt(1, addId);
            ps.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally {
            if(ps != null) {
                ps.close();
            }
        }
        return false;
    }



    // 특정 주소 조회 메서드
    @Override
    public Address select(Integer addId, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address address = null;
        try {
            ps = con.prepareStatement(Sql.selectOneAddress);
            ps.setInt(1, addId);
            rs = ps.executeQuery();
            if(rs.next()) {
                address = new Address();
                address.setAddId(rs.getInt("add_id"));
                address.setAddName(rs.getString("add_name"));
                address.setAddress(rs.getString("address"));
                address.setAddDetail(rs.getString("add_detail"));
                address.setUserId(rs.getString("user_id"));
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        }
        return address;
    }

    // 모든 주소 조회 메서드
    @Override
    public List<Address> select(Connection con) throws Exception {
        List<Address> addressList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectAddress);
            rs = ps.executeQuery();
            while(rs.next()) {
                Address address = new Address();
                address.setAddId(rs.getInt("add_id"));
                address.setAddName(rs.getString("add_name"));
                address.setAddress(rs.getString("address"));
                address.setAddDetail(rs.getString("add_detail"));
                address.setUserId(rs.getString("user_id"));
                addressList.add(address);
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        }
        return addressList;
    }
}

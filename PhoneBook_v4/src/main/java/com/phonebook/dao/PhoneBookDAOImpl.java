package com.phonebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.phonebook.vo.PhoneBookVO;

public class PhoneBookDAOImpl implements PhoneBookDAO {

	// 접속 코드(커넥션 확보)
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##BITUSER", "bituser");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		return conn;
	} // getConnection() end

	@Override
	public List<PhoneBookVO> getList() { // 전체 목록

		List<PhoneBookVO> list = new ArrayList<PhoneBookVO>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT id, name, hp, tel FROM phone_book" + " ORDER BY id";
			rs = stmt.executeQuery(sql);

			// 루프: 객체화
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);

				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	} // getList() end

	@Override
	public List<PhoneBookVO> search(String keyword) { // 검색
		List<PhoneBookVO> list = new ArrayList<PhoneBookVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel" + " FROM phone_book " + " WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");

			rs = pstmt.executeQuery();

			// ResultSet -> List 변환
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);

				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("[검색결과가 없습니다.]");
//			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		return list;
	} // search(String keyword) end

	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			// 실행 계획
			String sql = "INSERT INTO phone_book " + " VALUES(seq_phone_book.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); // 준비
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());

			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == insertedCount;
	} // insert(PhoneBookVO vo) end

//	@Override
//	public boolean update(PhoneBookVO vo) { // 수정
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int updatedCount = 0;
//
//		try {
//			conn = getConnection();
//			String sql = "UPDATE phone_book SET name = ?, hp = ? " + " WHERE id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getHp());
//			pstmt.setString(3, vo.getTel());
//
//			updatedCount = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pstmt.close();
//				conn.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 1 == updatedCount;
//	} // update(PhoneBookVO vo) end

	@Override
	public boolean delete(Long id) { // 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book " + " WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);

			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == deletedCount;
	} // delete(Long id) end

}

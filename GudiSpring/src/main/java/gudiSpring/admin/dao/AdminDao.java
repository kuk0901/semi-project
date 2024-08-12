package gudiSpring.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.admin.dto.AdminDto;
import gudiSpring.user.dto.UserDto;

public class AdminDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// 관리자 존재유무
	public AdminDto adminExist(String id, String pwd) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "";
			sql += "SELECT USER_NO, NAME, NICKNAME, ID, PASSWORD, PHONE";
			sql += " , TO_CHAR(CREATED_DATE, 'YYYY\"년\"MM\"월\"DD\"일\" HH24:MI:SS') AS FM_CRE_DATE";
			sql += " , TO_CHAR(UPDATE_DATE, 'YYYY\"년\"MM\"월\"DD\"일\" HH24:MI:SS') AS FM_UP_DATE";
			sql += " , AUTHORITY";
			sql += " FROM USER_INFO";
			sql += " WHERE ID = ?";
			sql += " AND PASSWORD = ?";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			pstmt.setString(colIndex++, id);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int userNo = rs.getInt("USER_NO");
				id = rs.getString("ID");
				pwd = rs.getString("PASSWORD");
				String name = rs.getString("NAME");
				String nickname = rs.getString("NICKNAME");
				String phone = rs.getString("PHONE");
				String cerDate = rs.getString("FM_CRE_DATE");
				String upDate = rs.getString("FM_UP_DATE");
				String authority = rs.getString("AUTHORITY");

				AdminDto adminDto = new AdminDto(userNo, id, pwd, name, nickname, phone, cerDate, upDate, authority);

				return adminDto;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료

		return null;
	}

	// footer 가이드라인 조회
	public List<String> selectFooterList(String id, String pwd) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "";
			sql += "";
			sql += " ";
			sql += " ";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

			} else {

			}

		} catch (Exception e) {
			// TODO: handle exception

		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료

		return null;
	}

}

package gudiSpring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.user.dto.UserDto;

public class UserDao {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// 회원가입
	public int userInsert(UserDto userDto) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;

		try {

			String sql = "";
			sql += "INSERT INTO USER_INFO";
			sql += " (USER_NO, NAME, NICKNAME, ID, PASSWORD, PHONE)";
			sql += " VALUES (USER_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

			pstmt = connection.prepareStatement(sql);

			String name = userDto.getName();
			String nickname = userDto.getNickname();
			String id = userDto.getId();
			String password = userDto.getPassword();
			String phone = userDto.getPhone();

			pstmt.setString(1, name);
			pstmt.setString(2, nickname);
			pstmt.setString(3, id);
			pstmt.setString(4, password);
			pstmt.setString(5, phone);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료
		return result;
	}

	// 로그인
	public UserDto userLogin(String id, String pwd) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserDto userDto = null;

		try {
			String sql = "";
			sql += "SELECT USER_NO, ID, PASSWORD, AUTHORITY";
			sql += " FROM USER_INFO";
			sql += " WHERE ID = ? AND PASSWORD = ?";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			pstmt.setString(colIndex++, id);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				int userNo = rs.getInt("USER_NO");
				id = rs.getString("ID");
				pwd = rs.getString("PASSWORD");
				String authority = rs.getString("AUTHORITY");

				userDto = new UserDto(userNo, id, pwd, authority);

				return userDto;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally 종료
		return userDto;
	}

	public int checkNickname(String nickname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int nickResult = -1;

		try {
			String sql = "";
			sql += "SELECT USER_NO, NICKNAME";
			sql += " FROM USER_INFO";
			sql += " WHERE NICKNAME = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, nickname);

			rs = pstmt.executeQuery();

			if (rs.next()) { // 닉네임이있을경우 1, 닉네임이없을경우 -1

				nickResult = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally 종료

		return nickResult;
	}

	public int checkId(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int idResult = -1;

		try {
			String sql = "";
			sql += "SELECT USER_NO, ID";
			sql += " FROM USER_INFO";
			sql += " WHERE ID = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) { // 닉네임이있을경우 1, 닉네임이없을경우 -1
				idResult = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally 종료

		return idResult;
	}

	// 모든 사용자 조회
	public List<UserDto> selectUserList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "";
			sql += "SELECT U.USER_NO, U.NAME, U.NICKNAME, U.ID";
			sql += " , TO_CHAR(U.CREATED_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CRE_DATE";
			sql += " , U.AUTHORITY, U.USER_LEAVE";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM BOARD_CONTENT BC";
			sql += " WHERE BC.USER_NO = U.USER_NO) AS BOARD_COUNT";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM (SELECT BCC.USER_NO FROM BOARD_CONTENT_COMMENT BCC";
			sql += " UNION ALL";
			sql += " SELECT PC.USER_NO FROM PLACE_COMMENT PC) CC";
			sql += " WHERE CC.USER_NO = U.USER_NO) AS COMMENT_COUNT";
			sql += " FROM USER_INFO U";
			sql += " WHERE AUTHORITY <> 'admin'";

			pstmt = connection.prepareStatement(sql);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();

			int userNo = 0;
			String userName = "";
			String userNickname = "";
			String id = "";
			String creDate = "";
			String authority = "";
			String userLeave = "";
			int postCount = 0;
			int commentCount = 0;

			ArrayList<UserDto> userList = new ArrayList<UserDto>();

			UserDto userDto = null;

			while (rs.next()) {
				userNo = rs.getInt("USER_NO");
				userName = rs.getString("NAME");
				userNickname = rs.getString("NICKNAME");
				id = rs.getString("ID");
				creDate = rs.getString("FM_CRE_DATE");
				authority = rs.getString("AUTHORITY");
				userLeave = rs.getString("USER_LEAVE");
				postCount = rs.getInt("BOARD_COUNT");
				commentCount = rs.getInt("COMMENT_COUNT");

				userDto = new UserDto(userNo, userName, userNickname, id, creDate, authority, userLeave, postCount,
				    commentCount);

				userList.add(userDto);
			}

			return userList;

		} catch (Exception e) {
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

	// 사용자 검색 기능(이름, 닉네임, 아이디) => 여러 명 나올 수 있음
	public List<UserDto> searchUserList(String param) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<UserDto> userList = new ArrayList<UserDto>();

		try {
			String sql = "";
			sql += "SELECT U.USER_NO, U.NAME, U.NICKNAME, U.ID";
			sql += " , TO_CHAR(U.CREATED_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CRE_DATE";
			sql += " , U.AUTHORITY, U.USER_LEAVE";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM BOARD_CONTENT BC";
			sql += " WHERE BC.USER_NO = U.USER_NO) AS BOARD_COUNT";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM (SELECT BCC.USER_NO FROM BOARD_CONTENT_COMMENT BCC";
			sql += " UNION ALL";
			sql += " SELECT PC.USER_NO FROM PLACE_COMMENT PC) CC";
			sql += " WHERE CC.USER_NO = U.USER_NO) AS COMMENT_COUNT";
			sql += " FROM USER_INFO U";
			sql += " WHERE AUTHORITY <> 'admin'";
			sql += " AND (U.NAME = ? OR U.NICKNAME = ? OR U.ID = ?)";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			pstmt.setString(colIndex++, param);
			pstmt.setString(colIndex++, param);
			pstmt.setString(colIndex, param);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();

			int userNo = 0;
			String userName = "";
			String userNickname = "";
			String id = "";
			String creDate = "";
			String authority = "";
			String userLeave = "";
			int postCount = 0;
			int commentCount = 0;

			UserDto userDto = null;

			while (rs.next()) {
				userNo = rs.getInt("USER_NO");
				userName = rs.getString("NAME");
				userNickname = rs.getString("NICKNAME");
				id = rs.getString("ID");
				creDate = rs.getString("FM_CRE_DATE");
				authority = rs.getString("AUTHORITY");
				userLeave = rs.getString("USER_LEAVE");
				postCount = rs.getInt("BOARD_COUNT");
				commentCount = rs.getInt("COMMENT_COUNT");

				userDto = new UserDto(userNo, userName, userNickname, id, creDate, authority, userLeave, postCount,
				    commentCount);

				userList.add(userDto);

				return userList;
			}

		} catch (Exception e) {
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

		return userList;
	}

	// 사용자 삭제 => 여러 명(default)

	public int deleteUsers(ArrayList<Integer> removeUserNoList) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "";

			for (int i = 0; i < removeUserNoList.size(); i++) {
				sql += "DELETE FROM USER_INFO";
				sql += " WHERE USER_NO = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, removeUserNoList.get(i));
			}

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료

		return result;
	}
}

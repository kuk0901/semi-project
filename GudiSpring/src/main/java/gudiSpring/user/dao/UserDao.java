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

	// user 회원가입
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

	public UserDto userLogin(String id, String password) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT USER_NO, ID, PASSWORD, AUTHORITY";
			sql += " FROM USER_INFO";
			sql += " WHERE ID = ? AND PASSWORD = ?";

			pstmt = connection.prepareStatement(sql);

			int index = 1;

			pstmt.setString(index++, id);
			pstmt.setString(index, password);

			rs = pstmt.executeQuery();

			UserDto userDto = null;

			if (rs.next()) {
				userDto = new UserDto(rs.getInt("USER_NO"), rs.getString("ID"), rs.getString("PASSWORD"),
				    rs.getString("AUTHORITY"));
			}

			return userDto;

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
		}
		return null;
	}

	// user 닉네임 중복검사
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

	// user 아이디 중복검사
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

	public int signinCheck(String id, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int idResult = -1;

		try {
			String sql = "";

			sql += "SELECT USER_NO, ID";
			sql += " FROM USER_INFO";
			sql += " WHERE ID = ? AND PASSWORD = ?";
			
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

	public UserDto userSelect(int userNo) throws Exception {
		UserDto userDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";

			sql += "SELECT USER_NO, NAME, NICKNAME, ID, PASSWORD, PHONE, AUTHORITY, USER_LEAVE";
			sql += " FROM USER_INFO";
			sql += " WHERE USER_NO = ?";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			rs = pstmt.executeQuery();

			String name = "";
			String nickname = "";
			String id = "";
			String password = "";
			String phone = "";
			String authority = "";
			String userLeave = "";

			if (rs.next()) {
				userNo = rs.getInt("USER_NO");
				name = rs.getString("NAME");
				nickname = rs.getString("NICKNAME");
				id = rs.getString("ID");
				password = rs.getString("PASSWORD");
				phone = rs.getString("PHONE");
				authority = rs.getString("AUTHORITY");
				userLeave = rs.getString("USER_LEAVE");

				userDto = new UserDto(userNo, name, nickname, id, password, phone, authority, userLeave);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
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

	public int userUpdate(UserDto userDto) throws SQLException {
		int result = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "";
			sql += "UPDATE USER_INFO";
			sql += " SET NAME = ?, NICKNAME = ?, ID = ?, PHONE = ?";
			sql += " WHERE USER_NO = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, userDto.getName());
			pstmt.setString(2, userDto.getNickname());
			pstmt.setString(3, userDto.getId());
			pstmt.setString(4, userDto.getPhone());
			pstmt.setInt(5, userDto.getUserNo());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally 종료
		return result;
	}

	// 모든 사용자 조회
	public List<UserDto> selectUserList(int start, int pageSize) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<UserDto> userList = new ArrayList<UserDto>();

		try {
			String sql = "";
			sql += "SELECT USER_NO, NAME, NICKNAME, ID";
			sql += " , TO_CHAR(CREATED_DATE, 'YYYY\"년\"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CRE_DATE";
			sql += " , AUTHORITY, USER_LEAVE, BOARD_COUNT, COMMENT_COUNT";
			sql += " FROM (SELECT U.USER_NO, U.NAME, U.NICKNAME, U.ID, U.CREATED_DATE, U.AUTHORITY, U.USER_LEAVE";
			sql += " , (SELECT COUNT(*) FROM BOARD_CONTENT BC WHERE BC.USER_NO = U.USER_NO) AS BOARD_COUNT";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM (SELECT USER_NO FROM BOARD_CONTENT_COMMENT";
			sql += " UNION ALL";
			sql += " SELECT USER_NO FROM PLACE_COMMENT) CC"; // 두 개의 테이블을 합침
			sql += " WHERE CC.USER_NO = U.USER_NO) AS COMMENT_COUNT"; // 작성한 글 수
			sql += " , ROW_NUMBER() OVER (ORDER BY U.USER_NO) AS RN"; // 결과 집합의 각 행에 순차적인 고유 번호를 할당 => 페이징 처리에 활용, 특정 범위의 행을
			                                                          // 선택하는 데 사용
			sql += " FROM USER_INFO U";
			sql += " WHERE U.AUTHORITY <> 'admin')";
			sql += " WHERE RN BETWEEN ? AND ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, start + pageSize - 1);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();

			UserDto userDto = null;

			while (rs.next()) {

				userDto = new UserDto(rs.getInt("USER_NO"), rs.getString("NAME"), rs.getString("NICKNAME"), rs.getString("ID"),
				    rs.getString("FM_CRE_DATE"), rs.getString("AUTHORITY"), rs.getString("USER_LEAVE"),
				    rs.getInt("BOARD_COUNT"), rs.getInt("COMMENT_COUNT"));

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

	// user count
	public int userTotalCount() {
		int totalCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT COUNT(USER_NO)";
			sql += " FROM USER_INFO";
			sql += " WHERE AUTHORITY = 'user'";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				totalCount = rs.getInt(1); // 첫 번째 칼럼의 값
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

		return totalCount;
	}

	public int userSearchTotalCount(String userName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;

		try {
			// 사용자 이름에 대한 조건을 포함한 SQL 쿼리
			String sql = "";
			sql += "SELECT COUNT(*) FROM USER_INFO ";
			sql += " WHERE AUTHORITY = 'user'";
			sql += " AND NAME LIKE UPPER(?)";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + userName + "%"); // 부분 검색을 위해 LIKE 사용

			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt(1); // 첫 번째 컬럼의 값을 가져옴
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return totalCount; // 검색된 사용자 수 반환
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
			sql += " AND (U.NAME LIKE ? OR U.NICKNAME LIKE ? OR U.ID LIKE ?)";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			String searchParam = "%" + param + "%";

			pstmt.setString(colIndex++, searchParam);
			pstmt.setString(colIndex++, searchParam);
			pstmt.setString(colIndex, searchParam);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();

			UserDto userDto = null;

			while (rs.next()) {
				userDto = new UserDto(rs.getInt("USER_NO"), rs.getString("NAME"), rs.getString("NICKNAME"), rs.getString("ID"),
				    rs.getString("FM_CRE_DATE"), rs.getString("AUTHORITY"), rs.getString("USER_LEAVE"),
				    rs.getInt("BOARD_COUNT"), rs.getInt("COMMENT_COUNT"));

				userList.add(userDto);
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

	// 사용자 삭제 => 여러 명(소수)
	// 배치 처리를 적용한 버전(다수), 현재는 소수 버전
	public int deleteUsers(ArrayList<Integer> removeUserNoList) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM USER_INFO WHERE USER_NO = ?";

		try {
			pstmt = connection.prepareStatement(sql);

			for (Integer userNo : removeUserNoList) {
				pstmt.setInt(1, userNo);
				result += pstmt.executeUpdate();
			}

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

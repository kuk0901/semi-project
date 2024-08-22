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
			sql += "SELECT USER_NO, ID, PASSWORD, AUTHORITY";
			sql += " FROM USER_INFO";
			sql += " WHERE ID = ?";
			sql += " AND PASSWORD = ?";
			sql += " AND AUTHORITY = 'admin'";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			pstmt.setString(colIndex++, id);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int userNo = rs.getInt("USER_NO");
				id = rs.getString("ID");
				pwd = rs.getString("PASSWORD");

				AdminDto adminDto = new AdminDto(userNo, id, pwd);

				return adminDto;
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

		return null;
	}

	public boolean adminExistPwd(int userNo, String pwd) {
		boolean result = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT COUNT(*)";
			sql += " FROM USER_INFO";
			sql += " WHERE USER_NO = ?";
			sql += " AND PASSWORD = ?";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			pstmt.setInt(colIndex++, userNo);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 결과가 0보다 크면 사용자가 존재함
				result = rs.getInt(1) > 0;
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
		return result;
	}

	// admin list
	public List<AdminDto> selectAdminList(int start, int pageSize) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AdminDto> adminList = new ArrayList<AdminDto>();

		try {
			String sql = "";
			sql += "SELECT USER_NO, NAME, NICKNAME, ID";
			sql += " , TO_CHAR(CREATED_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CRE_DATE";
			sql += " , AUTHORITY, BOARD_COUNT, COMMENT_COUNT";
			sql += " FROM (SELECT U.USER_NO, U.NAME, U.NICKNAME, U.ID, U.CREATED_DATE, U.AUTHORITY";
			sql += " , (SELECT COUNT(*) FROM BOARD_CONTENT BC WHERE BC.USER_NO = U.USER_NO) AS BOARD_COUNT";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM (SELECT USER_NO FROM BOARD_CONTENT_COMMENT";
			sql += " UNION ALL";
			sql += " SELECT USER_NO FROM PLACE_COMMENT) CC"; // 두 개의 테이블을 합침
			sql += " WHERE CC.USER_NO = U.USER_NO) AS COMMENT_COUNT"; // 작성한 글 수
			sql += " , ROW_NUMBER() OVER (ORDER BY U.USER_NO) AS RN"; // 결과 집합의 각 행에 순차적인 고유 번호를 할당 => 페이징 처리에 활용, 특정 범위의 행을
			                                                          // 선택하는 데 사용
			sql += " FROM USER_INFO U";
			sql += " WHERE U.AUTHORITY = 'admin')";
			sql += " WHERE RN BETWEEN ? AND ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, start + pageSize - 1);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();

			AdminDto adminDto = null;

			while (rs.next()) {

				adminDto = new AdminDto(rs.getInt("USER_NO"), rs.getString("NAME"), rs.getString("NICKNAME"),
				    rs.getString("ID"), rs.getString("FM_CRE_DATE"), rs.getString("AUTHORITY"), rs.getInt("BOARD_COUNT"),
				    rs.getInt("COMMENT_COUNT"));

				adminList.add(adminDto);
			}

			return adminList;

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

	// admin count
	public int adminTotalCount() {
		int totalCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT COUNT(USER_NO)";
			sql += " FROM USER_INFO";
			sql += " WHERE AUTHORITY = 'admin'";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt(1); // 첫 번째 칼럼
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

	// 사용자 검색 기능(이름, 닉네임, 아이디) => 여러 명 나올 수 있음
	public List<AdminDto> searchAdminList(String param) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<AdminDto> adminList = new ArrayList<AdminDto>();

		try {
			String sql = "";
			sql += "SELECT U.USER_NO, U.NAME, U.NICKNAME, U.ID";
			sql += " , TO_CHAR(U.CREATED_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CRE_DATE";
			sql += " , U.AUTHORITY";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM BOARD_CONTENT BC";
			sql += " WHERE BC.USER_NO = U.USER_NO) AS BOARD_COUNT";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM (SELECT BCC.USER_NO FROM BOARD_CONTENT_COMMENT BCC";
			sql += " UNION ALL";
			sql += " SELECT PC.USER_NO FROM PLACE_COMMENT PC) CC";
			sql += " WHERE CC.USER_NO = U.USER_NO) AS COMMENT_COUNT";
			sql += " FROM USER_INFO U";
			sql += " WHERE AUTHORITY = 'admin'";
			sql += " AND (U.NAME LIKE ? OR U.NICKNAME LIKE ? OR U.ID LIKE ?)";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			String searchParam = "%" + param + "%";

			pstmt.setString(colIndex++, searchParam);
			pstmt.setString(colIndex++, searchParam);
			pstmt.setString(colIndex, searchParam);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();

			AdminDto adminDto = null;

			while (rs.next()) {
				adminDto = new AdminDto(rs.getInt("USER_NO"), rs.getString("NAME"), rs.getString("NICKNAME"),
				    rs.getString("ID"), rs.getString("FM_CRE_DATE"), rs.getString("AUTHORITY"), rs.getInt("BOARD_COUNT"),
				    rs.getInt("COMMENT_COUNT"));

				adminList.add(adminDto);
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

		return adminList;
	}

	// 관리자 검색 수
	public int adminSearchTotalCount(String adminName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;

		try {
			// 사용자 이름에 대한 조건을 포함한 SQL 쿼리
			String sql = "";
			sql += "SELECT COUNT(*) FROM USER_INFO ";
			sql += " WHERE AUTHORITY = 'admin'";
			sql += " AND NAME LIKE UPPER(?)";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + adminName + "%"); // 부분 검색을 위해 LIKE 사용

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

	// 관리자 디테일 페이지 전용
	public AdminDto selectOneAdmin(int adminNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminDto adminDto = null;
		
		try {
			String sql = "";
			sql += "SELECT U.USER_NO, U.NAME, U.NICKNAME, U.ID, U.PASSWORD, U.PHONE";
			sql += " , TO_CHAR(U.CREATED_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CREATED_DATE";
			sql += " , TO_CHAR(U.UPDATE_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_UPDATE_DATE";
			sql += " , U.AUTHORITY";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM BOARD_CONTENT BC";
			sql += " WHERE BC.USER_NO = U.USER_NO) AS BOARD_COUNT";
			sql += " , (SELECT COUNT(*)";
			sql += " FROM (SELECT BCC.USER_NO FROM BOARD_CONTENT_COMMENT BCC";
			sql += " UNION ALL";
			sql += " SELECT PC.USER_NO FROM PLACE_COMMENT PC) CC";
			sql += " WHERE CC.USER_NO = U.USER_NO) AS COMMENT_COUNT";
			sql += " FROM USER_INFO U";
			sql += " WHERE U.AUTHORITY = 'admin'";
			sql += " AND U.USER_NO = ?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, adminNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adminDto = new AdminDto(
							rs.getInt("USER_NO"),
							rs.getString("NAME"),
							rs.getString("NICKNAME"),
							rs.getString("ID"),
							rs.getString("PASSWORD"),
							rs.getString("PHONE"),
							rs.getString("FM_CREATED_DATE"),
							rs.getString("FM_UPDATE_DATE"),
							rs.getString("AUTHORITY"),
							rs.getInt("BOARD_COUNT"),
							rs.getInt("COMMENT_COUNT")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료
		
		return adminDto;
	}
	
	// 관리자 정보 수정
	public int updateAdmin(int userNo, String name, String id, String pwd, String phone) {
		int result = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "";
			sql += "UPDATE USER_INFO";
			sql += " SET NAME = ?, ID = ?, PASSWORD = ?, PHONE = ?";
			sql += " , UPDATE_DATE = SYSDATE";
			sql += " WHERE USER_NO = ?";

			int colIndex = 1;

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(colIndex++, name);
			pstmt.setString(colIndex++, id);
			pstmt.setString(colIndex++, pwd);
			pstmt.setString(colIndex++, phone);
			pstmt.setInt(colIndex, userNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료

		return result;
	}

	public int deleteAdmin(int userNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM USER_INFO WHERE USER_NO = ?";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
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
		} // finally 종료

		return null;
	}

}

package gudiSpring.board.dao.freeboard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gudiSpring.board.dto.freeboard.BoardDto;
import gudiSpring.board.dto.reviewboard.ReviewBoardDto;

public class BoardDao {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	// 페이징을 위한 전체 게시글 수 조회-페이징용
	public int getFreeBoardTotalCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM BOARD_CONTENT WHERE CONTENT_BOARD_INFO_NO = 2";
		try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}
	}

	// 전체조회
	public List<BoardDto> selectList(int startRow, int pageSize) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT * FROM ( ";
			sql += "   SELECT ROWNUM AS rnum, a.* FROM ( ";
			sql += "       SELECT CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, ";
			sql += "       CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO ";
			sql += "       FROM BOARD_CONTENT ";
			sql += "       WHERE CONTENT_BOARD_INFO_NO = 2 ";
			sql += "       ORDER BY CONTENT_NO DESC ";
			sql += "   ) a ";
			sql += "   WHERE ROWNUM <= ? "; // endRow
			sql += ") ";
			sql += "WHERE rnum >= ?"; // startRow

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, startRow + pageSize - 1); // endRow 계산
			pstmt.setInt(2, startRow); // startRow 지정

			rs = pstmt.executeQuery();

			ArrayList<BoardDto> BoardList = new ArrayList<BoardDto>();

			BoardDto boardDto = null;
			while (rs.next()) {
				int contentNo = rs.getInt("CONTENT_NO");
				String contentSubject = rs.getString("CONTENT_SUBJECT");
				String contentText = rs.getString("CONTENT_TEXT");
				String contentFile = rs.getString("CONTENT_FILE");// 이미지 파일 경로 설정
				int contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
				Date contentCreDate = rs.getDate("CONTENT_CRE_DATE");
				Date contentUpdateDate = rs.getDate("CONTENT_UPDATE_DATE");
				int userNo = rs.getInt("USER_NO");

				// DTO 객체 생성 및 데이터 설정
				boardDto = new BoardDto(contentNo, contentSubject, contentText, contentFile, contentBoardInfoNo, contentCreDate,
				    contentUpdateDate, userNo);
				BoardList.add(boardDto);
			}

			return BoardList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	}

	// 게시글 상세 조회
	public BoardDto selectOne(int contentNo) throws SQLException {
		String sql = "SELECT CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO "
		    + "FROM BOARD_CONTENT " + "WHERE CONTENT_BOARD_INFO_NO = 2 AND CONTENT_NO = ? " + "ORDER BY CONTENT_NO DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, contentNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				contentNo = rs.getInt("CONTENT_NO");
				String contentSubject = rs.getString("CONTENT_SUBJECT");
				String contentText = rs.getString("CONTENT_TEXT");
				String contentFile = rs.getString("CONTENT_FILE");
				int contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
				Date contentCreDate = rs.getDate("CONTENT_CRE_DATE");
				Date contentUpdateDate = rs.getDate("CONTENT_UPDATE_DATE");
				int userNo = rs.getInt("USER_NO");

				return new BoardDto(contentNo, contentSubject, contentText, contentFile, contentBoardInfoNo, contentCreDate,
				    contentUpdateDate, userNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
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
		}

		return null;
	}// selectone종료

	// 게시글 추가
	public void addBoard(BoardDto boardDto) throws SQLException {
		String sql = "INSERT INTO BOARD_CONTENT (CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO) "
		    + "VALUES (CONTENT_NO_SEQ.NEXTVAL, ?, ?, ?, 2, SYSDATE, SYSDATE, ?)";
		// 자유게시판이니 2번,다른게시판으로수정시 수정할것
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, boardDto.getContentSubject());
			pstmt.setString(2, boardDto.getContentText());
			pstmt.setString(3, boardDto.getContentFile());// 추후설정필요
			pstmt.setInt(4, boardDto.getUserNo()); // 추후설정필요

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteBoardPost(int contentNo) throws SQLException {
		String sql = "DELETE FROM BOARD_CONTENT WHERE CONTENT_NO = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, contentNo);
			pstmt.executeUpdate();
		}
	}
	// BoardDao.java

	// 게시글 수정 메소드
	public void updateBoard(BoardDto boardDto) throws SQLException {
		String sql = "UPDATE BOARD_CONTENT SET CONTENT_SUBJECT = ?," + " CONTENT_TEXT = ?, CONTENT_FILE = ?, "
		    + "CONTENT_UPDATE_DATE = SYSDATE WHERE CONTENT_NO = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, boardDto.getContentSubject());
			pstmt.setString(2, boardDto.getContentText());
			pstmt.setString(3, boardDto.getContentFile());
			pstmt.setInt(4, boardDto.getContentNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 게시판(자유게시판 + 리뷰 게시판) 전체 조회 (페이징 적용)
	public List<ReviewBoardDto> selectBoardList(int startRow, int pageSize) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			 String sql = "";
       sql += "SELECT rnum, CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, ";
       sql += "CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO, NICKNAME, BOARD_INFO_NAME FROM ( ";
       sql += "   SELECT ROWNUM AS rnum, a.CONTENT_NO, a.CONTENT_SUBJECT, a.CONTENT_TEXT, ";
       sql += "   a.CONTENT_BOARD_INFO_NO, a.CONTENT_CRE_DATE, a.CONTENT_UPDATE_DATE, a.USER_NO, u.NICKNAME, b.BOARD_INFO_NAME FROM ( ";
       sql += "       SELECT CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, ";
       sql += "       CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO ";
       sql += "       FROM BOARD_CONTENT ";
       sql += "       ORDER BY CONTENT_NO DESC ";
       sql += "   ) a ";
       sql += "   LEFT JOIN USER_INFO u ON a.USER_NO = u.USER_NO ";
       sql += "   LEFT JOIN BOARD_INFO b ON a.CONTENT_BOARD_INFO_NO = b.BOARD_INFO_NO ";  // 추가된 JOIN
       sql += "   WHERE ROWNUM <= ? ";  // endRow
       sql += ") ";
       sql += "WHERE rnum >= ?";  // startRow

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, startRow + pageSize - 1); // endRow 계산
			pstmt.setInt(2, startRow); // startRow 지정

			rs = pstmt.executeQuery();

			List<ReviewBoardDto> boardList = new ArrayList<>();
			while (rs.next()) {
				int contentNo = rs.getInt("CONTENT_NO");
				String contentSubject = rs.getString("CONTENT_SUBJECT");
				String contentText = rs.getString("CONTENT_TEXT");
				int contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
				Date contentCreDate = rs.getDate("CONTENT_CRE_DATE");
				Date contentUpdateDate = rs.getDate("CONTENT_UPDATE_DATE");
				int userNo = rs.getInt("USER_NO");
				String nickname = rs.getString("NICKNAME"); // USER_INFO 테이블에서 가져온 닉네임
				String boardInfoName = rs.getString("BOARD_INFO_NAME");

				ReviewBoardDto boardDto = new ReviewBoardDto(contentNo, contentSubject, contentText, contentBoardInfoNo,
				    contentCreDate, contentUpdateDate, userNo, nickname, boardInfoName);
				boardList.add(boardDto);
			}

			return boardList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 게시글 총 개수 조회 (페이징을 위해 필요)
	public int getTotalCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM BOARD_CONTENT";
		try (PreparedStatement pstmt = connection.prepareStatement(sql); 
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}
	}

	public ReviewBoardDto selectDetail(int contentNo) throws SQLException {
		String sql = "SELECT bc.CONTENT_NO, bc.CONTENT_SUBJECT, bc.CONTENT_TEXT, bc.CONTENT_FILE, "
		    + "bc.CONTENT_BOARD_INFO_NO, bc.CONTENT_CRE_DATE, bc.CONTENT_UPDATE_DATE, bc.USER_NO, "
		    + "ui.NICKNAME, bi.BOARD_INFO_NAME " + "FROM BOARD_CONTENT bc "
		    + "JOIN USER_INFO ui ON bc.USER_NO = ui.USER_NO "
		    + "JOIN BOARD_INFO bi ON bc.CONTENT_BOARD_INFO_NO = bi.BOARD_INFO_NO " + "WHERE bc.CONTENT_NO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBoardDto boardDetail = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, contentNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String contentText = rs.getString("CONTENT_TEXT");
				int contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
				String contentSubject = rs.getString("CONTENT_SUBJECT");
				String contentFile = rs.getString("CONTENT_FILE"); // 파일 정보 추가
				String nickname = rs.getString("NICKNAME");
				List<String> contentFiles = contentFile != null ? Arrays.asList(contentFile.split(",")) : null; // 파일 리스트로 변환
				String boardInfoName = rs.getString("BOARD_INFO_NAME");
				Date contentCreDate = rs.getDate("CONTENT_CRE_DATE");
				Date contentUpdateDate = rs.getDate("CONTENT_UPDATE_DATE");
				int userNo = rs.getInt("USER_NO");

				boardDetail = new ReviewBoardDto(contentNo, contentSubject, contentText, contentFiles, contentBoardInfoNo,
				    contentCreDate, contentUpdateDate, userNo, nickname, boardInfoName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return boardDetail;
	}

	public void deletePost(int contentNo) throws SQLException {
		// 1. BOARD_CONTENT_IMG 테이블에서 해당 게시글 번호에 연결된 이미지를 삭제
		String deleteImagesSql = "DELETE FROM BOARD_CONTENT_IMG WHERE CONTENT_NO = ?";

		// 2. BOARD_CONTENT 테이블에서 게시글을 삭제
		String deletePostSql = "DELETE FROM BOARD_CONTENT WHERE CONTENT_NO = ?";

		try (PreparedStatement imgPstmt = connection.prepareStatement(deleteImagesSql);
		    PreparedStatement postPstmt = connection.prepareStatement(deletePostSql)) {

			// 이미지 삭제
			imgPstmt.setInt(1, contentNo);
			imgPstmt.executeUpdate();

			// 게시글 삭제
			postPstmt.setInt(1, contentNo);
			postPstmt.executeUpdate();
		}
	}

	// 게시글에 첨부된 파일 경로를 가져오는 메서드
	public List<String> getFilePathsByContentNo(int contentNo) throws SQLException {
		String sql = "SELECT CONTENT_FILE FROM BOARD_CONTENT WHERE CONTENT_NO = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, contentNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String contentFile = rs.getString("CONTENT_FILE");
					return contentFile != null ? Arrays.asList(contentFile.split(",")) : new ArrayList<>();
				}
			}
		}
		return new ArrayList<>();
	}

	public List<BoardDto> selectMapageContentList(int userNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		sql += "SELECT CONTENT_NO,CONTENT_SUBJECT,CONTENT_BOARD_INFO_NO,CONTENT_CRE_DATE,USER_NO";
		sql += " FROM BOARD_CONTENT";
		sql += " WHERE USER_NO = ? ";
		sql += " AND CONTENT_BOARD_INFO_NO = 2";

		ArrayList<BoardDto> myPageBoardList = new ArrayList<BoardDto>();
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int contentNo = rs.getInt("CONTENT_NO");
				String contentSubject = rs.getString("CONTENT_SUBJECT");
				int contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
				Date contentCreDate = rs.getDate("CONTENT_CRE_DATE");

				// DTO 객체 생성 및 데이터 설정
				BoardDto boardDto = new BoardDto(contentNo, contentSubject,contentBoardInfoNo, contentCreDate, userNo);
				myPageBoardList.add(boardDto);
			}

			return myPageBoardList;
		} catch (Exception e) {
			// TODO: handle exception
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료
	}

}// 전체dao

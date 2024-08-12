package gudiSpring.freeboard.dao;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import gudiSpring.freeboard.dto.BoardDto;

public class BoardDao {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	  // 페이징을 위한 전체 게시글 수 조회-페이징용
    public int getTotalCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM BOARD_CONTENT WHERE CONTENT_BOARD_INFO_NO = 2";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }
	//전체조회
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
	        sql += "   WHERE ROWNUM <= ? ";  // endRow
	        sql += ") ";
	        sql += "WHERE rnum >= ?";  // startRow

			pstmt = connection.prepareStatement(sql);
			 pstmt = connection.prepareStatement(sql);
		        pstmt.setInt(1, startRow + pageSize - 1);  // endRow 계산
		        pstmt.setInt(2, startRow);  // startRow 지정
			
			rs = pstmt.executeQuery();

			

			ArrayList<BoardDto> BoardList = 
				new ArrayList<BoardDto>();

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
	            boardDto = new BoardDto(contentNo,contentSubject,
	                contentText,contentFile,contentBoardInfoNo,
	                contentCreDate,contentUpdateDate,userNo
	            );
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
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
	}
	 
   
	//게시글 상세 조회 
	  public BoardDto selectOne(int contentNo) 
			  throws SQLException {
			String sql = "SELECT CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO " +
			             "FROM BOARD_CONTENT " +
			              "WHERE CONTENT_BOARD_INFO_NO = 2 AND CONTENT_NO = ? " +
			              "ORDER BY CONTENT_NO DESC";
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

			                return new BoardDto(
			                    contentNo,
			                    contentSubject,
			                    contentText,
			                    contentFile,
			                    contentBoardInfoNo,
			                    contentCreDate,
			                    contentUpdateDate,
			                    userNo
			                );
			            }
			        }catch (Exception e) {
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
	  }//selectone종료
	// 게시글 추가
	  public void addBoard(BoardDto boardDto) throws SQLException {
	        String sql = "INSERT INTO BOARD_CONTENT (CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO) "
	                   + "VALUES (CONTENT_NO_SEQ.NEXTVAL, ?, ?, ?, 2, SYSDATE, SYSDATE, ?)";
//자유게시판이니 2번,다른게시판으로수정시 수정할것
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, boardDto.getContentSubject());
	            pstmt.setString(2, boardDto.getContentText());
	            pstmt.setString(3, boardDto.getContentFile());//추후설정필요
	            pstmt.setInt(4, boardDto.getUserNo()); //추후설정필요

	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	  
	  public void deletePost(int contentNo) throws SQLException {
	        String sql = "DELETE FROM BOARD_CONTENT WHERE CONTENT_NO = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, contentNo);
	            pstmt.executeUpdate();
	        }
	    }
	// BoardDao.java

	// 게시글 수정 메소드
	public void updateBoard(BoardDto boardDto) throws SQLException {
	    String sql = "UPDATE BOARD_CONTENT SET CONTENT_SUBJECT = ?,"
	    		+ " CONTENT_TEXT = ?, CONTENT_FILE = ?, "
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








}//전체dao

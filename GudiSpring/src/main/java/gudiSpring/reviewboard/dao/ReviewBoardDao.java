package gudiSpring.reviewboard.dao;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gudiSpring.reviewboard.dto.ReviewBoardDto;



public class ReviewBoardDao {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	// 전체 조회 (페이징 적용)
    public List<ReviewBoardDto> selectList(int startRow, int pageSize) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "";
            sql += "SELECT * FROM ( ";
            sql += "   SELECT ROWNUM AS rnum, a.* FROM ( ";
            sql += "       SELECT CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, ";
            sql += "       CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO ";
            sql += "       FROM BOARD_CONTENT ";
            sql += "       WHERE CONTENT_BOARD_INFO_NO = 3 "; // 게시판 번호에 따라 수정 필요
            sql += "       ORDER BY CONTENT_NO DESC ";
            sql += "   ) a ";
            sql += "   WHERE ROWNUM <= ? ";  // endRow
            sql += ") ";
            sql += "WHERE rnum >= ?";  // startRow

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, startRow + pageSize - 1);  // endRow 계산
            pstmt.setInt(2, startRow);  // startRow 지정
            
            rs = pstmt.executeQuery();

            List<ReviewBoardDto> boardList = new ArrayList<>();
            while (rs.next()) {
                int contentNo = rs.getInt("CONTENT_NO");
                String contentSubject = rs.getString("CONTENT_SUBJECT");
                String contentText = rs.getString("CONTENT_TEXT");
                String contentFile = rs.getString("CONTENT_FILE"); // 이미지 파일 경로 설정
                int contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
                Date contentCreDate = rs.getDate("CONTENT_CRE_DATE");
                Date contentUpdateDate = rs.getDate("CONTENT_UPDATE_DATE");
                int userNo = rs.getInt("USER_NO");

                List<String> contentFiles = contentFile != null ? Arrays.asList(contentFile.split(",")) : new ArrayList<>();

                ReviewBoardDto boardDto = new ReviewBoardDto(contentNo, contentSubject, contentText, contentFiles,
                        contentBoardInfoNo, contentCreDate, contentUpdateDate, userNo);
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
        String sql = "SELECT COUNT(*) FROM BOARD_CONTENT WHERE CONTENT_BOARD_INFO_NO = 3";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

   
	//게시글 상세 조회 
	  public ReviewBoardDto selectOne(int contentNo) 
			  throws SQLException {
			String sql = "SELECT CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO " +
			             "FROM BOARD_CONTENT " +
			              "WHERE CONTENT_BOARD_INFO_NO = 3 AND CONTENT_NO = ? " +
			              "ORDER BY CONTENT_NO DESC";
			 PreparedStatement pstmt = null; //자유게시판이니 2번,다른게시판으로수정시 수정할것
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
                List<String> contentFiles
                	= contentFile != null ? Arrays.asList(contentFile.split(",")) : new ArrayList<>();
                return new ReviewBoardDto(contentNo, 
                		contentSubject, contentText, 
                		contentFiles, contentBoardInfoNo, 
                		contentCreDate, contentUpdateDate, userNo);
           
			                
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
	  public void addBoard(ReviewBoardDto reviewboardDto) throws SQLException {
	        String sql = "INSERT INTO BOARD_CONTENT (CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO) "
	                   + "VALUES (CONTENT_NO_SEQ.NEXTVAL, ?, ?, ?, 3, SYSDATE, SYSDATE, ?)";
//자유게시판이니 2번,다른게시판으로수정시 수정할것
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, reviewboardDto.getContentSubject());
	            pstmt.setString(2, reviewboardDto.getContentText());
	            pstmt.setString(3, String.join(",", reviewboardDto.getContentFiles())); // 파일 경로를 콤마로 연결하여 저장/
	            pstmt.setInt(4, reviewboardDto.getUserNo()); //추후설정필요

	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	  
	  //게시글삭제
	  public void deletePost(int contentNo) throws SQLException {
	        String sql = "DELETE FROM BOARD_CONTENT WHERE CONTENT_NO = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, contentNo);
	            pstmt.executeUpdate();
	        }
	    }
	// BoardDao.java

	// 게시글 수정 메소드
	public void updateBoard(ReviewBoardDto reviewboardDto) throws SQLException {
	    String sql = "UPDATE BOARD_CONTENT SET CONTENT_SUBJECT = ?,"
	    		+ " CONTENT_TEXT = ?, CONTENT_FILE = ?, "
	    		+ "CONTENT_UPDATE_DATE = SYSDATE WHERE CONTENT_NO = ?";
	    
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, reviewboardDto.getContentSubject());
	        pstmt.setString(2, reviewboardDto.getContentText());
	        pstmt.setString(3, String.join(",", reviewboardDto.getContentFiles())); 
	        // 파일 경로를 콤마로 연결하여 저장
	        pstmt.setInt(4, reviewboardDto.getContentNo());
	        
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}








}//전체dao

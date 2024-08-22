package gudiSpring.board.dao.customerservice;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import gudiSpring.board.dto.customerservice.CustomerServiceDto;



public class CustomerServiceDao {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	// 전체 조회 (페이징 적용)
    public List<CustomerServiceDto> selectList(int startRow, int pageSize) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	String sql = "";
        	sql += "SELECT rnum, CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, ";
        	sql += "CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO FROM ( ";
        	sql += "   SELECT ROWNUM AS rnum, a.CONTENT_NO, a.CONTENT_SUBJECT, a.CONTENT_TEXT, a.CONTENT_FILE, ";
        	sql += "   a.CONTENT_BOARD_INFO_NO, a.CONTENT_CRE_DATE, a.CONTENT_UPDATE_DATE, a.USER_NO FROM ( ";
        	sql += "       SELECT CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, ";
        	sql += "       CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO ";
        	sql += "       FROM BOARD_CONTENT ";
        	sql += "       WHERE CONTENT_BOARD_INFO_NO = 4 "; // 게시판 번호에 따라 수정 필요
        	sql += "       ORDER BY CONTENT_NO DESC ";
        	sql += "   ) a ";
        	sql += "   WHERE ROWNUM <= ? ";  // endRow
        	sql += ") ";
        	sql += "WHERE rnum >= ?";  // startRow


            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, startRow + pageSize - 1);  // endRow 계산
            pstmt.setInt(2, startRow);  // startRow 지정
            
            rs = pstmt.executeQuery();

            List<CustomerServiceDto> boardList = new ArrayList<>();
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

                CustomerServiceDto boardDto = new CustomerServiceDto(contentNo, contentSubject, contentText, contentFiles,
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
        String sql = "SELECT COUNT(*) FROM BOARD_CONTENT WHERE CONTENT_BOARD_INFO_NO = 4";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

   

	//게시글 상세 조회 
    public CustomerServiceDto selectOne(int contentNo) throws SQLException {
    	 String sql = "SELECT b.CONTENT_NO, b.CONTENT_SUBJECT, b.CONTENT_TEXT, b.CONTENT_BOARD_INFO_NO, " +
                 "b.CONTENT_CRE_DATE, b.CONTENT_UPDATE_DATE, b.USER_NO, u.NICKNAME " +
                 "FROM BOARD_CONTENT b " +
                 "JOIN USER_INFO u ON b.USER_NO = u.USER_NO " +
                 "WHERE b.CONTENT_NO = ?";

        String imgSql = "SELECT CONTENT_IMG_PATH FROM BOARD_CONTENT_IMG WHERE CONTENT_NO = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, contentNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String contentSubject = rs.getString("CONTENT_SUBJECT");
                    String contentText = rs.getString("CONTENT_TEXT");
                    int contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
                    Date contentCreDate = rs.getDate("CONTENT_CRE_DATE");
                    Date contentUpdateDate = rs.getDate("CONTENT_UPDATE_DATE");
                    int userNo = rs.getInt("USER_NO");
                    String nickname = rs.getString("NICKNAME"); 

                    List<String> contentFiles = new ArrayList<>();
                    try (PreparedStatement imgPstmt = connection.prepareStatement(imgSql)) {
                        imgPstmt.setInt(1, contentNo);
                        try (ResultSet imgRs = imgPstmt.executeQuery()) {
                            while (imgRs.next()) {
                                contentFiles.add(imgRs.getString("CONTENT_IMG_PATH"));
                            }
                        }
                    }

                    return new CustomerServiceDto(contentNo, contentSubject,
                    		contentText, contentFiles, contentBoardInfoNo,
                    		contentCreDate, contentUpdateDate, userNo,nickname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
//selectone종료
	// 게시글 추가

		public void addBoard(CustomerServiceDto customerserviceDto) throws SQLException {
			// 게시글을 DB에 삽입하는 SQL 문
			String sql = "INSERT INTO BOARD_CONTENT (CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO) "
					+ "VALUES (CONTENT_NO_SEQ.NEXTVAL, ?, ?, 4, SYSDATE, SYSDATE, ?)";
 //리뷰그외 다른게시판시반드시수정**

		    
			
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, customerserviceDto.getContentSubject());
		        pstmt.setString(2, customerserviceDto.getContentText());
		        pstmt.setInt(3, customerserviceDto.getUserNo());

		        int rowsAffected = pstmt.executeUpdate();
		        

		        // CONTENT_NO 값 수동 조회
		        String generatedKeyQuery = "SELECT CONTENT_NO_SEQ.CURRVAL FROM DUAL";
		        try (PreparedStatement keyPstmt = connection.prepareStatement(generatedKeyQuery);
		             ResultSet rs = keyPstmt.executeQuery()) {
		            if (rs.next()) {
		                int contentNo = rs.getInt(1);
		              

		                // 이미지 파일 경로들을 DB에 삽입
		                for (String filePath : customerserviceDto.getContentFiles()) {
		                    String imgSql = "INSERT INTO BOARD_CONTENT_IMG (BOARD_IMG_NO, CONTENT_NO, CONTENT_IMG_PATH) "
		                                  + "VALUES (BOARD_IMG_NO.NEXTVAL, ?, ?)";
		                    try (PreparedStatement imgPstmt = connection.prepareStatement(imgSql)) {
		                        imgPstmt.setInt(1, contentNo);
		                        imgPstmt.setString(2, filePath);
		                        imgPstmt.executeUpdate();
		                    }
		                    catch (SQLException e) {
	                            e.printStackTrace();
	                            throw new SQLException("이미지 경로 삽입 중 오류 발생: " + e.getMessage());
	                        }
		                }
		            }
		        }
			} catch (SQLException e) {
				e.printStackTrace();
				throw e; // 예외 발생 시 다시 던짐
			}
		}

	  
	  //게시글삭제
	  public void deletePost(int contentNo) throws SQLException {
		// 1. 먼저 BOARD_CONTENT_IMG 테이블에서 해당 게시글 번호에 연결된 이미지를 삭제
		    String deleteImagesSql = "DELETE FROM BOARD_CONTENT_IMG WHERE CONTENT_NO = ?";
		    
		    // 2. 그 다음 BOARD_CONTENT 테이블에서 게시글을 삭제
		    String deletePostSql = "DELETE FROM BOARD_CONTENT WHERE CONTENT_NO = ?";

		    try (
		        // BOARD_CONTENT_IMG에서 이미지 삭제
		        PreparedStatement imgPstmt = connection.prepareStatement(deleteImagesSql);
		        PreparedStatement postPstmt = connection.prepareStatement(deletePostSql)) {
		        
		        // 게시글 번호를 파라미터로 설정
		        imgPstmt.setInt(1, contentNo);
		        imgPstmt.executeUpdate(); // 이미지 경로 삭제

		        // 게시글 삭제
		        postPstmt.setInt(1, contentNo);
		        postPstmt.executeUpdate();
		    }
	    }


	// 게시글 수정 메소드
	public void updateBoard(CustomerServiceDto customerserviceDto) throws SQLException {
		 String sql = "UPDATE BOARD_CONTENT SET CONTENT_SUBJECT = ?,"
	               + " CONTENT_TEXT = ?, CONTENT_UPDATE_DATE = SYSDATE "
	               + "WHERE CONTENT_NO = ?";

		 	
		  try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, customerserviceDto.getContentSubject());
		        pstmt.setString(2, customerserviceDto.getContentText());
		        pstmt.setInt(3, customerserviceDto.getContentNo());
	        
	   
	        pstmt.executeUpdate();
	        

	        // 기존 이미지 파일 경로 삭제 SQL 문
	        String deleteImgSql = "DELETE FROM BOARD_CONTENT_IMG WHERE CONTENT_NO = ?";
	        try (PreparedStatement deleteImgPstmt = connection.prepareStatement(deleteImgSql)) {
	            deleteImgPstmt.setInt(1, customerserviceDto.getContentNo());
	            deleteImgPstmt.executeUpdate();
	        }

	        // 새로운 이미지 파일 경로 삽입
	        for (String filePath : customerserviceDto.getContentFiles()) {
	            String imgSql = "INSERT INTO BOARD_CONTENT_IMG (BOARD_IMG_NO, CONTENT_NO, CONTENT_IMG_PATH) "
	                          + "VALUES (BOARD_IMG_NO.NEXTVAL, ?, ?)";
	            try (PreparedStatement imgPstmt = connection.prepareStatement(imgSql)) {
	                imgPstmt.setInt(1, customerserviceDto.getContentNo());
	                imgPstmt.setString(2, filePath);
	                imgPstmt.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
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
	//userno->닉네임
	public String getNicknameByUserNo(int userNo) throws SQLException {
	    String sql = "SELECT nickname FROM USER_INFO WHERE user_no = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, userNo);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getString("nickname");
	            }
	        }
	    }
	    return null; // 해당 user_no에 대한 nickname이 없을 경우
	}
	//게시판이름가져오는 메서드
	public String getBoardInfoName(int boardInfoNo) throws SQLException {
        String sql = "SELECT board_info_name FROM board_info WHERE board_info_no = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, boardInfoNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("board_info_name");
                }
            }
        }
        return null;
    }
	//게시판수정때쓰는 파일지우기
	public void deleteImagePathFromDB(int contentNo, String fileName) throws SQLException {
	    String sql = "DELETE FROM BOARD_CONTENT_IMG WHERE CONTENT_NO = ? AND CONTENT_IMG_PATH = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, contentNo);
	        pstmt.setString(2, fileName);
	        pstmt.executeUpdate();
	    }
	}

}//전체dao

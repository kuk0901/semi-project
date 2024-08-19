package gudiSpring.board.dao.notice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.board.dto.freeboard.BoardDto;
import gudiSpring.board.dto.notice.NoticeDto;

public class NoticeDao {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	
	 public List<NoticeDto> getAllNotices() {
	        List<NoticeDto> notices = new ArrayList<>();
	        String sql = "SELECT * FROM BOARD_CONTENT "
	        		+ " WHERE CONTENT_BOARD_INFO_NO = 1";
	        

	        // NoticeDto 객체를 while 루프 밖에서 선언
	        int contentNo = 0;
	        String contentSubject = "";
	        String contentText = "";
	        String contentFile = "";
	        int contentBoardInfoNo = 0;
	        Date contentCreDate = null;
	        Date contentUpdateDate = null;
	        int userNo = 0;
	        try ( 
	        	PreparedStatement pstmt = connection.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	        	
	            while (rs.next()) {
	                NoticeDto notice = new NoticeDto();
	                contentNo = rs.getInt("CONTENT_NO");
	                contentSubject = rs.getString("CONTENT_SUBJECT");
	                contentText = rs.getString("CONTENT_TEXT");
	                contentFile = rs.getString("CONTENT_FILE");
	                contentBoardInfoNo = rs.getInt("CONTENT_BOARD_INFO_NO");
	                contentCreDate = rs.getDate("CONTENT_CRE_DATE");
	                contentUpdateDate = rs.getDate("CONTENT_UPDATE_DATE");
	                userNo = rs.getInt("USER_NO");

	                notice.setContentNo(contentNo);
	                notice.setContentSubject(contentSubject);
	                notice.setContentText(contentText);
	                notice.setContentFile(contentFile);
	                notice.setContentBoardInfoNo(contentBoardInfoNo);
	                notice.setContentCreDate(contentCreDate);
	                notice.setContentUpdateDate(contentUpdateDate);
	                notice.setUserNo(userNo);

	                notices.add(notice);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return notices;
	    } //getAllNotices 종료


	public NoticeDto getNoticeById(int contentNo) {
		NoticeDto notice = null;
	    String sql = "SELECT * FROM BOARD_CONTENT "
	    		+ " WHERE CONTENT_NO = ? AND"
	    		+ " CONTENT_BOARD_INFO_NO = 1";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, contentNo);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                notice = new NoticeDto();
	                notice.setContentNo(rs.getInt("CONTENT_NO"));
	                notice.setContentSubject(rs.getString("CONTENT_SUBJECT"));
	                notice.setContentText(rs.getString("CONTENT_TEXT"));
	                notice.setContentFile(rs.getString("CONTENT_FILE"));
	                notice.setContentCreDate(rs.getDate("CONTENT_CRE_DATE"));
	                notice.setContentUpdateDate(rs.getDate("CONTENT_UPDATE_DATE"));
	                notice.setUserNo(rs.getInt("USER_NO"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return notice;
	}
	
	// 게시글 추가
		  public void addNoticeBoard(NoticeDto noticeDto) throws SQLException {
		        String sql = "INSERT INTO BOARD_CONTENT (CONTENT_NO, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_BOARD_INFO_NO, CONTENT_CRE_DATE, CONTENT_UPDATE_DATE, USER_NO) "
		                   + "VALUES (CONTENT_NO_SEQ.NEXTVAL, ?, ?, ?, 1, SYSDATE, SYSDATE, ?)";
	//자유게시판이니 2번,다른게시판으로수정시 수정할것
		        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		            pstmt.setString(1, noticeDto.getContentSubject());
		            pstmt.setString(2, noticeDto.getContentText());
		            pstmt.setString(3, noticeDto.getContentFile());//추후설정필요
		            pstmt.setInt(4, noticeDto.getUserNo()); //추후설정필요

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
		public void updateNoticeBoard(BoardDto boardDto) throws SQLException {
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

	
}

package gudiSpring.notice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.notice.dto.NoticeDto;

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
	
	
	
}

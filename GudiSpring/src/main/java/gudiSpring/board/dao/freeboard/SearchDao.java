//package gudiSpring.board.dao.freeboard;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import gudiSpring.board.dto.freeboard.BoardDto;
//import gudiSpring.board.dto.freeboard.FreeBoardDto;
//import gudiSpring.board.dto.freeboard.MemberFreeBoardDto;
//import gudiSpring.user.dto.MemberDto;
//
////미완성 닉네임을 다른dto에서가져와야함
//public class SearchDao {
//	private Connection connection;
//	public SearchDao() {
//		// 기본 생성자 구현
//	}
//
//		// Connection을 매개변수로 받는 생성자
//		public SearchDao(Connection connection) {
//			this.connection = connection;
//		}
//
//	private ResultSet rs;//정보를 담을 수 있는 객체	
//	
//
//	public void setConnection(Connection conn) {
//		this.connection = conn;
//	}
//	public ArrayList<BoardDto> getSearch(String searchField, String searchText){//특정한 리스트를 받아서 반환
//		
//		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
//		// 매핑 처리
//		 String column = "";
//	    switch (searchField) {
//	        case "CONTENT_SUBJECT":
//	            column = "CONTENT_SUBJECT";
//	            break;
//	        case "FREE_BOARD_WRITER":
//	            column = "FREE_BOARD_WRITER";
//	            break;
//	        default:
//	            throw new IllegalArgumentException("Invalid search field");
//	        
//	    }
//		String SQL = "SELECT * FROM Free_Board";
//		 // 검색 조건이 있을 때 쿼리 수정
//	    if (searchField != null && !searchField.trim().isEmpty() && searchText != null && !searchText.trim().isEmpty()) {
//	        // WHERE 조건과 LIKE 연산자 추가
//	        SQL += " WHERE " + column  + " LIKE ? ORDER BY FREE_BOARD_ID DESC";
//	    } else {
//	        // ORDER BY만 있는 경우
//	        SQL += " ORDER BY FREE_BOARD_ID DESC";
//	    }
//
//	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setString(1, "%" + searchText.trim() + "%");
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                BoardDto boardDto = new BoardDto(
//                    rs.getInt("CONTENT_NO"),
//                    rs.getString("CONTENT_SUBJECT"),
//                    rs.getString("CONTENT_TEXT"),
//                    rs.getString("CONTENT_FILE"),
//                    rs.getInt("CONTENT_BOARD_INFO_NO"),
//                    rs.getDate("CONTENT_CRE_DATE"),
//                    rs.getDate("CONTENT_UPDATE_DATE"),
//                    rs.getInt("USER_NO")
//                );
//                list.add(boardDto);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return list;
//    }
//}

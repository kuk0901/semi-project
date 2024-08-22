package gudiSpring.event.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gudiSpring.event.dto.EventDto;

public class EventDao {
	Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// user event detail
	public EventDto selectOneEvent(int param) {
		EventDto eventDto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "";
			sql += "SELECT EVENT_NO, EVENT_NAME";
			sql += " , TO_CHAR(OPEN_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\"') AS FM_OPEN_DATE";
			sql += " , TO_CHAR(CLOSE_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\"') AS FM_CLOSE_DATE";
			sql += " , CASE";
			sql += " WHEN CLOSE_DATE > SYSDATE THEN '이벤트가 진행 중입니다.'";
			sql += " ELSE";
			sql += " CASE U.NICKNAME";
			sql += " WHEN 'EVENT' THEN '미추첨'";
			sql += " WHEN U.NICKNAME THEN U.NICKNAME";
			sql += " ELSE ''";
			sql += " END";
			sql += " END AS NICKNAME";
			sql += " FROM EVENT E, USER_INFO U";
			sql += " WHERE E.USER_NO = U.USER_NO";
			sql += " AND E.EVENT_NO = ?";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, param);
			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				eventDto = new EventDto(
							rs.getInt("EVENT_NO"),
							rs.getString("EVENT_NAME"),
							rs.getString("FM_OPEN_DATE"),
							rs.getString("FM_CLOSE_DATE"),
							rs.getString("NICKNAME")
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료
		
		return eventDto;
	}
	
	// Admin Event 전체 조회
	public List<EventDto> selectEventList(int start, int pageSize) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EventDto> eventList = new ArrayList<>();

		try {
			String sql = "";
			sql += "SELECT E.EVENT_NO, E.EVENT_NAME";
			sql += " , TO_CHAR(E.OPEN_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_OPEN_DATE";
			sql += " , TO_CHAR(E.CLOSE_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CLOSE_DATE";
			sql += " , U.NAME, U.NICKNAME";
			sql += " FROM EVENT E, USER_INFO U";
			sql += " WHERE E.USER_NO = U.USER_NO";

			pstmt = connection.prepareStatement(sql);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();

			int eventNo = 0;
			String eventName = "";
			String openDate = "";
			String closeDate = "";
			String userName = "";
			String userNickname = "";

			EventDto eventDto = null;

			while (rs.next()) {
				eventNo = rs.getInt("EVENT_NO");
				eventName = rs.getString("EVENT_NAME");
				openDate = rs.getString("FM_OPEN_DATE");
				closeDate = rs.getString("FM_CLOSE_DATE");
				userName = rs.getString("NAME");
				userNickname = rs.getString("NICKNAME");

				eventDto = new EventDto(eventNo, eventName, openDate, closeDate, userName, userNickname);

				eventList.add(eventDto);
			}

			return eventList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료

		return eventList;
	}

	// event count
	public int eventTotalCount() {
		int totalCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT COUNT(EVENT_NO)";
			sql += " FROM EVENT";

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

	// Event search
	public List<EventDto> searchEventList(String param, int start, int pageSize) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EventDto> eventList = new ArrayList<>();

		try {
			String sql = "";
			sql += "SELECT E.EVENT_NO, E.EVENT_NAME";
			sql += " , TO_CHAR(E.OPEN_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_OPEN_DATE";
			sql += " , TO_CHAR(E.CLOSE_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_CLOSE_DATE";
			sql += " , U.NAME, U.NICKNAME";
			sql += " FROM EVENT E, USER_INFO U";
			sql += " WHERE E.USER_NO = U.USER_NO";
			sql += " AND EVENT_NAME LIKE UPPER(?)";

			pstmt = connection.prepareStatement(sql);

			int colIndex = 1;

			pstmt.setString(colIndex, "%" + param + "%");

			rs = pstmt.executeQuery();

			EventDto eventDto = null;

			while (rs.next()) {

				eventDto = new EventDto(
							rs.getInt("EVENT_NO"), 
							rs.getString("EVENT_NAME"), 
							rs.getString("FM_OPEN_DATE"),
					    rs.getString("FM_CLOSE_DATE"), 
					    rs.getString("NAME"), 
					    rs.getString("NICKNAME")
				    );

				eventList.add(eventDto);
			}

			return eventList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally 종료

		return eventList;
	}

	// search event count
	public int searchEventTotalCount(String param) {
		int totalCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT COUNT(EVENT_NO)";
			sql += " FROM EVENT";
			sql += " WHERE EVENT_NAME LIKE UPPER(?)";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, param);
			
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
	
	public HashMap<String, String> adminPlaceDetailSelect(int eventNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HashMap<String, String> eventMap = new HashMap<String, String>();
		
		try {
			String sql = "";
			sql += "SELECT E.EVENT_NO, E.EVENT_NAME, TO_CHAR(E.OPEN_DATE, 'YYYY-MM-DD') AS FM_OPEN_DATE";
			sql += " , TO_CHAR(E.CLOSE_DATE, 'YYYY-MM-DD') AS FM_CLOSE_DATE";
			sql += " , U.NAME, U.NICKNAME, U.ID, U.PHONE";
			sql += " FROM EVENT E, USER_INFO U";
			sql += " WHERE E.USER_NO = U.USER_NO";
			sql += " AND E.EVENT_NO = ?";
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, eventNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				eventMap.put("eventNo", Integer.toString(rs.getInt("EVENT_NO")));
				eventMap.put("eventName", rs.getString("EVENT_NAME"));
				eventMap.put("openDate", rs.getString("FM_OPEN_DATE"));
				eventMap.put("closeDate", rs.getString("FM_CLOSE_DATE"));
				eventMap.put("userName", rs.getString("NAME"));
				eventMap.put("userNickname", rs.getString("NICKNAME"));
				eventMap.put("userId", rs.getString("ID"));
				eventMap.put("userPhone", rs.getString("PHONE"));	
			}
			
			return eventMap;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // finally 종료
		}
		
		return eventMap;
	}
	
	// 이벤트 추가
	public int eventInsert(EventDto eventDto) {
		int result = 0;
    PreparedStatement pstmt = null;

		try {
			String sql = "";
			sql += "INSERT INTO EVENT ";
			sql += " (EVENT_NO, EVENT_NAME, OPEN_DATE, CLOSE_DATE)";
			sql += " VALUES (EVENT_NO_SEQ.NEXTVAL, ?, ?, ?)";
			
			int colIndex = 1;
			
			pstmt = connection.prepareStatement(sql);
			
			Date openDate = java.sql.Date.valueOf(eventDto.getOpenDate());
			Date closeDate = java.sql.Date.valueOf(eventDto.getCloseDate());
			
			pstmt.setString(colIndex++, eventDto.getEventName());
			pstmt.setDate(colIndex++, openDate);
			pstmt.setDate(colIndex, closeDate);
			
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

	// 이벤트 업데이트
	public int eventUpdate(EventDto eventDto) {
		int result = 0;
    PreparedStatement pstmt = null;
		
		try {
			String sql = "";
			sql += "UPDATE EVENT";
			sql += " SET EVENT_NAME = ?, OPEN_DATE = ?, CLOSE_DATE = ?";
			sql += " , USER_NO = (SELECT USER_NO";
			sql += " FROM USER_INFO";
			sql += " WHERE NICKNAME = ?)";
			sql += " WHERE EVENT_NO = ?";
			
			int colIndex = 1;
			
			pstmt = connection.prepareStatement(sql);
			
			Date openDate = java.sql.Date.valueOf(eventDto.getOpenDate());
			Date closeDate = java.sql.Date.valueOf(eventDto.getCloseDate());
			
			pstmt.setString(colIndex++, eventDto.getEventName());
			pstmt.setDate(colIndex++, openDate);
			pstmt.setDate(colIndex++, closeDate);
			pstmt.setString(colIndex++, eventDto.getUserNickname());
			pstmt.setInt(colIndex, eventDto.getEventNo());
			
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
	
	// 이벤트 삭제 => 여러 개
	public int deleteEvents(ArrayList<Integer> removeEventNoList) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "";

			for (int i = 0; i < removeEventNoList.size(); i++) {
				sql += "DELETE FROM EVENT";
				sql += " WHERE EVENT_NO = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, removeEventNoList.get(i));
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

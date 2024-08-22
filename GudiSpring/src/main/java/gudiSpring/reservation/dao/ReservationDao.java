package gudiSpring.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.reservation.dto.ReservationDto;

public class ReservationDao {
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public List<ReservationDto> selectReservationList(int start, int pageSize) {
    ArrayList<ReservationDto> reservationList = new ArrayList<ReservationDto>();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        String sql = "";
        sql += "SELECT RESERVATION_NO, PLACE_NAME, FM_RESERVATION_DATE, FM_VISIT_DATE,";
        sql += " RESERVATION_TYPE, NAME, NICKNAME, PHONE, VISITOR_NUM";
        sql += " FROM (SELECT RV.*, ROWNUM rnum";
        sql += " FROM (SELECT R.RESERVATION_NO, P.PLACE_NAME";
        sql += " , TO_CHAR(RESERVATION_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_RESERVATION_DATE";
        sql += " , TO_CHAR(VISIT_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_VISIT_DATE";
        sql += " , CASE";
        sql += " WHEN P.RECO_RESERVATION <> 0 THEN '추천'";
        sql += " ELSE '일반'";
        sql += " END AS RESERVATION_TYPE";
        sql += " , U.NAME, U.NICKNAME, U.PHONE, R.VISITOR_NUM";
        sql += " FROM RESERVATION R, PLACE P, USER_INFO U";
        sql += " WHERE R.PLACE_NO = P.PLACE_NO";
        sql += " AND R.USER_NO = U.USER_NO";
        sql += " ORDER BY RESERVATION_NO) RV";
        sql += " WHERE ROWNUM <= ?)";
        sql += " WHERE rnum >= ? AND rnum < ?";

        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, start + pageSize); // 상한
        pstmt.setInt(2, start);            // 하한
        pstmt.setInt(3, start + pageSize); // 상한

        rs = pstmt.executeQuery();
        
        while(rs.next()) {
            ReservationDto reservationDto = new ReservationDto(
                        rs.getInt("RESERVATION_NO"),
                        rs.getString("FM_RESERVATION_DATE"),
                        rs.getString("FM_VISIT_DATE"),
                        rs.getInt("VISITOR_NUM"),
                        rs.getString("PLACE_NAME"),
                        rs.getString("RESERVATION_TYPE"),
                        rs.getString("NAME"),
                        rs.getString("NICKNAME"),
                        rs.getString("PHONE")
                    );
            
            reservationList.add(reservationDto);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return reservationList;
}
	
	public int reservationTotalCount() {
		int totalCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null; 

		try {
			String sql = "SELECT COUNT(RESERVATION_NO) FROM RESERVATION";
 			
 			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalCount = rs.getInt(1); // 첫 번째 칼럼
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
//admin reservation
	public List<ReservationDto> searchSelectReservationList(String param, int start, int pageSize) {
		ArrayList<ReservationDto> reservationList = new ArrayList<ReservationDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "";
			sql += "SELECT RESERVATION_NO, PLACE_NAME, FM_RESERVATION_DATE, FM_VISIT_DATE,";
			sql += " RESERVATION_TYPE, NAME, NICKNAME, PHONE, VISITOR_NUM";
			sql += " FROM (SELECT RV.*, ROWNUM rnum";
			sql += " FROM (SELECT R.RESERVATION_NO, P.PLACE_NAME";
			sql += " , TO_CHAR(RESERVATION_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_RESERVATION_DATE";
			sql += " , TO_CHAR(VISIT_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일\" HH24:MI:SS') AS FM_VISIT_DATE";
			sql += " , CASE";
			sql += " WHEN P.RECO_RESERVATION <> 0 THEN '추천'";
			sql += " ELSE '일반'";
			sql += " END AS RESERVATION_TYPE";
			sql += " , U.NAME, U.NICKNAME, U.PHONE, R.VISITOR_NUM";
			sql += " FROM RESERVATION R, PLACE P, USER_INFO U";
			sql += " WHERE R.PLACE_NO = P.PLACE_NO";
			sql += " AND R.USER_NO = U.USER_NO";
			sql += " AND (UPPER(P.PLACE_NAME) LIKE UPPER('%'||?||'%'))";
			sql += " ORDER BY RESERVATION_NO) RV";
			sql += " WHERE ROWNUM <= ?)";
      sql += " WHERE rnum >= ? AND rnum < ?";

			int colIndex = 1;

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(colIndex++, param);
			pstmt.setInt(colIndex++, start + pageSize); // 상한
      pstmt.setInt(colIndex++, start);            // 하한
      pstmt.setInt(colIndex, start + pageSize); // 상한

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery();
			
			ReservationDto reservationDto = null;
			
			while(rs.next()) {
				reservationDto = new ReservationDto(
							rs.getInt("RESERVATION_NO"),
							rs.getString("FM_RESERVATION_DATE"),
							rs.getString("FM_VISIT_DATE"),
							rs.getInt("VISITOR_NUM"),
							rs.getString("PLACE_NAME"),
							rs.getString("RESERVATION_TYPE"),
							rs.getString("NAME"),
							rs.getString("NICKNAME"),
							rs.getString("PHONE")
						);
				
				reservationList.add(reservationDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reservationList;
	}
	
	public int searchTotalCount(String placeName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT COUNT(RC.RESERVATION_NO)";
			sql += " FROM RESERVATION RC";
			sql += " JOIN (SELECT R.PLACE_NO";
			sql += " FROM RESERVATION R";
			sql += " JOIN PLACE P ON R.PLACE_NO = P.PLACE_NO";
			sql += " WHERE UPPER(P.PLACE_NAME) LIKE UPPER('%'||?||'%')) RE";
			sql += " ON RC.PLACE_NO = RE.PLACE_NO";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, placeName); // 검색어 설정

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1); // 첫 번째 칼럼의 값을 반환
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
		}

		return 0; // 기본값
	}
}

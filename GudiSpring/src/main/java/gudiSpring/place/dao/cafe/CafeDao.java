package gudiSpring.place.dao.cafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.place.dto.PlaceDto;
import gudiSpring.place.dto.cafe.CafeDto;

public class CafeDao {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// user cafe list
	public List<CafeDto> selectCafeList(int areaNo, int startRow, int endRow) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		sql += "SELECT rnum, PLACE_NAME, PLACE_NO, PLACE_IMG_PATH FROM ( ";
		sql += "   SELECT ROWNUM AS rnum, P.PLACE_NAME, P.PLACE_NO, P.PLACE_IMG_PATH ";
		sql += "   FROM PLACE P, AREA A ";
		sql += "   WHERE P.AREA_NO = A.AREA_NO ";
		sql += "   AND P.CATEGORY = '카페' ";
		sql += "   AND A.AREA_NO = ? ";
		sql += "   ORDER BY P.PLACE_NO ";
		sql += ") ";
		sql += "WHERE rnum BETWEEN ? AND ?";

		ArrayList<CafeDto> cafeList = new ArrayList<>();

		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, areaNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			pstmt.setInt(1, areaNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String placeName = rs.getString("PLACE_NAME");
				int placeNo = rs.getInt("PLACE_NO");
				String plImgPath = rs.getString("PLACE_IMG_PATH");
				CafeDto cafeDto = new CafeDto(placeName, placeNo, plImgPath);

				cafeList.add(cafeDto);
			}
			return cafeList;
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
		return cafeList;
	}

	// user 카페 총 개수 조회
	public int getTotalCount(int areaNo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try  {
			String sql = "";
			sql += "SELECT COUNT(PLACE_NO)";
			sql += " FROM PLACE P, AREA A";
			sql += " WHERE P.AREA_NO = A.AREA_NO";
			sql += " AND P.CATEGORY = '카페' AND A.AREA_NO = ?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, areaNo);
			rs = pstmt.executeQuery();
				
			if (rs.next()) {
					result = rs.getInt(1);
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
		
		return result;
	}

	// 카페 정보
	public CafeDto selectCafeInfomation(int placeNo) throws Exception {
		CafeDto cafeDto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT PLACE_NO, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE, PLACE_IMG_PATH, GEN_RESERVATION, RECO_RESERVATION";
			sql += " FROM PLACE";
			sql += " WHERE CATEGORY = '카페'";
			sql += " AND PLACE_NO = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, placeNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String placeName = rs.getString("PLACE_NAME");
				String plAddress = rs.getString("PL_ADDRESS");
				String plPhone = rs.getString("PL_PHONE");
				String plWebsite = rs.getString("PL_WEBSITE");
				String plImgPath = rs.getString("PLACE_IMG_PATH");
				int genReservation = rs.getInt("GEN_RESERVATION");

				cafeDto = new CafeDto(placeName, plAddress, plPhone, plWebsite, plImgPath, genReservation);
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
		return cafeDto;
	}

	// GEN_RESERVATION 값을 증가시키는 메서드
	public void incrementReservation(int placeNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "";
			sql += "UPDATE PLACE SET GEN_RESERVATION = GEN_RESERVATION + 1 WHERE PLACE_NO = ?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			pstmt.executeUpdate();
			
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

	}

	// 랜덤 카페 추천
	public CafeDto getRandomCafe() throws SQLException {
		CafeDto cafeDto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "";
			sql += "SELECT PLACE_NO, PLACE_NAME, CATEGORY, PL_ADDRESS, PL_PHONE, PL_WEBSITE, PLACE_IMG_PATH, RECO_RESERVATION";
			sql += " FROM( ";
			sql += " 	SELECT PLACE_NO, PLACE_NAME, CATEGORY, PL_ADDRESS, PL_PHONE, PL_WEBSITE, PLACE_IMG_PATH, RECO_RESERVATION";
			sql += " 	FROM PLACE ";
			sql += " 	ORDER BY DBMS_RANDOM.RANDOM ";
			sql += ")";
			sql += " WHERE ROWNUM = 1";
			sql += " AND CATEGORY = '카페'";
			
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int placeNo = rs.getInt("PLACE_NO");
				String placeName = rs.getString("PLACE_NAME");
				String category = rs.getString("CATEGORY");
				String plAddress = rs.getString("PL_ADDRESS");
				String plPhone = rs.getString("PL_PHONE");
				String plWebsite = rs.getString("PL_WEBSITE");
				String plImgPath = rs.getString("PLACE_IMG_PATH");
				int recoReservation = rs.getInt("RECO_RESERVATION");

				cafeDto = new CafeDto(placeNo, placeName, category, plAddress, plPhone, plWebsite, plImgPath, recoReservation);
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
		
		return cafeDto;
	}

	// RECO_RESERVATION 값을 증가시키는 메서드
	public void incrementRecoReservation(int placeNo) throws SQLException {
		String sql = "UPDATE PLACE SET RECO_RESERVATION = RECO_RESERVATION + 1 WHERE PLACE_NO = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, placeNo);
			pstmt.executeUpdate();
		}
	}

	// admin
	public List<CafeDto> selectCafeList(int start, int pageSize) {
		ArrayList<CafeDto> cafeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT PLACE_NO, CATEGORY, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE";
			sql += " , GEN_RESERVATION, RECO_RESERVATION";
			sql += " FROM (SELECT p.*, ROWNUM rnum";
			sql += " FROM (SELECT PLACE_NO, CATEGORY, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE, GEN_RESERVATION, RECO_RESERVATION";
			sql += " FROM PLACE";
			sql += " WHERE CATEGORY = '카페'";
			sql += " ORDER BY PLACE_NO) p";
			sql += " WHERE ROWNUM <= ?)";
			sql += " WHERE rnum > ?";
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, start + pageSize); // 시작 인덱스 + 페이지 크기
      pstmt.setInt(2, start); // 시작 인덱스

			rs = pstmt.executeQuery();

			int placeNo = 0;
			String category = "";
			String placeName = "";
			String plAddress = "";
			String plPhone = "";
			String plWebsite = "";
			int genReservation = 0;
			int recoReservation = 0;

			CafeDto cafeDto = null;

			while (rs.next()) {
				placeNo = rs.getInt("PLACE_NO");
				category = rs.getString("CATEGORY");
				placeName = rs.getString("PLACE_NAME");
				plAddress = rs.getString("PL_ADDRESS");
				plPhone = rs.getString("PL_PHONE");
				plWebsite = rs.getString("PL_WEBSITE");
				genReservation = rs.getInt("GEN_RESERVATION");
				recoReservation = rs.getInt("RECO_RESERVATION");

				cafeDto = new CafeDto(placeNo, category, placeName, plAddress, plPhone, plWebsite, genReservation,
				    recoReservation);

				cafeList.add(cafeDto);
			}

			return cafeList;

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

	public List<PlaceDto> searchCafeList(String placeName, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	// cafe count
	public int cafeTotalCount() {
		int totalCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";
			sql += "SELECT COUNT(PLACE_NO)";
			sql += " FROM PLACE";
			sql += " WHERE CATEGORY = '카페'";

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

}

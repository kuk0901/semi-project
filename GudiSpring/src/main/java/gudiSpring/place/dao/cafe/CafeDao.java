package gudiSpring.place.dao.cafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.place.dto.cafe.CafeDto;

public class CafeDao {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// user
	public List<CafeDto> selectCafeList(int areaNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		sql += "SELECT PLACE_NAME, PLACE_NO";
		sql += " FROM PLACE P, AREA A";
		sql += " WHERE P.AREA_NO = A.AREA_NO";
		sql += " AND P.CATEGORY = '카페'";
		sql += " AND A.AREA_NO = ?";

		ArrayList<CafeDto> cafeList = new ArrayList<>();

		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, areaNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String placeName = rs.getString("PLACE_NAME");
				int placeNo = rs.getInt("PLACE_NO");
				CafeDto cafeDto = new CafeDto(placeName, placeNo);

				cafeList.add(cafeDto);
			}
			return cafeList;
		} catch (Exception e) {
			// TODO: handle exception
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return cafeList;
	}

	// cafe detail
	public CafeDto selectCafeInfomation(int placeNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		sql += "SELECT PLACE_NO, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE";
		sql += " FROM PLACE";
		sql += " WHERE CATEGORY = '카페'";
		sql += " AND PLACE_NO = ?";

		CafeDto cafeDto = null;
		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, placeNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String placeName = rs.getString("PLACE_NAME");
				String plAddress = rs.getString("PL_ADDRESS");
				String plPhone = rs.getString("PL_PHONE");
				String plWebsite = rs.getString("PL_WEBSITE");

				cafeDto = new CafeDto(placeName, plAddress, plPhone, plWebsite);
			}

		} catch (Exception e) {
			// TODO: handle exception
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return cafeDto;
	}

	// admin
	public List<CafeDto> selectCafeList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "";
			sql += "SELECT PLACE_NO, CATEGORY, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE, GEN_RESERVATION, RECO_RESERVATION";
			sql += " FROM PLACE";
			sql += " WHERE CATEGORY = '카페'";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int placeNo = 0;
			String category = "";
			String placeName = "";
			String plAddress = "";
			String plPhone = "";
			String plWebsite = "";
			int genReservation = 0;
			int recoReservation = 0;

			ArrayList<CafeDto> cafeList = new ArrayList<>();

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

}

package gudiSpring.place.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.place.dto.PlaceDto;

public class PlaceDao {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// 사용자 ui용 place list
	public List<PlaceDto> selectPlaceList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "";
			sql += "SELECT PLACE_NO, CATEGORY, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE, GEN_RESERVATION, RECO_RESERVATION";
			sql += " FROM PLACE";

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

			ArrayList<PlaceDto> placeList = new ArrayList<>();

			PlaceDto placeDto = null;

			while (rs.next()) {
				placeNo = rs.getInt("PLACE_NO");
				category = rs.getString("CATEGORY");
				placeName = rs.getString("PLACE_NAME");
				plAddress = rs.getString("PL_ADDRESS");
				plPhone = rs.getString("PL_PHONE");
				plWebsite = rs.getString("PL_WEBSITE");
				genReservation = rs.getInt("GEN_RESERVATION");
				recoReservation = rs.getInt("RECO_RESERVATION");

				placeDto = new PlaceDto(placeNo, category, placeName, plAddress, plPhone, plWebsite, genReservation,
				    recoReservation);

				placeList.add(placeDto);
			}

			return placeList;

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

	// admin place detail info select
	public PlaceDto adminPlaceDetailSelect(int placeNo) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PlaceDto placeDto = null;

		try {
			String sql = "";

			sql += "SELECT PLACE_NO, CATEGORY, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE, GEN_RESERVATION, RECO_RESERVATION";
			sql += " FROM PLACE";
			sql += " WHERE PLACE_NO = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, placeNo);

			rs = pstmt.executeQuery();

			String category = "";
			String placeName = "";
			String plAddress = "";
			String plPhone = "";
			String plWebsite = "";
			int genReservation = 0;
			int recoReservation = 0;

			while (rs.next()) {

				placeNo = rs.getInt("PLACE_NO");
				category = rs.getString("CATEGORY");
				placeName = rs.getString("PLACE_NAME");
				plAddress = rs.getString("PL_ADDRESS");
				plPhone = rs.getString("PL_PHONE");
				plWebsite = rs.getString("PL_WEBSITE");
				genReservation = rs.getInt("GEN_RESERVATION");
				recoReservation = rs.getInt("RECO_RESERVATION");

				placeDto = new PlaceDto(placeNo, category, placeName, plAddress, plPhone, plWebsite, genReservation,
				    recoReservation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return placeDto;

	}

	// admin placeList select
	public List<PlaceDto> searchPlaceList(String placeName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PlaceDto placeDto = null;
		ArrayList<PlaceDto> placeList = new ArrayList<>();

		try {

			String sql = "";

			sql += "SELECT PLACE_NO, CATEGORY, PLACE_NAME, PL_ADDRESS, PL_PHONE, GEN_RESERVATION, RECO_RESERVATION";
			sql += " FROM PLACE";
			sql += " WHERE PLACE_NAME = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, placeName);

			rs = pstmt.executeQuery();

			int placeNo = 0;
			String category = "";
			String plAddress = "";
			String plPhone = "";
			int genReservation = 0;
			int recoReservation = 0;

			while (rs.next()) {

				placeNo = rs.getInt("PLACE_NO");
				category = rs.getString("CATEGORY");
				placeName = rs.getString("PLACE_NAME");
				plAddress = rs.getString("PL_ADDRESS");
				plPhone = rs.getString("PL_PHONE");
				genReservation = rs.getInt("GEN_RESERVATION");
				recoReservation = rs.getInt("RECO_RESERVATION");

				placeDto = new PlaceDto(placeNo, category, placeName, plAddress, plPhone, genReservation, recoReservation);

				placeList.add(placeDto);
			}

			return placeList;

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
				e.printStackTrace();
			}
		} // finally 종료

		return placeList;
	}

	// admin insert place
	public int placeInsert(PlaceDto placeDto) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {

			String sql = "";
			sql += "INSERT INTO PLACE";
			sql += " (PLACE_NO, CATEGORY, AREA_NO, PLACE_NAME, PL_ADDRESS, PL_PHONE, PL_WEBSITE)";
			sql += " VALUES (PLACE_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";

			int colIndex = 1;

			String category = placeDto.getCategory();
			int areaNo = placeDto.getAreaNo();
			String placeName = placeDto.getPlaceName();
			String plAddress = placeDto.getPlAddress();
			String plPhone = placeDto.getPlPhone();
			String plWebsite = placeDto.getPlWebsite();

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(colIndex++, category);
			pstmt.setInt(colIndex++, areaNo);
			pstmt.setString(colIndex++, placeName);
			pstmt.setString(colIndex++, plAddress);
			pstmt.setString(colIndex++, plPhone);
			pstmt.setString(colIndex, plWebsite);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
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

	// admin place update
	public int placeUpdate(PlaceDto placeDto) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {

			String sql = "";
			sql += "UPDATE PLACE";
			sql += " SET PLACE_NAME = ?, PL_ADDRESS = ?, PL_PHONE = ?";
			sql += " , PL_WEBSITE = ?";
			sql += " WHERE PLACE_NO = ?";

			int colIndex = 1;

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(colIndex++, placeDto.getPlaceName());
			pstmt.setString(colIndex++, placeDto.getPlAddress());
			pstmt.setString(colIndex++, placeDto.getPlPhone());
			pstmt.setString(colIndex++, placeDto.getPlWebsite());
			pstmt.setInt(colIndex, placeDto.getPlaceNo());

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

	// admin placeList delete
	public int placeDelete(ArrayList<Integer> removePlaceNoList) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "";

		try {

			for (int i = 0; i < removePlaceNoList.size(); i++) {
				sql += "DELETE FROM PLACE";
				sql += " WHERE PLACE_NO = ?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, removePlaceNoList.get(i));
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

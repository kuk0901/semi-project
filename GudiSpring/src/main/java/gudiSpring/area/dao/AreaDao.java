package gudiSpring.area.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.area.dto.AreaDto;

public class AreaDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public List<AreaDto> selectList() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";

			sql += "SELECT AREA_NO, AREA_NAME";
			sql += " FROM AREA";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int areaNo = 1;
			String areaName = "";

			ArrayList<AreaDto> areaList = new ArrayList<AreaDto>();

			AreaDto AreaDto = null;

			while (rs.next()) {
				areaNo = rs.getInt("AREA_NO");
				areaName = rs.getString("AREA_NAME");

				AreaDto = new AreaDto(areaNo, areaName);

				areaList.add(AreaDto);
			}

			return areaList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public List<AreaDto> reservationArea() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";

			sql += "SELECT AREA_NO";
			sql += " FROM AREA";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int areaNo = 1;			

			ArrayList<AreaDto> reservationArea = new ArrayList<AreaDto>();

			AreaDto AreaDto = null;
			while (rs.next()) {
				areaNo = rs.getInt("AREA_NO");
				

				AreaDto = new AreaDto(areaNo);

				reservationArea.add(AreaDto);
			}
			return reservationArea;
		} catch (Exception e) {
			
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
	}
}
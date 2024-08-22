package gudiSpring.reservation.dto;

public class ReservationDto {
	int reservationNo;
	int areaNo;
	int placeNo;
	String reservationDate;
	String visitDate;
	int userNo;
	int visitorNum;
	
	// 관리자 전용
	String placeName;
	String reservationType;
	String name;
	String nickname;
	String phone;
	
	public ReservationDto() {
		super();
	}
	
	public ReservationDto(int reservationNo, String reservationDate, String visitDate, int visitorNum, String placeName,
	    String reservationType, String name, String nickname, String phone) {
		super();
		this.reservationNo = reservationNo;
		this.reservationDate = reservationDate;
		this.visitDate = visitDate;
		this.visitorNum = visitorNum;
		this.placeName = placeName;
		this.reservationType = reservationType;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
	}

	public ReservationDto(int reservationNo, int areaNo, int placeNo, String reservationDate, String visitDate,
	    int userNo, int visitorNum, String placeName, String reservationType, String name, String nickname,
	    String phone) {
		super();
		this.reservationNo = reservationNo;
		this.areaNo = areaNo;
		this.placeNo = placeNo;
		this.reservationDate = reservationDate;
		this.visitDate = visitDate;
		this.userNo = userNo;
		this.visitorNum = visitorNum;
		this.placeName = placeName;
		this.reservationType = reservationType;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public int getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getVisitorNum() {
		return visitorNum;
	}

	public void setVisitorNum(int visitorNum) {
		this.visitorNum = visitorNum;
	}

	public String getReservationType() {
		return reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "ReservationDto [reservationNo=" + reservationNo + ", areaNo=" + areaNo + ", placeNo=" + placeNo
		    + ", reservationDate=" + reservationDate + ", visitDate=" + visitDate + ", userNo=" + userNo + ", visitorNum="
		    + visitorNum + ", reservationType=" + reservationType + ", name=" + name + ", nickname=" + nickname + ", phone="
		    + phone + "]";
	}
	
}

package gudiSpring.place.dto;

import gudiSpring.place.interfaces.Place;

public class PlaceDto implements Place {

	private int placeNo;
	private String category;
	private int areaNo; 
	private String placeName;
	private String plAddress;
	private String plPhone;
	private String plWebsite;
	private int genReservation;
	private int recoReservation;
	private String placeImgPath;
	
	// admin 전용
	private String areaName;
	
	public PlaceDto() {
		super();
	}
	
	public PlaceDto(int placeNo, String category, String areaName) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.areaName = areaName;
	}

	public PlaceDto(int placeNo, String placeName, String plAddress, String plPhone, String plWebsite,
	    String placeImgPath) {
		super();
		this.placeNo = placeNo;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.placeImgPath = placeImgPath;
	}
	
	public PlaceDto(String category, int areaNo, String placeName, String plAddress, String plPhone, String plWebsite, String placeImgPath) {
		super();
		this.category = category;
		this.areaNo = areaNo;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.placeImgPath = placeImgPath;
	}

	public PlaceDto(int placeNo, String category, String placeName, String plAddress, String plPhone, int genReservation,
	    int recoReservation) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.genReservation = genReservation;
		this.recoReservation = recoReservation;
	}

	public PlaceDto(int placeNo, String category, String placeName, String plAddress, String plPhone, String plWebsite,
	    int genReservation, int recoReservation) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.genReservation = genReservation;
		this.recoReservation = recoReservation;
	}

	public PlaceDto(int placeNo, String category, String placeName, String plAddress, String plPhone, String plWebsite,
	    int genReservation, int recoReservation, String placeImgPath, String areaName) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.genReservation = genReservation;
		this.recoReservation = recoReservation;
		this.placeImgPath = placeImgPath;
		this.areaName = areaName;
	}

	public PlaceDto(int placeNo, String category, int areaNo, String placeName, String plAddress, String plPhone,
	    String plWebsite, int genReservation, int recoReservation, String placeImgPath, String areaName) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.areaNo = areaNo;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.genReservation = genReservation;
		this.recoReservation = recoReservation;
		this.placeImgPath = placeImgPath;
		this.areaName = areaName;
	}
	
	// user 전용
	
	public PlaceDto(int placeNo) {
		super();
		this.placeNo = placeNo;
	}
	
	public PlaceDto(int placeNo, int areaNo, String placeName, String category, String placeImgPath) {
		super();
		this.placeNo = placeNo;
		this.areaNo = areaNo;
		this.placeName = placeName;
		this.category = category;
		this.placeImgPath = placeImgPath;
	}


	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlAddress() {
		return plAddress;
	}

	public void setPlAddress(String plAddress) {
		this.plAddress = plAddress;
	}

	public String getPlPhone() {
		return plPhone;
	}

	public void setPlPhone(String plPhone) {
		this.plPhone = plPhone;
	}

	public String getPlWebsite() {
		return plWebsite;
	}

	public void setPlWebsite(String plWebsite) {
		this.plWebsite = plWebsite;
	}	
	
	public int getGenReservation() {
		return genReservation;
	}

	public void setGenReservation(int genReservation) {
		this.genReservation = genReservation;
	}

	public int getRecoReservation() {
		return recoReservation;
	}

	public void setRecoReservation(int recoReservation) {
		this.recoReservation = recoReservation;
	}

	public String getPlaceImgPath() {
		return placeImgPath;
	}

	public void setPlaceImgPath(String placeImgPath) {
		this.placeImgPath = placeImgPath;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	

	@Override
	public String toString() {
		return "PlaceDto [placeNo=" + placeNo + ", category=" + category + ", areaNo=" + areaNo + ", placeName=" + placeName
		    + ", plAddress=" + plAddress + ", plPhone=" + plPhone + ", plWebsite=" + plWebsite + ", genReservation="
		    + genReservation + ", recoReservation=" + recoReservation + ", placeImgPath=" + placeImgPath + ", areaName="
		    + areaName + "]";
	}
	
}

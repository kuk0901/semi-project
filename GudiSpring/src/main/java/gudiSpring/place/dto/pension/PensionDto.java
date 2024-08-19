package gudiSpring.place.dto.pension;

import gudiSpring.place.dto.PlaceDto;

public class PensionDto extends PlaceDto {

	private int placeNo;
	private String category;
	private int areaNo;
	private String placeName;
	private String plAddress;
	private String plPhone;
	private String plWebsite;
	private String plImgPath;
	private int genReservation;
	private int recoReservation;

	public PensionDto() {
		super();
	}

	public PensionDto(String placeName, int placeNo, String plImgPath) {
		this.placeName = placeName;
		this.placeNo = placeNo;
		this.plImgPath = plImgPath;
	}
	
	public PensionDto(String placeName, String plAddress, String plPhone, String plWebsite) {
		super();
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
	}

	public PensionDto(int placeNo, String placeName, String plAddress, String category, String plPhone, String plWebsite,
			String plImgPath, int recoReservation) {
		super();
		this.placeNo = placeNo;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.category = category;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.plImgPath = plImgPath;
		this.recoReservation = recoReservation;
	}
	
	public PensionDto(String placeName, String plAddress, String plPhone, String plWebsite, String plImgPath, int genReservation) {
		super();
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.plImgPath = plImgPath;
		this.genReservation = genReservation;
	}	
	
	public PensionDto(String category, String placeName, String plAddress, String plPhone, String plWebsite,
	    int genReservation, int recoReservation) {
		super();
		this.category = category;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.genReservation = genReservation;
		this.recoReservation = recoReservation;
	}

	public PensionDto(int placeNo, String category, String placeName, String plAddress, String plPhone, String plWebsite,
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

	public PensionDto(int placeNo, String category, int areaNo, String placeName, String plAddress, String plPhone,
	    String plWebsite, String plImgPath, int genReservation, int recoReservation) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.areaNo = areaNo;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.plImgPath = plImgPath;
		this.genReservation = genReservation;
		this.recoReservation = recoReservation;
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

	public String getPlImgPath() {
		return plImgPath;
	}

	public void setPlImgPath(String plImgPath) {
		this.plImgPath = plImgPath;
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

	@Override
	public String toString() {
		return "PensionDto [placeNo=" + placeNo + ", category=" + category + ", areaNo=" + areaNo + ", placeName="
		    + placeName + ", plAddress=" + plAddress + ", plPhone=" + plPhone + ", plWebsite=" + plWebsite + ", plImgPath="
		    + plImgPath + ", genReservation=" + genReservation + ", recoReservation=" + recoReservation + "]";
	}

}

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
	private int genRerervation;
	private int recoRerervation;
	
	public PlaceDto() {
		super();
	}
	
	public PlaceDto(String category, int areaNo, String placeName, String plAddress, String plPhone, String plWebsite) {
		super();
		this.category = category;
		this.areaNo = areaNo;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
	}
	
	public PlaceDto(int placeNo, String category, String placeName, String plAddress, String plPhone, int genRerervation,
	    int recoRerervation) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.genRerervation = genRerervation;
		this.recoRerervation = recoRerervation;
	}

	public PlaceDto(int placeNo, String category, String placeName, String plAddress, String plPhone, String plWebsite,
	    int genRerervation, int recoRerervation) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.genRerervation = genRerervation;
		this.recoRerervation = recoRerervation;
	}

	public PlaceDto(int placeNo, String category, int areaNo, String placeName, String plAddress, String plPhone,
	    String plWebsite, int genRerervation, int recoRerervation) {
		super();
		this.placeNo = placeNo;
		this.category = category;
		this.areaNo = areaNo;
		this.placeName = placeName;
		this.plAddress = plAddress;
		this.plPhone = plPhone;
		this.plWebsite = plWebsite;
		this.genRerervation = genRerervation;
		this.recoRerervation = recoRerervation;
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

	public int getGenRerervation() {
		return genRerervation;
	}

	public void setGenRerervation(int genRerervation) {
		this.genRerervation = genRerervation;
	}

	public int getRecoRerervation() {
		return recoRerervation;
	}

	public void setRecoRerervation(int recoRerervation) {
		this.recoRerervation = recoRerervation;
	}

	@Override
	public String toString() {
		return "PlaceDto [placeNo=" + placeNo + ", category=" + category + ", areaNo=" + areaNo + ", placeName=" + placeName
		    + ", plAddress=" + plAddress + ", plPhone=" + plPhone + ", phWebsite=" + plWebsite + ", genRerervation="
		    + genRerervation + ", recoRerervation=" + recoRerervation + "]";
	}
	
}

package gudiSpring.area.dto;

public class AreaDto {

	private int areaNo;
	private String areaName;

	public AreaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AreaDto(int areaNo) {
		super();
		this.areaNo = areaNo;
	}

	public AreaDto(int areaNo, String areaName) {
		super();
		this.areaNo = areaNo;
		this.areaName = areaName;
	}

	public int getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "AreaDto [areaNo=" + areaNo + ", areaName=" + areaName + "]";
	}

}

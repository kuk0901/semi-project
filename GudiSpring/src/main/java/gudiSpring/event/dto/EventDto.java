package gudiSpring.event.dto;

public class EventDto {

	// 실제 DB에 저장된 값
	private int eventNo;
	private String eventName;
	private String openDate;
	private String closeDate;
	private int userNo;
	
	// join용 인스턴스 변수
	private String userName;
	private String userNickname;
	
	public EventDto() {
		super();
	}
	
	public EventDto(String eventName, String openDate, String closeDate) {
		super();
		this.eventName = eventName;
		this.openDate = openDate;
		this.closeDate = closeDate;
	}
	
	public EventDto(int eventNo, String eventName, String openDate, String closeDate, String userNickname) {
		super();
		this.eventNo = eventNo;
		this.eventName = eventName;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.userNickname = userNickname;
	}

	public EventDto(int eventNo, String eventName, String openDate, String closeDate, String userName,
	    String userNickname) {
		super();
		this.eventNo = eventNo;
		this.eventName = eventName;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.userName = userName;
		this.userNickname = userNickname;
	}

	public EventDto(int eventNo, String eventName, String openDate, String closeDate, int userNo, String userName, String userNickname) {
		super();
		this.eventNo = eventNo;
		this.eventName = eventName;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.userNo = userNo;
		this.userName = userName;
		this.userNickname = userNickname;
	}

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Override
	public String toString() {
		return "EventDto [eventNo=" + eventNo + ", eventName=" + eventName + ", openDate=" + openDate + ", closeDate="
		    + closeDate + ", userNo=" + userNo + ", userName=" + userName + ", userNickname=" + userNickname + "]";
	}
	
}

package gudiSpring.board.dto.freeboard;

public class BoardInfoDTO {
	private int boardInfoNo;
    private String boardInfoName;
	public int getBoardInfoNo() {
		return boardInfoNo;
	}
	public void setBoardInfoNo(int boardInfoNo) {
		this.boardInfoNo = boardInfoNo;
	}
	public String getBoardInfoName() {
		return boardInfoName;
	}
	public void setBoardInfoName(String boardInfoName) {
		this.boardInfoName = boardInfoName;
	}
	@Override
	public String toString() {
		return "BoardInfoDTO [boardInfoNo=" + boardInfoNo + ", boardInfoName=" + boardInfoName + "]";
	}
	
}

// Emily Xie
// 251035713

public class Layout {
	public String boardLayout;
	public int score;
	
	public Layout(String boardLayout, int score) {
		this.boardLayout = boardLayout;
		this.score = score;
	}
	
	public String getBoardLayout() {
		return boardLayout;
	}
	
	public int getScore() {
		return score;
	}
	
	public static void main(String[] args)
    {
		Layout newObj = new Layout("chchch", 3);
		System.out.println(newObj.getBoardLayout());
    }
	
}

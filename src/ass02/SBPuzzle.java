package ass02;

public class SBPuzzle {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board(4);
		System.out.println(b.arrayToString(b.shortestPath(15)));
	}
}

package ass02;

public class SBPuzzle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board(3);
		b.initialiseBoard(8);
		b.shortestPath(8);
		b.printBoardToConsole();
		//int i = b.getNode(8).indexOfUp();
		//
		//System.out.print(i);
	}

}

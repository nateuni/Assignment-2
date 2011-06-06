package ass02;

public abstract class Node implements Comparable<Node> {
	
	// the values needed for the A*
	static int n;
	int g;
	int h;
	int f;
	int location;
	Node parent;
	
	public void setN(int nVal){
		n = nVal;
	}
	
	public int getN(){
		return n;
	}
	
	public abstract int indexOfLeft();
	public abstract int indexOfRight();
	public abstract int indexOfUp();
	public abstract int indexOfDown();
	public abstract void setIndexesToOtherNodes();
	
	/*
	 * Returns the conceptual row value
	 */
	public int row(){
		return (this.location - this.col()) / n;
	}
	
	/*
	 * Returns the conceptual col value
	 */
	public int col(){
		return this.location % n;
	}
	
}

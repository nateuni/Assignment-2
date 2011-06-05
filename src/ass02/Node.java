package ass02;

import java.util.Comparator;

public abstract class Node implements Comparator<Node> {
	
	static int n;
	int g;
	int h;
	int f;
	int location;
	Node parent;
	//boolean isHole;
	
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
	
	public int row(){
		return (this.location - this.col()) / n;
	}
	
	public int col(){
		return this.location % n;
	}
}

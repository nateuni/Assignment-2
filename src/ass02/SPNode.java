package ass02;


public class SPNode extends Node {
	int up;
	int down;
	int left;
	int right;
	
	public SPNode(int dimensions){
		n = dimensions;
	}	
	
	/*
	 * Finds the index of the left as if it was an actual matrix
	 */
	@Override
	public int indexOfLeft(){
		if(this.location % n == 0)
			return -1;
		return this.location - 1;
	}
	
	/*
	 * Finds the index of the right as if it was an actual matrix
	 */
	@Override
	public int indexOfRight(){
		if((this.location + 1) % n == 0)
			return -1;
		return this.location + 1;
	}
	
	/*
	 * Finds the index of the above as if it was an actual matrix
	 */
	@Override
	public int indexOfUp(){
		if(this.location - n < 0)
			return -1;
		return this.location - n;
	}
	
	/*
	 * Finds the index of the below as if it was an actual matrix
	 */
	@Override
	public int indexOfDown(){
		if(this.location + n >= (n*n))
			return -1;
		return this.location + n;
	}

	/*
	 * Sets this node to know what indexes are around in 4 directions.
	 */
	@Override
	public void setIndexesToOtherNodes() {
		this.down = indexOfDown();
		this.up = indexOfUp();
		this.left = indexOfLeft();
		this.right = indexOfRight();
		
	}

	@Override
	public String toString(){
		return "\n#:"+this.location+" up:"+this.up+" down:"+this.down+" left:"+this.left+" right:"+this.right+" f:"+this.f+" g:"+this.g+" h:"+this.h;
	}

	/*
	 * Used for evaluation of PriorityQueue
	 */
	@Override
	public int compareTo(Node o1) {
		return this.f - o1.f;
	}
}

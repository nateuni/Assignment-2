package ass02;

public class SPNode extends Node {
	int up;
	int down;
	int left;
	int right;
	
	public SPNode(int dimensions){
		n = dimensions;
	}

	@Override
	public int compare(Node n1, Node n2) {
		return n1.f - n2.f;
	}	
	
	@Override
	public int indexOfLeft(){
		if(this.location % n == 0)
			return -1;
		return this.location - 1;
	}
	@Override
	public int indexOfRight(){
		if((this.location + 1) % n == 0)
			return -1;
		return this.location + 1;
	}
	@Override
	public int indexOfUp(){
		if(this.location - n < 0)
			return -1;
		return this.location - n;
	}
	@Override
	public int indexOfDown(){
		if(this.location + n >= (n*n))
			return -1;
		return this.location + n;
	}

	@Override
	public void setIndexesToOtherNodes() {
		this.down = indexOfDown();
		this.up = indexOfUp();
		this.left = indexOfLeft();
		this.right = indexOfRight();
		
	}

	@Override
	public String toString(){
		return "#:"+this.location+" up:"+this.up+" down:"+this.down+" left:"+this.left+" right:"+this.right;
	}
}

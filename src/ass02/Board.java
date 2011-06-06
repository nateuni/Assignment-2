package ass02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Board implements SlidingBlock {
	
	LinkedList<Integer> path = new LinkedList<Integer>();
	int n;
	int[] board;
	Node[] node;
	int startPosition;

	public Board(int n){
		this.n = n;
		board = new int[n*n];
	}

	@Override
	public int[] solve(int[] start, int[] goal, int maxMoves) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] shortestPath(int startPosition) {
		this.startPosition = startPosition;
		initialiseSPNodes(startPosition);
		return this.aStar(node[startPosition], node[0], Integer.MAX_VALUE);
	}

	@Override
	public void addWall(int positionI, int positionJ) {
		// TODO Auto-generated method stub
		
	}
	
	// Initalise a node represention of the input array 
	public void initialiseSPNodes(int startPos){
		node = new SPNode[n*n];
		for(int i = 0; i < this.node.length; i++){
			this.node[i] = new SPNode(n);
			if(i == startPos){
				this.node[i].f = 0;
				this.node[i].g = 0;
				this.node[i].h = 0;
				this.node[i].location = i;
			} else{
				this.node[i].g = 0;
				this.node[i].h = 0;
				this.node[i].f = Integer.MAX_VALUE;
				this.node[i].location = i;
			}
		}
		
		for(int i = 0; i < this.node.length; i++){
			node[i].setIndexesToOtherNodes();
		}
		node[0].setN(n);
	}
	
	// prints the board as a matrix
	public void printBoardToConsole(){
		for(int i = 0; i < this.node.length; i++){
			System.out.print(this.node[i].location+"  ");
			if(this.node[i].location < 10) System.out.print(" ");
			if((i+1) % n == 0){
				System.out.print("\n");
			}
		}
	}
	

	/*
	 * Return the node located above, if there is one
	 */
	public Node up(Node node){
		if(node.indexOfUp() < 0) return null;
		return this.node[node.indexOfUp()];
	}
	
	/*
	 * Return the node located below, if there is one
	 */
	public Node down(Node node){
		if(node.indexOfDown() < 0) return null;
		return this.node[node.indexOfDown()];
	}
	
	/*
	 * Return the node located on the left, if there is one
	 */
	public Node left(Node node){
		if(node.indexOfLeft() < 0) return null;
		return this.node[node.indexOfLeft()];
	}
	
	/*
	 * Return the node located on the right, if there is one
	 */
	public Node right(Node node){
		if(node.indexOfRight() < 0) return null;
		return this.node[node.indexOfRight()];
	}
	
	/*
	 * Return the node from the stated position
	 */
	public Node getNode(int pos){
		return node[pos];
	}
	
	/*
	 * For the shortest path algo
	 * 
	 * Return true if the node is in the first row
	 */
	public boolean reachedGoal(Node node){
		return node.location < n;
	}
	
	/*
	 * Add directly attached nodes to a list for processing in A*
	 */
	public ArrayList<Node> getDirectlyAttachedNodes(Node n){
		ArrayList<Node> list = new ArrayList<Node>();
		if(this.up(n) != null) list.add(this.up(n));
		if(this.down(n) != null) list.add(this.down(n));
		if(this.left(n) != null) list.add(this.left(n));
		if(this.right(n) != null) list.add(this.right(n));
		return list;
	}
	
	/* 
	 * A* Algo from psuedo code from wikipedia
	 * http://en.wikipedia.org/wiki/A*_search_algorithm
	 */
	private int[] aStar(Node startNode, Node goalNode, int maxLength) {
		ArrayList<Node> closedSet = new ArrayList<Node>();
		PriorityQueue<Node> openSet = new PriorityQueue<Node>();
		
		// When handling SPNodes
		if(goalNode instanceof SPNode){
			goalNode = node[startNode.col()];
		}

		int tentativeG;
		Node current;
		boolean tentativeBetter = false;
		
		//set details of start node
		startNode.g = 0;
		startNode.h = this.heuristicDistance(startNode, goalNode);
		startNode.f = startNode.h;
		
		//add startNode to openset
		openSet.add(startNode);
		
		while (!openSet.isEmpty()) {
			current = openSet.poll();
			if(this.reachedGoal(current)){
				reconstructPath(current);
				return this.toIntArray(this.path);
			}
			closedSet.add(current);
			if (current.g >= maxLength) continue;
			for (Node attached : getDirectlyAttachedNodes(current)) {
				if (closedSet.contains(attached)) continue;
				tentativeG = current.g + 1;
				if(!(openSet.contains(attached))){
	                 tentativeBetter = true;
				}else if(tentativeG < attached.g){
	                 tentativeBetter = true;
				}else{
	                 tentativeBetter = false;
	 			}
	            if(tentativeBetter){
	            	attached.parent = current;
	            	attached.g = tentativeG;
	            	attached.h = heuristicDistance(attached, goalNode);
	                attached.f = attached.g + attached.h;
	                openSet.add(attached);
	           } 
			}
		}
		return null;
	}
	
	/*
	 * Return the distance away in terms of the sum of the row and col dofferences
	 */
	private int heuristicDistance(Node from, Node to){
		return(Math.abs(from.col() - to.col()) + Math.abs(from.row() - to.row()));
	}

	/*
	 * Reconstruct the path to the start, the data is kept in a list
	 */
	public void reconstructPath(Node node){
		if(node.parent != null){
			reconstructPath(node.parent);
		} 
			path.add(node.location);
	}
	
	/*
	 * Return the Integer List as a primitive int array
	 * code found at taken from here http://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
	 */
	int[] toIntArray(List<Integer> list)  {
	    int[] ret = new int[list.size()];
	    int i = 0;
	    for (Integer e : list)  
	        ret[i++] = e.intValue();
	    return ret;
	}
	
	/*
	 * Return array contents as a string
	 */
	public String arrayToString(int[] theArray){
		if(theArray == null) return "";
		StringBuilder arrayAsString = new StringBuilder();
		for(int i = 0; i < theArray.length; i++){
			arrayAsString.append(theArray[i]+" ");
		}
		return arrayAsString.toString();	
	}
}

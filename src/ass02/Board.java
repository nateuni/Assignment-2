package ass02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Board implements SlidingBlock {
	
	LinkedList<Integer> path = new LinkedList<Integer>();
	int n;
	int[] board;
	Node[] node;
	int startPosition;
	
	
	//PriorityQueue<Node> openSet;
	//ArrayList<Node> closedSet;
	
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
		this.aStar(node[startPosition], node[0], Integer.MAX_VALUE);
		return null;
	}

	@Override
	public void addWall(int positionI, int positionJ) {
		// TODO Auto-generated method stub
		
	}
	
	public void initialiseBoard(int startPos){
		for(int i = 0; i < this.board.length; i++){
			if(i == startPos){
				this.board[i] = 0;
			} else{
				this.board[i] = 1;
			}
		}
	}
	
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
	
	public void printBoardToConsole(){
		for(int i = 0; i < this.node.length; i++){
			System.out.print(this.node[i].location+"   ");
			if((i+1) % n == 0){
				System.out.print("\n");
			}
		}
	}
	
	public Node up(Node node){
		if(node.indexOfUp() < 0) return null;
		return this.node[node.indexOfUp()];
	}
	public Node down(Node node){
		if(node.indexOfDown() < 0) return null;
		return this.node[node.indexOfDown()];
	}
	public Node left(Node node){
		if(node.indexOfLeft() < 0) return null;
		return this.node[node.indexOfLeft()];
	}
	public Node right(Node node){
		if(node.indexOfRight() < 0) return null;
		return this.node[node.indexOfRight()];
	}
	
	public Node getNode(int pos){
		return node[pos];
	}
	
	public boolean reachedGoal(Node node){
		return node.location < n;
	}
	
	public ArrayList<Node> getDirectlyAttachedNodes(Node n){
		ArrayList<Node> list = new ArrayList<Node>();
		if(this.up(n) != null) list.add(this.up(n));
		if(this.down(n) != null) list.add(this.down(n));
		if(this.left(n) != null) list.add(this.left(n));
		if(this.right(n) != null) list.add(this.right(n));
		return list;
	}
	

	private int[] aStar(Node startNode, Node goalNode, int maxLength) {
		ArrayList<Node> closedSet = new ArrayList<Node>();
		PriorityQueue<Node> openSet = new PriorityQueue<Node>();
		startNode.h = this.heuristicDistance(startNode, goalNode);
		int tempG;
		Node current;
		boolean yInOpenSet;
		
		while (!openSet.isEmpty()) {
			current = openSet.poll();
			if(this.reachedGoal(current)){
				System.out.print("done");
			}
			closedSet.add(current);
			if (current.g >= maxLength) continue;
			this.printBoardToConsole();
			for (Node attached : getDirectlyAttachedNodes(current)) {
				if (closedSet.contains(attached)) continue;
				tempG = current.g + 1;
				
				yInOpenSet = false;
				for (Node thisState : openSet) {
					if (attached.equals(thisState)) {
						attached = thisState;
						yInOpenSet = true;
					}
				}
				if (!yInOpenSet || tempG < attached.g) {
					attached.parent = current;
					attached.g = tempG;
					attached.h = heuristicDistance(attached, goalNode);
					attached.f = attached.g + attached.h;
				}
				if (!yInOpenSet && attached.f <= maxLength) openSet.add(attached);
			}
		}
		
		return null;
	}
	
	private int heuristicDistance(Node from, Node to){
		return(Math.abs(from.col() - to.col()) + Math.abs(from.row() - to.row()));
	}

	public void reconstructPath(Node node){
		if(node.parent != null){
			reconstructPath(node.parent);
		} else{
			path.add(node.location);
		}
	}
}

package com.ccmdevel.sbisolver.model;

public abstract class Node {
	Node parent;
	Node left;
	Node right;
	
	public Node(){
		this.parent = null;
		this.left = null;
		this.right = null;
	}
	
	public abstract String toString();

	public abstract int compare(Node otherNode);
}

package com.ccmdevel.ooosolver.model;

import java.math.BigInteger;

import com.ccmdevel.ooosolver.model.OOOSExceptions.ExceptionDivideZero;
import com.ccmdevel.ooosolver.model.OOOSExceptions.ExceptionExponentNegative;
import com.ccmdevel.ooosolver.model.OOOSExceptions.ExceptionModuloZero;

public class CalculationTree {
	Node root;
	
	public CalculationTree(){
		this.root = null;
	}
	
	public BigInteger solve() throws ExceptionExponentNegative, ExceptionModuloZero, ExceptionDivideZero {
		if (root != null){
			return getNodeAnswer(root);
		} else {
			return new BigInteger("0");
		}
	}
	
	private BigInteger getNodeAnswer(Node n) throws ExceptionExponentNegative, ExceptionModuloZero, ExceptionDivideZero {
		if (n instanceof OperandNode){
			return ((OperandNode)n).operand;
		} else {
			BigInteger left = getNodeAnswer(n.left);
			BigInteger right = getNodeAnswer(n.right);
			switch (((OperationNode)n).operation){
			case (OpCode.ADD):
				left = left.add(right);
				break;
			case (OpCode.SUB):
				left = left.subtract(right);
				break;
			case (OpCode.MULTIPLY):
				left = left.multiply(right);
				break;
			case (OpCode.DIVIDE):
				OOOSExceptions.checkDivideZero(right);
				left = left.divide(right);
				break;
			case (OpCode.EXP):
				OOOSExceptions.checkExponent(left, right);
				left = left.pow(right.intValue());
				break;
			case (OpCode.MOD):
				OOOSExceptions.checkModuloZero(right);
				left = left.mod(right.abs());
			}
			return left;
		}
	}
	/*
	public void printTree(){
		int treeHeight = this.getTreeHeight();
		System.out.println();
		for (int i = 0; i < treeHeight; i += 1){
			System.out.print(i + ": \t");
			printTreeSubLevel(this.root, 0, i);
			System.out.print('\n');
		}
		System.out.println();
	} */
	
	public void printTreeSubLevel(Node currentNode, int currentDepth, int stopDepth){
		if (currentDepth == stopDepth){
			if (currentNode == null){
				System.out.print("[] ");
			} else {
				System.out.print(currentNode.toString() + " ");
			}
		} else {
			if (currentNode == null){ 
				return; 
			}
			printTreeSubLevel(currentNode.left, currentDepth + 1, stopDepth);
			printTreeSubLevel(currentNode.right, currentDepth + 1, stopDepth);
		}
	}
	
	public int getTreeHeight(){
		if (this.root == null){
			return 0;
		} else {
			int heightRight = getBranchHeight(root.left, 1);
			int heightLeft = getBranchHeight(root.right, 1);
			return heightLeft > heightRight ? heightLeft : heightRight;
		}
	}
	
	public int getBranchHeight(Node node, int depth){
		if (node == null){
			return depth;
		} 
		int heightLeft = getBranchHeight(node.left, depth + 1);
		int heightRight = getBranchHeight(node.right, depth + 1);
		return heightLeft > heightRight ? heightLeft : heightRight;
	}
	
	public void insert(Node newNode){
		if (this.root == null){
			this.root = newNode;
			return;
		}
		
		if (newNode instanceof OperationNode){
			Node p = findOpPos((OperationNode)newNode, this.root);
			if (p instanceof OperandNode){
				if (p == this.root){
					this.root = newNode;
				}
				newNode.left = p;
				newNode.parent = p.parent;
				if (p.parent != null) { 
					p.parent.right = newNode; 
				}
				p.parent = newNode;
			} else {
				if (p == this.root){
					this.root = newNode;
				}
				newNode.left = p;
				newNode.parent = p.parent;
				if (newNode.parent != null){
					newNode.parent.right = newNode;
				}
				p.parent = newNode;
			}
		} else {
			Node p = findLeafPos((OperandNode)newNode, this.root);
			if (p.left == null){
				p.left = newNode;
			} else {
				p.right = newNode;
			}
			newNode.parent = p;
		}
	}
	
	public static Node findOpPos(OperationNode newNode, Node pos){
		if (pos instanceof OperandNode){
			return pos;
		}
		int comparison = newNode.compare((OperationNode)pos);
		if (comparison > 0){
			return findOpPos(newNode, pos.right);
		} else {
			return pos;
		}
	}
	
	public static Node findLeafPos(OperandNode newNode, Node pos){
		if (pos.left == null){
			return pos;
		} else if (pos.right == null){
			return pos;
		} else {
			return findLeafPos(newNode, pos.right);
		}
	}
}

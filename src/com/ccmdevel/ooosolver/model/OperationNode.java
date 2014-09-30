package com.ccmdevel.ooosolver.model;

public class OperationNode extends Node {
	
	public int operation; 
	
	public OperationNode(int operation){
		super();
		this.operation = operation;
	}
	
	@Override
	public int compare(Node otherNode){
		if (otherNode instanceof OperationNode){
			if (this.operation >> 4 > ((OperationNode)otherNode).operation >> 4){
				return 1;
			} else if (this.operation >> 4 < ((OperationNode)otherNode).operation >> 4){
				return -1;
			} else {
				return 0;
			}
		} else {
			return 1;
		}
	}
	
	@Override
	public String toString(){
		String s = "error";
		switch(operation){
		case OpCode.ADD:
			s = "+";
			break;
		case OpCode.DIVIDE:
			s = "/";
			break;
		case OpCode.EXP:
			s = "^";
			break;
		case OpCode.MULTIPLY:
			s = "*";
			break;
		case OpCode.SUB:
			s = "-";
			break;
		case OpCode.MOD:
			s = "m";
		}
		return s;
	}
}

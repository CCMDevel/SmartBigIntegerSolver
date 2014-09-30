package com.ccmdevel.ooosolver.model;

import java.math.BigInteger;

public class OperandNode extends Node {
	public BigInteger operand;
	
	public OperandNode(BigInteger operand){
		this.operand = operand;
	}
	
	public OperandNode(String string){
		this.operand = new BigInteger(string);
	}
	
	@Override
	public int compare(Node otherNode){
		if (otherNode instanceof OperandNode){
			BigInteger otherOperand = ((OperandNode)otherNode).operand;
			return operand.compareTo(otherOperand);
		} else {
			return -1;
		}
	}
	
	@Override
	public String toString(){
		return operand.toString();
	}
}

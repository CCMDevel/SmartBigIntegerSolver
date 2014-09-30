package com.ccmdevel.sbisolver;

import java.math.BigInteger;
import java.util.ArrayList;

import com.ccmdevel.sbisolver.model.CalculationTree;
import com.ccmdevel.sbisolver.model.SBISExceptions;
import com.ccmdevel.sbisolver.model.OpCode;
import com.ccmdevel.sbisolver.model.OperandNode;
import com.ccmdevel.sbisolver.model.OperationNode;
import com.ccmdevel.sbisolver.model.SBISExceptions.ExceptionCalculationFormat;
import com.ccmdevel.sbisolver.model.SBISExceptions.ExceptionDivideZero;
import com.ccmdevel.sbisolver.model.SBISExceptions.ExceptionExponentNegative;
import com.ccmdevel.sbisolver.model.SBISExceptions.ExceptionModuloZero;

public class SBISolver {
	// Calculation with operands above these thresholds is not recommended as they
	// take too long.
	public static final BigInteger FACT_THRESHOLD = new BigInteger("200000")
	                             , EXP_THRESHOLD = new BigInteger("2000000");
	
	private int currentOp = OpCode.NONE;
	private boolean ooo = true;
	private ArrayList<BigInteger> operands = new ArrayList<BigInteger>();
	private ArrayList<Integer> operations = new ArrayList<Integer>();
	
	public boolean isFirstOperation(){
		return operations.size() == 0 && currentOp == OpCode.NONE;
	}
	
	public void appendOperand(String string) throws NumberFormatException{
		appendCurrentOp();
		operands.add(new BigInteger(string));
	}
	
	public int getNumberOperands(){
		return operands.size();
	}
	
	public int getNumberOperations(){
		return operations.size();
	}
	
	public int getCurrentOp(){
		return currentOp;
	}
	
	public void setCurrentOp(int op){
		if (op == OpCode.FACTORIAL){
			if (operands.size() > 0){
				BigInteger operand = operands.remove(operands.size() - 1);
				operands.add(calcFactorial(operand));
				currentOp = OpCode.NONE;
			}
			return;
		}
		currentOp = op;
	}
	
	public void setOOO(boolean ooo){
		this.ooo = ooo;
	}
	
	public void clear(){
		currentOp = OpCode.NONE;
		while (!operands.isEmpty()){
			operands.remove(0);
		}
		while (!operations.isEmpty()){
			operations.remove(0);
		}
	}
	
	public String calculate() throws ExceptionExponentNegative, ExceptionModuloZero, ExceptionDivideZero, ExceptionCalculationFormat {
		if (ooo){
			return solveTree();
		} else {
			return solveLinear();
		}
	}
	
	private void appendCurrentOp(){
		if (currentOp != OpCode.NONE && operands.size() != 0){
			operations.add(currentOp);
		} 
	}
	
	private BigInteger calcFactorial(BigInteger n) {
		boolean negate = n.compareTo(BigInteger.ZERO) < 0;
		n = n.abs();
		
		BigInteger ans = new BigInteger("1");
		while (n.compareTo(BigInteger.ZERO) > 0){
			ans = ans.multiply(n);
			n = n.subtract(BigInteger.ONE);
		}
		return negate ? ans.negate() : ans;
	}
	
	private String solveTree() throws ExceptionModuloZero, ExceptionDivideZero, ExceptionExponentNegative, ExceptionCalculationFormat {
		CalculationTree cT = new CalculationTree();
		int j = 0;
		int k = 0;
		if (operands.size() - operations.size() != 1){
			throw new ExceptionCalculationFormat();
		}
		for (int i = 0; i < operands.size() + operations.size(); i += 1){
			switch(i % 2){
			case 0:
				cT.insert(new OperandNode(operands.get(j)));
				j += 1;
				break;
			case 1:
				cT.insert(new OperationNode(operations.get(k)));
				k += 1;
				break;
			}
		}
		return cT.solve().toString();
	}
	
	private String solveLinear() throws ExceptionDivideZero, ExceptionExponentNegative, ExceptionModuloZero {
		BigInteger ans = operands.get(0);
		for (int i = 0; i < operations.size(); i += 1){
			BigInteger operand = operands.get(i + 1);
			switch (operations.get(i)){
			case OpCode.ADD:
				ans = ans.add(operand);
				break;
			case OpCode.DIVIDE:
				SBISExceptions.checkDivideZero(operand);
				ans = ans.divide(operand);
				break;
			case OpCode.EXP:
				SBISExceptions.checkExponent(ans, operand);
				ans = ans.pow(operand.intValue());
				break;
			case OpCode.MOD:
				SBISExceptions.checkModuloZero(operand);
				ans = ans.mod(operand);
				break;
			case OpCode.MULTIPLY:
				ans = ans.multiply(operand);
				break;
			case OpCode.SUB:
				ans = ans.subtract(operand);
				break;
			}
		}
		return ans.toString();
	}
}

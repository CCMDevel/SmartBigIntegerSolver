package com.ccmdevel.sbisolver;

import com.ccmdevel.sbisolver.model.OpCode;

public class Sample {
	public static final void main(String[] asdf){
		
		// 2 + 2 = 
		SBISolver solver = new SBISolver();
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.ADD);
		solver.appendOperand("2");
		
		// Unnecessary operations are ignored
		solver.setCurrentOp(OpCode.DIVIDE); 
		
		try {
			System.out.println("2 + 2 = " + solver.calculate());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			solver.clear();
		}
		
		// With order of operations
		// 2 + 2 * 2 ^ 2 = 
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.ADD);
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.MULTIPLY);
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.EXP);
		solver.appendOperand("2");
		
		try {
			System.out.println("2 + 2 * 2 ^ 2 = " + solver.calculate());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			solver.clear();
		}
		
		// Without order of operations
		// 2 + 2 * 2 ^ 2 = 
		solver.setOOO(false);
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.ADD);
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.MULTIPLY);
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.EXP);
		solver.appendOperand("2");
				
		try {
			System.out.println("2 + 2 * 2 ^ 2 = " + solver.calculate());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			solver.clear();
		}
		
		// Can handle input as expected from a user on a calculator
		// 2 - 2 + 10 ^ 2
		solver.setOOO(true);
		solver.appendOperand("2");
		// user changing their mind about the operation
		solver.setCurrentOp(OpCode.ADD);
		solver.setCurrentOp(OpCode.MULTIPLY);
		solver.setCurrentOp(OpCode.SUB);
		solver.appendOperand("2");
		solver.setCurrentOp(OpCode.EXP);
		solver.setCurrentOp(OpCode.MOD);
		solver.setCurrentOp(OpCode.ADD);
		solver.appendOperand("10");
		solver.setCurrentOp(OpCode.MULTIPLY);
		solver.setCurrentOp(OpCode.EXP);
		solver.appendOperand("2");
		
		try {
			System.out.println("2 - 2 * 10 ^ 2 = " + solver.calculate());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			solver.clear();
		}
		
		// What about factorial? Note: factorial is treated as a function in
		// math. Thus, it is given highest priority in order of operations.
		solver.appendOperand("10");
		solver.setCurrentOp(OpCode.ADD);
		solver.appendOperand("5");
		solver.setCurrentOp(OpCode.FACTORIAL); // this op is not ignored
		solver.setCurrentOp(OpCode.ADD);
		solver.appendOperand("1");
		
		try {
			System.out.println("10 + 5! + 1 = " + solver.calculate());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			solver.clear();
		}
		
	}
}

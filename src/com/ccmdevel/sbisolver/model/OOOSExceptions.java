package com.ccmdevel.sbisolver.model;

import java.math.BigInteger;

public class OOOSExceptions {
	public static final int EXCEPTION_EXP_BIG = 0xe0
	   	 			      , EXCEPTION_EXP_NEG = 0xe1
		 			      , EXCEPTION_FACT_BIG = 0xe2
		 			      , EXCEPTION_NO_INPUT = 0xe3
		 			      , EXCEPTION_NO_ANS = 0xe4
		 			      , EXCEPTION_NO_INVERSE = 0xe5
	                      , EXCEPTION_NOT_NUMBER = 0xe6
	                      , EXCEPTION_ZERO_DIVIDE = 0xe7
	                      , EXCEPTION_ZERO_MOD = 0xe8
	                      , EXCEPTION_CALC_TERMINATED = 0xe9
	                      , EXCEPTION_CALC_RUNTIME = 0xea;
	 
	public static final String MSG_EXP_NEGATIVE = "Error: negative exponent."
			 				 , MSG_EXP_BIG = "Error: exponent too large."
			 				 , MSG_FACT_BIG = "Error: factorial too large."
			 				 , MSG_NO_INPUT = "Error: no input."
			 				 , MSG_NO_ANS = "Error: Ans is undefined."
			 				 , MSG_NO_INVERSE = "No inverse exists: n and M are not relatively prime."
			 				 , MSG_NOT_NUMBER = "Error: integers only."
			 				 , MSG_ZERO_DIVIDE = "Undefined: n / 0 is undefined."
			 				 , MSG_ZERO_MOD = "Undefined: n mod 0 is undefined.";
	
	public static final String MSG_OVERFLOW_D = "Warning: Ans has %d digits! Scroll down on the answer window."
    		                 , ANS_UNDEFINED = "Ans = undefined"
    		                 , ANS_NOT_INTEGER = "Ans is not an integer."
    		                 , ANS_CALC_TERMINATED = "Error: calculation terminated."
    				 		 , ANS_CALC_RUNTIME = "Error: calculation too intense. You should probibly use a super computer for this one.";
     
	public static void checkDivideZero(BigInteger operand) throws ExceptionDivideZero{
		if (operand.compareTo(BigInteger.ZERO) == 0){
			throw new ExceptionDivideZero();
		}
	}
	
	public static void checkModuloZero(BigInteger operand) throws ExceptionModuloZero{
		if (operand.compareTo(BigInteger.ZERO) == 0){
			throw new ExceptionModuloZero();
		}
	}
	
	public static void checkExponent(BigInteger left, BigInteger right) throws ExceptionExponentNegative{
		if (right.compareTo(BigInteger.ZERO) < 0){
			throw new ExceptionExponentNegative();
		}
		/*
		if (right.compareTo(EXP_THRESHOLD) >= 0){
			throw new ExceptionExponentBig();
		}
		*/
	}
	
	private OOOSExceptions(){
	}
	
	public static class ExceptionCalculationFormat extends Exception{
		private static final long serialVersionUID = 3076534729319322714L;

		public ExceptionCalculationFormat(){
    		super("Improper calculation");
    	}
		
		@Override
		public String toString(){
    		return "Exception: Improper calculation.";
    	}
	}
	
    public static class ExceptionDivideZero extends Exception {
		private static final long serialVersionUID = 3076534729319322714L;

		public ExceptionDivideZero(){
    		super("Tried to divide something by zero.");
    	}
		
		@Override
		public String toString(){
    		return "Exception: Divide by Zero";
    	}
    }
     
    public static class ExceptionExponentBig extends Exception {
   		private static final long serialVersionUID = 5098635419470530709L;

   		public ExceptionExponentBig(){
    		super("Tried to provide BigInteger operation with big exponent");
    	}
   		
   		@Override
   		public String toString(){
    		return "Exception: Exponent Big";
    	}
    }
    
    public static class ExceptionExponentNegative extends Exception {
    	private static final long serialVersionUID = 7410240427079321141L;

    	public ExceptionExponentNegative(){
    		super("Tried to provide BigInteger operation with negative exponent");
    	}
    	
    	@Override
    	public String toString(){
    		return "Exception: Exponent Negative";
    	}
    }
    

    public static class ExceptionFactorialBig extends Exception{
    	private static final long serialVersionUID = 2191544090291202651L;

    	public ExceptionFactorialBig(){
    		super("Factorial too large to calculate.");
    	}
    	
    	@Override
   		public String toString(){
    		return "Exception: Factorial Big";
    	}
    }
    
    public static class ExceptionModuloZero extends Exception {
    	private static final long serialVersionUID = 8765310128945230800L;

    	public ExceptionModuloZero(){
    		super("Tried to mod by zero.");
    	}
    	
    	@Override
   		public String toString(){
    		return "Exception: Modulo Zero";
    	}
    }
    
    public static class ExceptionNoInverseExists extends Exception{
    	private static final long serialVersionUID = 8014038579778481794L;
    	
    	public ExceptionNoInverseExists(){
    		super("Error: Integers are not relatively prime.");
    	}
    	
    	@Override
   		public String toString(){
    		return "Exception: No Inverse Exists";
    	}
    }
}

package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The mod expression provides the mathematical modulus function
 * 
 * @author simon
 *
 */
public class ExprMod extends AbstractExpression<Integer> implements K2Expression<Integer> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<Integer> num1Expr;
	K2Expression<Integer> num2Expr;
	Integer num1;
	Integer num2;

	/**
	 * Create a modulus expression to calculate the modulus to the two numerical values
	 * @param num1	The numerator
	 * @param num2	The denominator
	 */
	public ExprMod(K2Expression<Integer> num1, K2Expression<Integer> num2) {
		super(Integer.class);
		this.num1Expr = num1;
		this.num2Expr = num2;
	}

	/**
	 * Create a modulus expression to calculate the modulus to the two numerical values
	 * @param num1	The numerator
	 * @param num2	The denominator
	 */
	public ExprMod(K2Expression<Integer> num1, Integer num2) {
		super(Integer.class);
		this.num1Expr = num1;
		this.num2 = num2;
	}

	/**
	 * Create a modulus expression to calculate the modulus to the two numerical values
	 * @param num1	The numerator
	 * @param num2	The denominator
	 */
	public ExprMod(Integer num1, K2Expression<Integer> num2) {
		super(Integer.class);
		this.num1 = num1;
		this.num2Expr = num2;
	}

	@Override
	public Integer evaluate(Evaluator eval) {
		
		Integer n1 = (num1Expr == null) ? num1: num1Expr.evaluate(eval);
		Integer n2 = (num2Expr == null) ? num2: num2Expr.evaluate(eval);
		
		if (n1 == null || n2 == null) return null;
		
		return n1 % n2;

	}

}

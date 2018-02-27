package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.DoubleUtil;

/**
 * The quotient expression calculates the quotient of two numbers
 * 
 * @author simon
 *
 */
public class ExprQuot<Number> extends AbstractExpression<Number> implements K2Expression<Number> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<? extends Number> num1Exp = null;
	K2Expression<? extends Number> num2Exp = null;
	Number num1 = null;
	Number num2 = null;

	/**
	 * Create a quotient expression for two numerical expressions
	 * @param num1	The numerator expression
	 * @param num2	The denominator expression
	 */
	public ExprQuot(K2Expression<? extends Number> num1, K2Expression<? extends Number> num2) {
		super(num1.getJavaType());
		this.num1Exp = num1;
		this.num2Exp = num2;
	}

	/**
	 * Create a quotient expression for a numerical expression and a literal value
	 * @param num1	The numerator expression
	 * @param num2	The denominator
	 */
	public ExprQuot(K2Expression<? extends Number> num1, Number num2) {
		super(num1.getJavaType());
		this.num1Exp = num1;
		this.num2 = num2;
	}

	/**
	 * Create a quotient expression for a numerical expression and a literal value
	 * @param num1	The numerator
	 * @param num2	The denominator expression
	 */
	@SuppressWarnings("unchecked")
	public ExprQuot(Number num1, K2Expression<? extends Number> num2) {
		super((Class<? extends Number>) num1.getClass());
		this.num1 = num1;
		this.num2Exp = num2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Number evaluate(Evaluator eval) {

		Number n1 = (num1Exp == null) ? num1: num1Exp.evaluate(eval);
		Number n2 = (num2Exp == null) ? num2: num2Exp.evaluate(eval);
		
		if (n1 == null || n2 == null) return (Number)null;
		
		if (getJavaType().equals(Integer.class)) {
			return (Number) new Integer(((Integer)n1) / ((Integer)n2));
		} else if (getJavaType().equals(Long.class)) {
			return (Number) new Long(((Long)n1) / ((Long)n2));
		} else if (getJavaType().equals(Float.class)) {
			return (Number) new Float(((Float)n1) / ((Float)n2));
		} else if (getJavaType().equals(Double.class)) {
			return (Number) new Double(((Double)n1) / ((Double)n2));
		} else 
		return (Number)null;
	}

}

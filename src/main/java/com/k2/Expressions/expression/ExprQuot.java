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
public class ExprQuot extends AbstractExpression<Double> implements Expression<Double> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<? extends Number> num1Exp = null;
	Expression<? extends Number> num2Exp = null;
	Number num1 = null;
	Number num2 = null;

	/**
	 * Create a quotient expression for two numerical expressions
	 * @param num1	The numerator expression
	 * @param num2	The denominator expression
	 */
	public ExprQuot(Expression<? extends Number> num1, Expression<? extends Number> num2) {
		super(Double.class);
		this.num1Exp = num1;
		this.num2Exp = num2;
	}

	/**
	 * Create a quotient expression for a numerical expression and a literal value
	 * @param num1	The numerator expression
	 * @param num2	The denominator
	 */
	public ExprQuot(Expression<? extends Number> num1, Number num2) {
		super(Double.class);
		this.num1Exp = num1;
		this.num2 = num2;
	}

	/**
	 * Create a quotient expression for a numerical expression and a literal value
	 * @param num1	The numerator
	 * @param num2	The denominator expression
	 */
	public ExprQuot(Number num1, Expression<? extends Number> num2) {
		super(Double.class);
		this.num1 = num1;
		this.num2Exp = num2;
	}

	@Override
	public Double evaluate(Evaluator eval) {

		Number n1 = (num1Exp == null) ? num1: num1Exp.evaluate(eval);
		Number n2 = (num2Exp == null) ? num2: num2Exp.evaluate(eval);
		
		if (n1 == null || n2 == null) return (Double)null;
		
		return DoubleUtil.round(n1.doubleValue()/n2.doubleValue(), 12);
	}

}

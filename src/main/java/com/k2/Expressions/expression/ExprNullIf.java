package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

/**
 * The nullIf expression returns null if the first argument is null or the two arguments are equal, otherwise it returns the first value
 * 
 * @author simon
 *
 * @param <Y>	The type of the value returned by the expression.
 * 
 */
public class ExprNullIf<Y> extends AbstractExpression<Y> implements K2Expression<Y> {
	
	K2Expression<Y> expr1 = null;
	K2Expression<?> expr2 = null;
	Y value2 = null;

	/**
	 * Create a nullIf expression for the two numerical expressions
	 * @param expr1	The first expression
	 * @param expr2	The second expression
	 */
	public ExprNullIf(K2Expression<Y> expr1, K2Expression<?> expr2) {
		super(expr1.getJavaType());
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Create a nullIf expression for the numerical expression and numeric literal
	 * @param expr1	The first expression
	 * @param value2		The second expression
	 */
	public ExprNullIf(K2Expression<Y> expr1, Y value2) {
		super(expr1.getJavaType());
		this.expr1 = expr1;
		this.value2 = value2;
	}

	@Override
	public Y evaluate(Evaluator eval) {
		
		Object val = (expr2 == null) ? value2: expr2.evaluate(eval);
		Y y = expr1.evaluate(eval);
		if (y == null) return (Y)null;
		if (y.equals(val)) return (Y)null;
		return y;
		
	}

}

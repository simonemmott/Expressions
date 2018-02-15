package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

/**
 * This implementation of the Expression interface provides implements an mathematical absolute method that returns the
 * absolute value of the given numerical expression
 * 
 * @author simon
 *
 * @param <N> The numerical type of this absolute expression
 */
public class ExprAbsolute<N extends Number> extends AbstractExpression<N> implements Expression<N> {
	
	Expression<N> num;

	/**
	 * Create the absolute expression setting the source expression
	 * @param num	The numeric value for which an absolute value is required
	 */
	@SuppressWarnings("unchecked")
	public ExprAbsolute(Expression<N> num) {
		super((Class<? extends N>) num.getClass());
		this.num = num;
	}

	@SuppressWarnings("unchecked")
	@Override
	public N evaluate(Evaluator eval) {

		N value = num.evaluate(eval);
		if (value == null) return null;
		if (num.getJavaType().equals(Integer.class)) {
			return (N)new Integer(Math.abs(value.intValue()));
		} else if (num.getJavaType().equals(Long.class)) {
			return (N)new Long(Math.abs(value.longValue()));
		} else if (num.getJavaType().equals(Float.class)) {
			return (N)new Float(Math.abs(value.floatValue()));
		} else if (num.getJavaType().equals(Double.class)) {
			return (N)new Double(Math.abs(value.doubleValue()));
		}
		
		return value;
	}

}

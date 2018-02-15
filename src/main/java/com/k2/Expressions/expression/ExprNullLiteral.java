package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

/**
 * The null literal expression provides a typed null value
 * 
 * @author simon
 *
 * @param <T>	The type of this null value
 */
public class ExprNullLiteral<T> extends AbstractExpression<T> implements Expression<T> {
	
	/**
	 * Create a null literal expression to return a null of the given type
	 * @param cls	The type of the null value to return
	 */
	public ExprNullLiteral(Class<T> cls) {
		super(cls);
	}

	@Override
	public T evaluate(Evaluator eval) {
		return (T)null;
	}


}

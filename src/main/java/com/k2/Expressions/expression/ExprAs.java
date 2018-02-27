package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

/**
 * This implementation of the Expression interface casts the evaluated result of the source expression into the desired type
 * 
 * @author simon
 *
 * @param <T> The desired type of the expressed value
 */
public class ExprAs<T> extends AbstractExpression<T> implements K2Expression<T> {
	
	K2Expression<?> source;

	/**
	 * Createan expression to cast the value of the given expression to the given class
	 * @param cls		The class to which to cast the value of the given expression
	 * @param source		The source expression
	 */
	public ExprAs(Class<T> cls, K2Expression<?> source) {
		super(cls);
		this.source = source;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T evaluate(Evaluator eval) {

		return (T) source.evaluate(eval);
	}

}

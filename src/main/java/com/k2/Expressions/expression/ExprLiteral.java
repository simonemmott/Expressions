package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

/**
 * The literal expression encapsulates a given literal value as an expression
 * 
 * @author simon
 *
 * @param <T>	The type of the literal value
 */
public class ExprLiteral<T> extends AbstractExpression<T> implements Expression<T> {
	
	T literal;

	@SuppressWarnings("unchecked")
	public ExprLiteral(T literal) {
		super((Class<? extends T>) literal.getClass());
		this.literal = literal;
	}

	@Override
	public T evaluate(Evaluator eval) {
		return literal;
	}


}

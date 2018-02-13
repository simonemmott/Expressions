package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

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

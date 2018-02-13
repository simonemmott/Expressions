package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

public class ExprNullLiteral<T> extends AbstractExpression<T> implements Expression<T> {
	
	public ExprNullLiteral(Class<T> cls) {
		super(cls);
	}

	@Override
	public T evaluate(Evaluator eval) {
		return (T)null;
	}


}

package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

public class GenericExpression<X> extends AbstractExpression<X> {

	Expression<?> expression;
	
	public GenericExpression(Expression<?> expression, Class<X> javaType) {
		super(javaType);
		this.expression = expression;
	}

	@SuppressWarnings("unchecked")
	@Override
	public X evaluate(Evaluator eval) {
		return (X) expression.evaluate(eval);
	}


}

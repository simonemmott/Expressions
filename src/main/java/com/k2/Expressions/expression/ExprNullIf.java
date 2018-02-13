package com.k2.Expressions.expression;

import com.k2.Expressions.Evaluator;

public class ExprNullIf<Y> extends AbstractExpression<Y> implements Expression<Y> {
	
	Expression<Y> expr1 = null;
	Expression<?> expr2 = null;
	Y value2 = null;

	public ExprNullIf(Expression<Y> expr1, Expression<?> expr2) {
		super(expr1.getJavaType());
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	public ExprNullIf(Expression<Y> expr1, Y value2) {
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

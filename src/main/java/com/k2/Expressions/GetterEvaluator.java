package com.k2.Expressions;

import com.k2.Expressions.expression.GetterExpression;

public interface GetterEvaluator<E> extends Evaluator{

	public <T> T valueOf(GetterExpression<E, T> getterExpression);

}

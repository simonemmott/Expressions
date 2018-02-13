package com.k2.Expressions;

import com.k2.Expressions.expression.ParameterExpression;

public interface ParameterEvaluator extends Evaluator{

	public <T> T valueOf(ParameterExpression<T> parameterExpression);

}

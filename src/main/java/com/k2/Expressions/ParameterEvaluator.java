package com.k2.Expressions;

import com.k2.Expressions.expression.ParameterExpression;

/**
 * The parameter evaluator provides values for parameter expressions
 * 
 * @author simon
 *
 */
public interface ParameterEvaluator extends Evaluator{

	/**
	 * Get the value of the given parameter expression
	 * @param parameterExpression	The parameter expression for which to get the value
	 * @return	The value of the parameter expression as evaluated by this evaluator
	 * @param <T> The type of the returned value
	 */
	public <T> T valueOf(ParameterExpression<T> parameterExpression);

}

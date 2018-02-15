package com.k2.Expressions;

import com.k2.Expressions.expression.GetterExpression;

/**
 * Getter evaluators provide values for getter expressions
 * 
 * @author simon
 *
 * @param <E> The type of the object providing the got values
 */
public interface GetterEvaluator<E> extends Evaluator{

	/**
	 * Get the value for the given getter expression
	 * @param getterExpression	the getter expression for which to return the value
	 * @return	The value of the getter expression as evaluated by this evaluator
	 * @param <T> The type of the value returned
	 */
	public <T> T valueOf(GetterExpression<E, T> getterExpression);

}

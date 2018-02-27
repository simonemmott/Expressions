package com.k2.Expressions.expression;

import javax.persistence.criteria.Expression;

import com.k2.Expressions.Evaluator;

/**
 * This interface defines the methods available on all Expressions
 * Expressions provide an abstracted value derived from an Evaluator. This construct allows the structure
 * of complex expressions to be defined in isolation of the source of the values used to determine the 
 * value of the complex expression.
 * 
 * @author simon
 *
 * @param <T>	The type of the value returned by the expression
 */
public interface K2Expression<T> extends Expression<T> {

	/**
	 * This method extracts the value of this expression from the given evaluator
	 * 
	 * @param eval	The evaluator to be used to derive values for this expression and its sub expressions
	 * @return	The value of this expression as derived from the given evaluator
	 */
	public T evaluate(Evaluator eval);
	

}

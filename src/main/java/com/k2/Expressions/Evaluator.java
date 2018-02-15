package com.k2.Expressions;

import com.k2.Expressions.expression.CurrentTime;
import com.k2.Expressions.expression.ParameterExpression;
import com.k2.Expressions.predicate.Predicate;

/**
 * The evaluator interface defines the minimum methods that are to be implemented by all Evaluators.
 * 
 * Evaluators are used to provide values for expressions of various types
 * 
 * @author simon
 *
 */
public interface Evaluator {

	/**
	 * Add the parameter expression to the list of required paramters maintained by the evaluator
	 * @param expr	The paramter expression
	 */
	public void add(ParameterExpression<?> expr);
	
	/**
	 * Check that this evaluator has values set for all the parameters defined in the array of predicates
	 * @param predicates		The predicates that contain parameter expressions to be evaluated by this evaluator
	 * @return		True if all the parameter expression contained in the list of predicates has a value defined by this evaluator
	 */
	public boolean checkParametersSet(Predicate ... predicates);
	
	/**
	 * Get the current time from this evaluator. Repeated calls to this method will return the same value for the current time.
	 * @return	An instance of CurrentTime 
	 */
	public CurrentTime getCurrentTime();

}

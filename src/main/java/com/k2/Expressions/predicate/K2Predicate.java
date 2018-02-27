package com.k2.Expressions.predicate;

import javax.persistence.criteria.Predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;

/**
 * The predicate interface extends the Expression interface for Boolean data type.
 * The compound predicates And and Or can contain other predicates and as such from a hierarchical assembly of 
 * predicates.
 * 
 * @author simon
 *
 */
public interface K2Predicate extends Predicate, K2Expression<Boolean> {

	/**
	 * This method returns a negated variant of this predicate
	 * @return	This predicate having been negated. Or if the predicate was already negated then it returns this predicate having been un-negated
	 */
	public K2Predicate not();

	/**
	 * Inform the evaluator of all the parameter expressions used by this predicate assembly
	 * @param eval	The evaluator in which the list of required parameters will be populated
	 */
	public void populateParameters(Evaluator eval);

}

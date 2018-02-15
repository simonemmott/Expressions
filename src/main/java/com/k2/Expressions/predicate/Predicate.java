package com.k2.Expressions.predicate;

import java.util.List;

import javax.persistence.criteria.Predicate.BooleanOperator;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;

/**
 * The predicate interface extends the Expression interface for Boolean data type.
 * The compound predicates And and Or can contain other predicates and as such from a hierarchical assembly of 
 * predicates.
 * 
 * @author simon
 *
 */
public interface Predicate extends Expression<Boolean>{

	/**
	 * This method returns true if the predicate has been negated
	 * @return	True if this predicate has been negated
	 */
	public boolean isNegated();

	/**
	 * This method returns a negated variant of this predicate
	 * @return	This predicate having been negated. Or if the predicate was already negated then it returns this predicate having been un-negated
	 */
	public Predicate not();

	/**
	 * This method returns the child boolean expressions of this predicate if it is an and or or predicate
	 * @return	The child boolean expressions that form this node predicate
	 */
	public List<Expression<Boolean>> getExpressions();

	/**
	 * The boolean operator of this predicate if it is an and or or predicate
	 * @return	boolean operator of this predicate if it has been set
	 */
	public BooleanOperator getOperator();

	/**
	 * Inform the evaluator of all the parameter expressions used by this predicate assembly
	 * @param eval	The evaluator in which the list of required parameters will be populated
	 */
	public void populateParameters(Evaluator eval);

}

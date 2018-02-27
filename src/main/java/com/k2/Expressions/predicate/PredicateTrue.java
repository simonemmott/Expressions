package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;

/**
 * The true predicate returns true
 * 
 * @author simon
 *
 */
public class PredicateTrue extends AbstractPredicate implements K2Predicate {

	@Override
	public Boolean evaluate(Evaluator eval) {
		return isNegatedRVal(true);
	}

	@Override
	public void populateParameters(Evaluator eval) { }


}

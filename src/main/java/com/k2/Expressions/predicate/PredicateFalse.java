package com.k2.Expressions.predicate;


import com.k2.Expressions.Evaluator;

/**
 * The false predicate returns false
 * 
 * @author simon
 *
 */
public class PredicateFalse extends AbstractPredicate implements K2Predicate {

	@Override
	public void populateParameters(Evaluator eval) { }

	@Override
	public Boolean evaluate(Evaluator eval) {
		return isNegatedRVal(false);
	}


}

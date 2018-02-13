package com.k2.Expressions.predicate;


import com.k2.Expressions.Evaluator;

public class PredicateFalse extends AbstractPredicate implements Predicate {

	@Override
	public void populateParameters(Evaluator eval) { }

	@Override
	public Boolean evaluate(Evaluator eval) {
		return isNegatedRVal(false);
	}


}

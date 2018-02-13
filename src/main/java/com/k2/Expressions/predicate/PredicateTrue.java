package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;

public class PredicateTrue extends AbstractPredicate implements Predicate {

	@Override
	public Boolean evaluate(Evaluator eval) {
		if (this.isNegated()) return false;
		return true;
	}

	@Override
	public void populateParameters(Evaluator eval) { }


}

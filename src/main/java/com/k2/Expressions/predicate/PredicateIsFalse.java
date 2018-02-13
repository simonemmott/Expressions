package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

public class PredicateIsFalse extends AbstractPredicate implements Predicate {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Expression<Boolean> bool;

	public PredicateIsFalse(Expression<Boolean> bool) {
		this.bool = bool;
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		if (bool == null) return false;
		return isNegatedRVal(!bool.evaluate(eval));
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(bool != null && bool instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)bool);
	}


}

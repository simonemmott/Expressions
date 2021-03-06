package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.K2ParameterExpression;

/**
 * The is true predicate checks whether its given value is true
 * 
 * @author simon
 *
 */
public class PredicateIsTrue extends AbstractPredicate implements K2Predicate {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private K2Expression<Boolean> bool;

	/**
	 * Create an is true predicate to test whether the value of the given boolean expression is true
	 * 
	 * @param bool The boolean expression to check
	 */
	public PredicateIsTrue(K2Expression<Boolean> bool) {
		this.bool = bool;
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		if (bool == null) return false;
		return isNegatedRVal(bool.evaluate(eval));
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(bool != null && bool instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)bool);
	}


}

package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.K2ParameterExpression;

/**
 * THe null predicate checks whether the value of its argument is null
 * 
 * @author simon
 *
 */
public class PredicateNull extends AbstractPredicate implements K2Predicate {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<?> expr;
	
	/**
	 * Create a predicate to check whether the value of the given expression is null
	 * @param expr	The expression whose value is to be checked for null
	 */
	public PredicateNull(K2Expression<?> expr) {
		this.expr = expr;
	}


	@Override
	public void populateParameters(Evaluator eval) {
		if(expr != null && expr instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)expr);
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		logger.trace("Evaluate null {}", getAlias());
		Object value = expr.evaluate(eval);
		logger.trace("Evaluate null {} evaluates {}", getAlias(), value);
		return isNegatedRVal(value == null);
	}

}

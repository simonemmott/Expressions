package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * The not null predicate checks that the value of its argeuemnt is not null
 * 
 * @author simon
 *
 */
public class PredicateNotNull extends AbstractPredicate implements K2Predicate {

	K2Expression<?> expr;
	
	/**
	 * Create a not null predicate for the given expression
	 * @param expr	The expression whose value should be checked for not null
	 */
	public PredicateNotNull(K2Expression<?> expr) {
		this.expr = expr;
	}


	@Override
	public void populateParameters(Evaluator eval) {
		if(expr != null && expr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr);
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		Object value = expr.evaluate(eval);
		return isNegatedRVal(value != null);
	}

}

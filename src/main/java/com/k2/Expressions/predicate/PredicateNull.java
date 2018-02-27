package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * THe null predicate checks whether the value of its argument is null
 * 
 * @author simon
 *
 */
public class PredicateNull extends AbstractPredicate implements K2Predicate {

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
		if(expr != null && expr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr);
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		Object value = expr.evaluate(eval);
		return isNegatedRVal(value == null);
	}

}

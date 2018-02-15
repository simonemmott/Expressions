package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * The not predicate negates its given boolean expression
 * 
 * @author simon
 *
 */
public class PredicateNot extends AbstractPredicate implements Predicate {

	Expression<Boolean> expr;
	
	/**
	 * Create a negating predicate for the given boolean expression
	 * @param expr	The boolean expression to negate
	 */
	public PredicateNot(Expression<Boolean> expr) {
		this.expr = expr;
	}


	@Override
	public void populateParameters(Evaluator eval) {
		if(expr != null && expr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr);
		if(expr != null && expr instanceof Predicate) ((Predicate)expr).populateParameters(eval);
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		Boolean b = expr.evaluate(eval);
		if (b == null) return false;
		return isNegatedRVal(!b);
	}

}

package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;


public class PredicateNotNull extends AbstractPredicate implements Predicate {

	Expression<?> expr;
	
	public PredicateNotNull(Expression<?> expr) {
		this.expr = expr;
	}


	@Override
	public void populateParameters(Evaluator eval) {
		if(expr != null && expr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr);
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		Object value = expr.evaluate(eval);
		if (value == null) return (isNegated()) ? true: false;
		return (isNegated()) ? false: true;
	}

}

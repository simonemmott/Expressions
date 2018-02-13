package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

public class PredicateNull extends AbstractPredicate implements Predicate {

	Expression<?> expr;
	
	public PredicateNull(Expression<?> expr) {
		this.expr = expr;
	}


	@Override
	public void populateParameters(Evaluator eval) {
		if(expr != null && expr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr);
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		Object value = expr.evaluate(eval);
		if (value == null) return (isNegated()) ? false: true;
		return (isNegated()) ? true: false;
	}

}

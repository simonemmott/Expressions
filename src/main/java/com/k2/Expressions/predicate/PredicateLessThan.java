package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

public class PredicateLessThan extends AbstractPredicate implements Predicate {
	
	private Expression<? extends Comparable<?>> expr1 = null;
	private Expression<? extends Comparable<?>> expr2 = null;
	private Comparable<?> comp = null;

	public <Y extends Comparable<? super Y>> PredicateLessThan(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	public <Y extends Comparable<? super Y>> PredicateLessThan(Expression<? extends Y> expr, Y comp) {
		this.expr1 = expr;
		this.comp = comp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Boolean evaluate(Evaluator eval) {
		if (expr1 == null || expr1.evaluate(eval) == null) return false;
		
		Comparable c1 = expr1.evaluate(eval);
		Comparable c2 = (expr2 != null) ? expr2.evaluate(eval): comp;
		
		
		return isNegatedRVal(c1.compareTo(c2) < 0);
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(expr1 != null && expr1 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr1);
		if(expr2 != null && expr2 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr2);
	}

}

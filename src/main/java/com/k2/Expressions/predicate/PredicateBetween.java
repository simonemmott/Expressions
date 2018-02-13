package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;


public class PredicateBetween extends AbstractPredicate implements Predicate {

	Expression<? extends Comparable<?>> check;
	Expression<? extends Comparable<?>> beginExpr = null;
	Expression<? extends Comparable<?>> endExpr = null;
	@SuppressWarnings("rawtypes")
	Comparable begin = null;
	@SuppressWarnings("rawtypes")
	Comparable end = null;
	
	public <Y extends Comparable<? super Y>> PredicateBetween(Expression<? extends Y> check,
			Expression<? extends Y> beginExpr, Expression<? extends Y> endExpr) {
		this.check = check;
		this.beginExpr = beginExpr;
		this.endExpr = endExpr;
	}

	public <Y extends Comparable<? super Y>> PredicateBetween(Expression<? extends Y> check, Y begin, Y end) {
		this.check = check;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(check != null && check instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)check);
		if(beginExpr != null && beginExpr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)beginExpr);
		if(endExpr != null && endExpr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)endExpr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean evaluate(Evaluator eval) {
		Comparable<?> checkV = check.evaluate(eval);
		if (checkV == null) return false;
		if (begin == null) begin = beginExpr.evaluate(eval);
		if (end == null) end = endExpr.evaluate(eval);
			
		return isNegatedRVal( begin.compareTo(checkV) <= 0 && end.compareTo(checkV) >= 0);
	}

}

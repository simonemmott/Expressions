package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.K2ParameterExpression;

/**
 * The greater than predicate checks whether a comparable value is greater than another comparable value
 * 
 * @author simon
 *
 */
public class PredicateGreaterThan extends AbstractPredicate implements K2Predicate {
	
	private K2Expression<? extends Comparable<?>> expr1 = null;
	private K2Expression<? extends Comparable<?>> expr2 = null;
	private Comparable<?> comp = null;

	/**
	 * Create a greater than predicate to check whether the first comparable expression is greater then the second comparable expression
	 * @param expr1	The first expression
	 * @param expr2	The second expression
	 * @param <Y> The comparable type of this predicate
	 */
	public <Y extends Comparable<? super Y>> PredicateGreaterThan(K2Expression<? extends Y> expr1, K2Expression<? extends Y> expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Create a greater than predicate to check whether the comparable expression is greater then the comparable value
	 * @param expr	The expression
	 * @param comp	The value
	 * @param <Y> The comparable type of this predicate
	 */
	public <Y extends Comparable<? super Y>> PredicateGreaterThan(K2Expression<? extends Y> expr, Y comp) {
		this.expr1 = expr;
		this.comp = comp;
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(expr1 != null && expr1 instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)expr1);
		if(expr2 != null && expr2 instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)expr2);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Boolean evaluate(Evaluator eval) {
		if (expr1 == null || expr1.evaluate(eval) == null) return false;
		
		Comparable c1 = expr1.evaluate(eval);
		Comparable c2 = (expr2 != null) ? expr2.evaluate(eval): comp;
		
		
		return isNegatedRVal(c1.compareTo(c2) > 0);
	}

}

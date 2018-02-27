package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * The greater than or equals predicate checks whether the first comparable value is greater than or equal to the second comparable value
 * @author simon
 *
 */
public class PredicateGreaterThanOrEqualTo extends AbstractPredicate implements K2Predicate {
	
	private K2Expression<? extends Comparable<?>> expr1 = null;
	private K2Expression<? extends Comparable<?>> expr2 = null;
	private Comparable<?> comp = null;

	/**
	 * Create a greater than or equal to predicate to compare two expressions
	 * @param expr1	The first expression
	 * @param expr2	The second expression
	 * @param <Y> The comparable type of this predicate
	 */
	public <Y extends Comparable<? super Y>> PredicateGreaterThanOrEqualTo(K2Expression<? extends Y> expr1, K2Expression<? extends Y> expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Create a greater than or equal to predicate to compare an expression to a literal value
	 * @param expr	The expression
	 * @param comp	The literal value
	 * @param <Y> The comparable type of this predicate
	 */
	public <Y extends Comparable<? super Y>> PredicateGreaterThanOrEqualTo(K2Expression<? extends Y> expr, Y comp) {
		this.expr1 = expr;
		this.comp = comp;
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(expr1 != null && expr1 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr1);
		if(expr2 != null && expr2 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr2);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Boolean evaluate(Evaluator eval) {
		if (expr1 == null || expr1.evaluate(eval) == null) return false;
		
		Comparable c1 = expr1.evaluate(eval);
		Comparable c2 = (expr2 != null) ? expr2.evaluate(eval): comp;
		
		
		return isNegatedRVal(c1.compareTo(c2) >= 0);
	}

}

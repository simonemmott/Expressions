package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * The less than predicate checks whether the first comparable value is less than the second comparable value
 * 
 * @author simon
 *
 */
public class PredicateLessThan extends AbstractPredicate implements Predicate {
	
	private Expression<? extends Comparable<?>> expr1 = null;
	private Expression<? extends Comparable<?>> expr2 = null;
	private Comparable<?> comp = null;

	/**
	 * Create a less than predicate to check whether the value of the first expressed value is less than the second expressed value
	 * @param expr1		The first expression
	 * @param expr2		The second expression
	 * @param <Y> The comparable type of this predicate
	 */
	public <Y extends Comparable<? super Y>> PredicateLessThan(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Create a less than predicate to check whether the value of the expressed value is less than the literal value
	 * @param expr		The expression
	 * @param comp		The literal value
	 * @param <Y> The comparable type of this predicate
	 */
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

package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.K2ParameterExpression;

/**
 * The lt predicate checks whether the first numerical value is less than the second numerical value
 * @author simon
 *
 */
public class PredicateLT extends AbstractPredicate implements K2Predicate {
	
	private K2Expression<? extends Number> value1;
	private Object value2;

	/**
	 * Create a predicate to check whether the value of the first numerical expression is less than the value of the second numerical expression
	 * @param expr1		The first numerical expression
	 * @param expr2		The second numerical expression
	 */
	public PredicateLT(K2Expression<? extends Number> expr1, K2Expression<? extends Number> expr2) {
		value1 = expr1;
		value2 = expr2;
	}

	/**
	 * Create a predicate to check whether the value of the numerical expression is less than the value of the literal numerical value
	 * @param expr		The numerical expression
	 * @param num		The literal numerical value
	 */
	public PredicateLT(K2Expression<? extends Number> expr, Number num) {
		value1 = expr;
		value2 = num;
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		if (value1 == null || value1.evaluate(eval) == null || value2 == null) return false;
		
		if (value2 instanceof K2Expression) {
			
			K2Expression<?> value2Expr = (K2Expression<?>)value2;
			return isNegatedRVal(value1.evaluate(eval).doubleValue() < ((Number) value2Expr.evaluate(eval)).doubleValue());
		}
		
		return isNegatedRVal(value1.evaluate(eval).doubleValue() < ((Number)value2).doubleValue());
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(value1 != null && value1 instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)value1);
		if(value2 != null && value2 instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)value2);
	}

}

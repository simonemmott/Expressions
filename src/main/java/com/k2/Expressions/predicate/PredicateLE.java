package com.k2.Expressions.predicate;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * The le predicate checks whether the first numerical value is less than or equal to the second numerical value
 * 
 * @author simon
 *
 */
public class PredicateLE extends AbstractPredicate implements Predicate {
	
	private Expression<? extends Number> value1;
	private Object value2;

	/**
	 * Create a le predicate to check whether the value of the first expression is less than or equal to the value of the second numerical expression
	 * @param expr1	The first expression
	 * @param expr2	The second exprssion
	 */
	public PredicateLE(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		value1 = expr1;
		value2 = expr2;
	}

	/**
	 * Create a le predicate to check whether the value of the expression is less than or equal to the numeric value
	 * @param expr	The numeric expression
	 * @param num	The numeric literal value
	 */
	public PredicateLE(Expression<? extends Number> expr, Number num) {
		value1 = expr;
		value2 = num;
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		if (value1 == null || value1.evaluate(eval) == null || value2 == null) return false;
		
		if (value2 instanceof Expression) {
			
			Expression<?> value2Expr = (Expression<?>)value2;
			return isNegatedRVal(value1.evaluate(eval).doubleValue() <= ((Number) value2Expr.evaluate(eval)).doubleValue());
		}
		
		return isNegatedRVal(value1.evaluate(eval).doubleValue() <= ((Number)value2).doubleValue());
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(value1 != null && value1 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)value1);
		if(value2 != null && value2 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)value2);
	}

}

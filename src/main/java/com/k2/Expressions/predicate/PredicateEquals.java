package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * The equals predicate checks for equality between its arguments
 * 
 * @author simon
 *
 */
public class PredicateEquals extends AbstractPredicate implements K2Predicate {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private K2Expression<?> value1;
	private Object value2;

	/**
	 * Create an equality predicate to check for equality between two expressions
	 * @param expr1	The first expression
	 * @param expr2	The second expression
	 */
	public PredicateEquals(K2Expression<?> expr1, K2Expression<?> expr2) {
		value1 = expr1;
		value2 = expr2;
	}

	/**
	 * Create an equality predicate to check for equality between an expression and a literal value
	 * @param expr	The expression
	 * @param obj	The literal value
	 */
	public PredicateEquals(K2Expression<?> expr, Object obj) {
		value1 = expr;
		value2 = obj;
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(value1 != null && value1 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)value1);
		if(value2 != null && value2 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)value2);
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		if (value1 == null ||  value2 == null) return isNegatedRVal(false);
		Object v1 = value1.evaluate(eval);
		if (v1 == null) return isNegatedRVal(false);
		Object v2;
		
		if (value2 instanceof K2Expression) {
			v2 = ((K2Expression<?>)value2).evaluate(eval);
		} else {
			v2 = value2;
		}
		
		logger.trace("Equals: '{}' = '{}' [{}]", v1, v2, v1.equals(v2));
		
		return isNegatedRVal(v1.equals(v2));
	}

}

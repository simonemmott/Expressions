package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

public class PredicateEquals extends AbstractPredicate implements Predicate {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Expression<?> value1;
	private Object value2;

	public PredicateEquals(Expression<?> expr1, Expression<?> expr2) {
		value1 = expr1;
		value2 = expr2;
	}

	public PredicateEquals(Expression<?> expr, Object obj) {
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
		
		if (value2 instanceof Expression) {
			v2 = ((Expression<?>)value2).evaluate(eval);
		} else {
			v2 = value2;
		}
		
		logger.trace("Equals: '{}' = '{}' [{}]", v1, v2, v1.equals(v2));
		
		return isNegatedRVal(v1.equals(v2));
	}

}

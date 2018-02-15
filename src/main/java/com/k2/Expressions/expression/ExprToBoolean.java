package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.BooleanUtil;

/**
 * The to Boolean expression converts the given expression in to a Boolean expression
 * 
 * @author simon
 *
 */
public class ExprToBoolean extends AbstractExpression<Boolean> implements Expression<Boolean> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;

	/**
	 * Create a to Boolean expression to convert the given expression into a Boolean expression
	 * @param expr	The expression to convert to a Boolean expression
	 */
	public ExprToBoolean(Expression<?> expr) {
		super(Boolean.class);
		this.expr = expr;
	}

	@Override
	public Boolean evaluate(Evaluator eval) {

		Object value = expr.evaluate(eval);
		
		if (value == null) return null;
		
		return BooleanUtil.toBoolean(value);

	}

}

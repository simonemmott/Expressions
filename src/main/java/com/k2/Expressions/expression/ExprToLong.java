package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.LongUtil;

/**
 * The to Long expression converts the given expression in to a Long expression
 * 
 * @author simon
 *
 */
public class ExprToLong extends AbstractExpression<Long> implements Expression<Long> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;

	/**
	 * Create a to Long expression to convert the given expression into a Long expression
	 * @param expr	The expression to convert to a Long expression
	 */
	public ExprToLong(Expression<?> expr) {
		super(Long.class);
		this.expr = expr;
	}

	@Override
	public Long evaluate(Evaluator eval) {

		Object o = expr.evaluate(eval);
		
		if (o == null) return null;
 
		return LongUtil.toLong(o);
	}

}

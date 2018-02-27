package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.IntegerUtil;

/**
 * The to Integer expression converts the given expression in to a Integer expression
 * 
 * @author simon
 *
 */
public class ExprToInteger extends AbstractExpression<Integer> implements K2Expression<Integer> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<?> expr;

	/**
	 * Create a to Integer expression to convert the given expression into a Integer expression
	 * @param expr	The expression to convert to a Integer expression
	 */
	public ExprToInteger(K2Expression<?> expr) {
		super(Integer.class);
		this.expr = expr;
	}

	@Override
	public Integer evaluate(Evaluator eval) {

		Object o = expr.evaluate(eval);

		if (o == null) return null;
		 
		return IntegerUtil.toInteger(o);
	}

}

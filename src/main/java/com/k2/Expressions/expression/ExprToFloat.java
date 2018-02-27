package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.FloatUtil;

/**
 * The to Float expression converts the given expression in to a Float expression
 * 
 * @author simon
 *
 */
public class ExprToFloat extends AbstractExpression<Float> implements K2Expression<Float> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<?> expr;

	/**
	 * Create a to Float expression to convert the given expression into a Float expression
	 * @param expr	The expression to convert to a Float expression
	 */
	public ExprToFloat(K2Expression<?> expr) {
		super(Float.class);
		this.expr = expr;
	}

	@Override
	public Float evaluate(Evaluator eval) {

		Object o = expr.evaluate(eval);

		if (o == null) return null;
		 
		return FloatUtil.toFloat(o);
	}

}

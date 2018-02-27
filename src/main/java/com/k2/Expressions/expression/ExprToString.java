package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.StringUtil;

/**
 * The to String expression converts the given expression in to a String expression
 * 
 * @author simon
 *
 */
public class ExprToString extends AbstractExpression<String> implements K2Expression<String> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<?> expr;

	/**
	 * Create a to String expression to convert the given expression into a String expression
	 * @param expr	The expression to convert to a String expression
	 */
	public ExprToString(K2Expression<?> expr) {
		super(String.class);
		this.expr = expr;
	}

	@Override
	public String evaluate(Evaluator eval) {

		Object value = expr.evaluate(eval);
		
		if (value == null) return null;
		
		return StringUtil.toString(value);

	}

}

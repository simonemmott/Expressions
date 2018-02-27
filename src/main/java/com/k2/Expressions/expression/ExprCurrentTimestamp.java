package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * This implementation of the Expression interface provides the current timestamp. Other current date, current time or current
 * timestamp expressions will return values derived from the date of the first instance of current * expressions to be 
 * evaluated consequently all current * expressions will return consistent value for the same evaluator
 * 
 * @author simon
 *
 */
public class ExprCurrentTimestamp extends AbstractExpression<Timestamp> implements K2Expression<Timestamp> {
	
	/**
	 * Create the current timestamp expression
	 */
	public ExprCurrentTimestamp() {
		super(Timestamp.class);
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Timestamp evaluate(Evaluator eval) {
		return eval.getCurrentTime().getTimestamp();
	}


}

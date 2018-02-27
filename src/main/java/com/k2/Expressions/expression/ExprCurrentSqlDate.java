package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * This implementation of the Expression interface provides the current date. Other current date, current time or current
 * timestamp expressions will return values derived from the date of the first instance of current * expressions to be 
 * evaluated consequently all current * expressions will return consistent value for the same evaluator
 * 
 * @author simon
 *
 */
public class ExprCurrentSqlDate extends AbstractExpression<Date> implements K2Expression<Date> {
	
	/**
	 * Create an instance of the current date expression
	 */
	public ExprCurrentSqlDate() {
		super(Date.class);
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Date evaluate(Evaluator eval) {
		return eval.getCurrentTime().getDate();
	}


}

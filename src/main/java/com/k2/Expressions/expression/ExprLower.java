package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The lower expression converts a string into lower case
 * 
 * @author simon
 *
 */
public class ExprLower extends AbstractExpression<String> implements K2Expression<String> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<String> string;

	/**
	 * Create an expression to convert a string to lower case
	 * @param string		The string to convert to lower case
	 */
	public ExprLower(K2Expression<String> string) {
		super(String.class);
		this.string = string;
	}

	@Override
	public String evaluate(Evaluator eval) {

		
		if (string==null || string.evaluate(eval)==null) return null;
		String s = string.evaluate(eval);
		logger.trace("Lower '{}' is '{}'", s, s.toLowerCase());
		return s.toLowerCase();
	}

}

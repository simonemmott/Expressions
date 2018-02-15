package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The upper expression converts a string to upper case
 * 
 * @author simon
 *
 */
public class ExprUpper extends AbstractExpression<String> implements Expression<String> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> string;

	/**
	 * Create an expression to convert the given string to upper case
	 * @param string		The string expression to convert to upper case
	 */
	public ExprUpper(Expression<String> string) {
		super(String.class);
		this.string = string;
	}

	@Override
	public String evaluate(Evaluator eval) {

		
		if (string==null) return null;
		String s = string.evaluate(eval);
		if (s == null) return null;
		logger.trace("Upper '{}' is '{}'", s, s.toUpperCase());
		return s.toUpperCase();
	}

}

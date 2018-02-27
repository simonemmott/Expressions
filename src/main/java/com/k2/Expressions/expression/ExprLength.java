package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The Length expression calculates the length in characters of a string
 * 
 * @author simon
 *
 */
public class ExprLength extends AbstractExpression<Integer> implements K2Expression<Integer> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<String> string;

	/**
	 * Create the length expression for the given string expression
	 * @param string		The expression providing the string for which the length is required
	 */
	public ExprLength(K2Expression<String> string) {
		super(Integer.class);
		this.string = string;
	}

	@Override
	public Integer evaluate(Evaluator eval) {

		if (string==null) return 0;
		String value = string.evaluate(eval);
		logger.trace("Length: {} is {}", 
				value,
				value.length());
		return (value.length());
	}

}

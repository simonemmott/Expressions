package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The locate expression identifies the location of one string within another
 * 
 * @author simon
 *
 */
public class ExprLocate extends AbstractExpression<Integer> implements K2Expression<Integer> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<String> stringExp;
	K2Expression<String> patternExp;
	K2Expression<Integer> fromExp;
	String pattern;
	int from = 0;

	/**
	 * Create a location expression that locates the string returned by the pattern string expression in the string returned by
	 * the string expression
	 * @param string		The string expression
	 * @param pattern	The pattern expression
	 */
	public ExprLocate(K2Expression<String> string, K2Expression<String> pattern) {
		super(Integer.class);
		this.stringExp = string;
		this.patternExp = pattern;
	}

	/**
	 * Create a location expression that locates the pattern string expression in the string returned by
	 * the string expression
	 * @param string		The string expression
	 * @param pattern	The pattern expression
	 */
	public ExprLocate(K2Expression<String> string, String pattern) {
		super(Integer.class);
		this.stringExp = string;
		this.pattern = pattern;
	}

	/**
	 * Create a location expression that locates the string returned by the pattern string expression in the string returned by
	 * the string expression starting from a given location
	 * @param string		The string expression
	 * @param pattern	The pattern expression
	 * @param from		The location within the string to start searching for the pattern
	 */
	public ExprLocate(K2Expression<String> string, K2Expression<String> pattern, K2Expression<Integer> from) {
		super(Integer.class);
		this.stringExp = string;
		this.patternExp = pattern;
		this.fromExp = from;
	}

	/**
	 * Create a location expression that locates the pattern string expression in the string returned by
	 * the string expression starting from a given location
	 * @param string		The string expression
	 * @param pattern	The pattern expression
	 * @param from		The location within the string to start searching for the pattern
	 */
	public ExprLocate(K2Expression<String> string, String pattern, int from) {
		super(Integer.class);
		this.stringExp = string;
		this.pattern = pattern;
		this.from = (from < 0) ? 0: from;
	}

	@Override
	public Integer evaluate(Evaluator eval) {
		
		String s = stringExp.evaluate(eval);
		String p = (patternExp == null) ? pattern: patternExp.evaluate(eval);
		int f = from;
		if (fromExp != null) {
			Integer fromExpValue = fromExp.evaluate(eval);
			if (fromExpValue != null && fromExpValue.intValue() >= 0) {
				f = fromExpValue.intValue();
			}
		}
		int i = s.indexOf(p, f)+1;
		logger.trace("Locate '{}' in '{}' after position {}{}", p, s, f, (i==0) ? "": " Found at "+i);
		return s.indexOf(p, f)+1;
			
	}


}

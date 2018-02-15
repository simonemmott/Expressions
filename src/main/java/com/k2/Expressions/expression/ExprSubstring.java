package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The substring expression extracts a substring from a given string
 * 
 * @author simon
 *
 */
public class ExprSubstring extends AbstractExpression<String> implements Expression<String> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> stringExp = null;
	Expression<Integer> fromExp = null;
	Integer from = null;
	Expression<Integer> lengthExp = null;
	Integer length = null;

	/**
	 * Create a substring expression to extract the substring from the given string from the given position
	 * @param string		The string from which to extract the substring
	 * @param from		The location of the beginning of the substring
	 */
	public ExprSubstring(Expression<String> string, Expression<Integer> from) {
		super(String.class);
		this.stringExp = string;
		this.fromExp = from;
	}

	/**
	 * Create a substring expression to extract the substring from the given string from the given position
	 * @param string		The string from which to extract the substring
	 * @param from		The location of the beginning of the substring
	 */
	public ExprSubstring(Expression<String> string, int from) {
		super(String.class);
		this.stringExp = string;
		this.from = from;
	}

	/**
	 * Create a substring expression to extract the substring from the given string from the given position with the given length
	 * @param string		The string from which to extract the substring
	 * @param from		The location of the beginning of the substring
	 * @param length		The length of the substring to extract
	 */
	public ExprSubstring(Expression<String> string, Expression<Integer> from, Expression<Integer> length) {
		super(String.class);
		this.stringExp = string;
		this.fromExp = from;
		this.lengthExp = length;
	}

	/**
	 * Create a substring expression to extract the substring from the given string from the given position with the given length
	 * @param string		The string from which to extract the substring
	 * @param from		The location of the beginning of the substring
	 * @param length		The length of the substring to extract
	 */
	public ExprSubstring(Expression<String> string, int from, int length) {
		super(String.class);
		this.stringExp = string;
		this.from = from;
		this.length = length;
	}

	@Override
	public String evaluate(Evaluator eval) {
		String s = stringExp.evaluate(eval);
		int f = (fromExp == null) ? from: fromExp.evaluate(eval);
		Integer l = (lengthExp == null) ? length: lengthExp.evaluate(eval);
		
		logger.trace("Substring: '{}' from: {} length: {}", s, f, l);
		
		if (s == null) return null;
		if (f-1 < 0) {
			logger.trace("Before start: returning '{}'", s);
			return s;
		} else if (f-1 > s.length()) {
			logger.trace("After end: returning '{}'", "");
			return "";
		} else if (l == null || f+l-1 > s.length()) {
			logger.trace("Select to end: returning {}", s.substring(f-1));
			return s.substring(f-1);
		} else {
			logger.trace("Select between {} and {}: returning {}", f-1, f+l-1, s.substring(f-1));
			return s.substring(f-1, f+l-1);
		}
	}

}

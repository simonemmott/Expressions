package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import javax.persistence.criteria.CriteriaBuilder.Trimspec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.StringUtil;

/**
 * The Trim expression trims leading and or trailing characters from a string
 * 
 * @author simon
 *
 */
public class ExprTrim extends AbstractExpression<String> implements Expression<String> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> string;
	Expression<Character> trimCharExp;
	char trimChar = ' ';
	Trimspec trimSpec = Trimspec.BOTH;

	/**
	 * Create a trim expression to to trim the leading and trailing spaces from a string
	 * @param string		The string to trim
	 */
	public ExprTrim(Expression<String> string) {
		super(String.class);
		this.string = string;
	}

	/**
	 * Create a trim expression to to trim the leading and trailing characters from a string
	 * @param trimChar	The character to trim from the string
	 * @param string		The string to trim
	 */
	public ExprTrim(Expression<Character> trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimCharExp = trimChar;
	}

	/**
	 * Create a trim expression to to trim the leading and trailing characters from a string
	 * @param trimChar	The character to trim from the string
	 * @param string		The string to trim
	 */
	public ExprTrim(char trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimChar = trimChar;
	}

	/**
	 * Create a trim expression to to trim the leading and/or trailing characters from a string
	 * @param trimSpec	The enumeration to identify whether to trim lead character, trailing characters or both
	 * @param trimChar	The character to trim from the string
	 * @param string		The string to trim
	 */
	public ExprTrim(Trimspec trimSpec, Expression<Character> trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimCharExp = trimChar;
		this.trimSpec = trimSpec;
	}

	/**
	 * Create a trim expression to to trim the leading and/or trailing characters from a string
	 * @param trimSpec	The enumeration to identify whether to trim lead character, trailing characters or both
	 * @param trimChar	The character to trim from the string
	 * @param string		The string to trim
	 */
	public ExprTrim(Trimspec trimSpec, char trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimChar = trimChar;
		this.trimSpec = trimSpec;
	}

	/**
	 * Create a trim expression to to trim the leading and/or trailing spaces from a string
	 * @param trimSpec	The enumeration to identify whether to trim lead character, trailing characters or both
	 * @param string		The string to trim
	 */
	public ExprTrim(Trimspec trimSpec, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimSpec = trimSpec;
	}

	public String evaluate(Evaluator eval) {

		String s = string.evaluate(eval);
		char tc = (trimCharExp == null) ? trimChar: trimCharExp.evaluate(eval);
		
		return StringUtil.trim(trimSpec, tc, s);

	}

}

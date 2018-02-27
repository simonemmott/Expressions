package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.K2ParameterExpression;

/**
 * The like predicate checks whether the given string matches the given pattern
 * @author simon
 *
 */
public class PredicateLike extends AbstractPredicate implements K2Predicate {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private K2Expression<String> stringExp;
	private K2Expression<String> patternExp;
	private K2Expression<Character> escapeCharExp;
	private String pattern;
	private char escapeChar = '\\';

	/**
	 * Create a predicate to check whether the value of the first expression matches the pattern supplied by the second expression
	 * @param stringExp		The string expression
	 * @param patternExp		The pattern expression
	 */
	public PredicateLike(K2Expression<String> stringExp, K2Expression<String> patternExp) {
		this.stringExp = stringExp;
		this.patternExp = patternExp;
	}

	/**
	 * Create a predicate to check whether the value of the first expression matches the literal string pattern
	 * @param stringExp		The string expression
	 * @param pattern		The pattern expression
	 */
	public PredicateLike(K2Expression<String> stringExp, String pattern) {
		this.stringExp = stringExp;
		this.pattern = pattern;
	}

	/**
	 * Create a predicate to check whether the value of the first expression matches the pattern supplied by the second expression using the escape character supplied
	 * by the last expression
	 * @param stringExp		The string expression
	 * @param patternExp		The pattern expression
	 * @param escapeCharExp	The escape character expression
	 */
	public PredicateLike(K2Expression<String> stringExp, K2Expression<String> patternExp, K2Expression<Character> escapeCharExp) {
		this.stringExp = stringExp;
		this.patternExp = patternExp;
		this.escapeCharExp = escapeCharExp;
	}

	/**
	 * Create a predicate to check whether the value of the first expression matches the pattern supplied by the second expression using the literal escape character
	 * @param stringExp		The string expression
	 * @param patternExp		The pattern expression
	 * @param escapeChar		The escape character
	 */
	public PredicateLike(K2Expression<String> stringExp, K2Expression<String> patternExp, char escapeChar) {
		this.stringExp = stringExp;
		this.patternExp = patternExp;
		this.escapeChar = escapeChar;
	}

	/**
	 * Create a predicate to check whether the value of the first expression matches the literal string pattern  using the escape character supplied
	 * by the last expression
	 * @param stringExp		The string expression
	 * @param pattern		The literal pattern
	 * @param escapeCharExp	The escape character expression
	 */
	public PredicateLike(K2Expression<String> stringExp, String pattern, K2Expression<Character> escapeCharExp) {
		this.stringExp = stringExp;
		this.pattern = pattern;
		this.escapeCharExp = escapeCharExp;
	}

	/**
	 * Create a predicate to check whether the value of the first expression matches the literal pattern using the literal escape character
	 * @param stringExp		The string expression
	 * @param pattern		The literal pattern
	 * @param escapeChar		The literal escape character
	 */
	public PredicateLike(K2Expression<String> stringExp, String pattern, char escapeChar) {
		this.stringExp = stringExp;
		this.pattern = pattern;
		this.escapeChar = escapeChar;
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		
		char c = (escapeCharExp == null) ? escapeChar : escapeCharExp.evaluate(eval);
		if (c != '\'') logger.warn("Custom escape characters '{}'not supported, using '{}' instead", 
				c,
				escapeChar);
		
		Pattern p = Pattern.compile(((patternExp == null) ? pattern : patternExp.evaluate(eval)).replaceAll("%", "(.*)"));
		Matcher m = p.matcher(stringExp.evaluate(eval));

		logger.trace("Matching '{}' in '{}'{}", 
				p.pattern(),
				stringExp.evaluate(eval),
				(m.matches() ? " [Matched]": ""));
		
		return isNegatedRVal(m.matches());
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if(stringExp != null && stringExp instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)stringExp);
		if(patternExp != null && patternExp instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)patternExp);
		if(escapeCharExp != null && escapeCharExp instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)escapeCharExp);
	}

}

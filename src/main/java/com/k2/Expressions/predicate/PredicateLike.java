package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

public class PredicateLike extends AbstractPredicate implements Predicate {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Expression<String> stringExp;
	private Expression<String> patternExp;
	private Expression<Character> escapeCharExp;
	private String pattern;
	private char escapeChar = '\\';

	public PredicateLike(Expression<String> stringExp, Expression<String> patternExp) {
		this.stringExp = stringExp;
		this.patternExp = patternExp;
	}

	public PredicateLike(Expression<String> stringExp, String pattern) {
		this.stringExp = stringExp;
		this.pattern = pattern;
	}

	public PredicateLike(Expression<String> stringExp, Expression<String> patternExp, Expression<Character> escapeCharExp) {
		this.stringExp = stringExp;
		this.patternExp = patternExp;
		this.escapeCharExp = escapeCharExp;
	}

	public PredicateLike(Expression<String> stringExp, Expression<String> patternExp, char escapeChar) {
		this.stringExp = stringExp;
		this.patternExp = patternExp;
		this.escapeChar = escapeChar;
	}

	public PredicateLike(Expression<String> stringExp, String pattern, Expression<Character> escapeCharExp) {
		this.stringExp = stringExp;
		this.pattern = pattern;
		this.escapeCharExp = escapeCharExp;
	}

	public PredicateLike(Expression<String> stringExp, String pattern, char escapeChar) {
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
		if(stringExp != null && stringExp instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)stringExp);
		if(patternExp != null && patternExp instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)patternExp);
		if(escapeCharExp != null && escapeCharExp instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)escapeCharExp);
	}

}

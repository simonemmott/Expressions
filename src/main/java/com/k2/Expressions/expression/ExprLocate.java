package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprLocate extends AbstractExpression<Integer> implements Expression<Integer> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> stringExp;
	Expression<String> patternExp;
	Expression<Integer> fromExp;
	String pattern;
	int from = 0;

	public ExprLocate(Expression<String> string, Expression<String> pattern) {
		super(Integer.class);
		this.stringExp = string;
		this.patternExp = pattern;
	}

	public ExprLocate(Expression<String> string, String pattern) {
		super(Integer.class);
		this.stringExp = string;
		this.pattern = pattern;
	}

	public ExprLocate(Expression<String> string, Expression<String> pattern, Expression<Integer> from) {
		super(Integer.class);
		this.stringExp = string;
		this.patternExp = pattern;
		this.fromExp = from;
	}

	public ExprLocate(Expression<String> string, String pattern, int from) {
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

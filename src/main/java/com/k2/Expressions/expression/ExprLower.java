package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprLower extends AbstractExpression<String> implements Expression<String> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> string;

	public ExprLower(Expression<String> string) {
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

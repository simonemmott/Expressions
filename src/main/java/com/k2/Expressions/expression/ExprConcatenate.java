package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprConcatenate extends AbstractExpression<String> implements Expression<String> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> stringExp1 = null;
	Expression<String> stringExp2 = null;
	String string1 = null;
	String string2 = null;

	public ExprConcatenate(Expression<String> stringExp1, Expression<String> stringExp2) {
		super(String.class);
		this.stringExp1 = stringExp1;
		this.stringExp2 = stringExp2;
	}

	public ExprConcatenate(Expression<String> stringExp1, String string2) {
		super(String.class);
		this.stringExp1 = stringExp1;
		this.string2 = string2;
	}

	public ExprConcatenate(String string1, Expression<String> stringExp2) {
		super(String.class);
		this.string1 = string1;
		this.stringExp2 = stringExp2;
	}

	@Override
	public String evaluate(Evaluator eval) {
		String s1 = string1;
		String s2 = string2;
		if (s1 == null) s1 = stringExp1.evaluate(eval);
		if (s2 == null) s2 = stringExp2.evaluate(eval);
		
		logger.trace("Concatenate: {} + {} = {}", s1, s2, s1+s2);
		
		return s1+s2;
	}

}

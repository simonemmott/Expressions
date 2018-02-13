package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import javax.persistence.criteria.CriteriaBuilder.Trimspec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.StringUtil;

public class ExprTrim extends AbstractExpression<String> implements Expression<String> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> string;
	Expression<Character> trimCharExp;
	char trimChar = ' ';
	Trimspec trimSpec = Trimspec.BOTH;

	public ExprTrim(Expression<String> string) {
		super(String.class);
		this.string = string;
	}

	public ExprTrim(Expression<Character> trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimCharExp = trimChar;
	}

	public ExprTrim(char trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimChar = trimChar;
	}

	public ExprTrim(Trimspec trimSpec, Expression<Character> trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimCharExp = trimChar;
		this.trimSpec = trimSpec;
	}

	public ExprTrim(Trimspec trimSpec, char trimChar, Expression<String> string) {
		super(String.class);
		this.string = string;
		this.trimChar = trimChar;
		this.trimSpec = trimSpec;
	}

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

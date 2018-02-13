package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.StringUtil;

public class ExprToString extends AbstractExpression<String> implements Expression<String> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;

	public ExprToString(Expression<?> expr) {
		super(String.class);
		this.expr = expr;
	}

	@Override
	public String evaluate(Evaluator eval) {

		Object value = expr.evaluate(eval);
		
		if (value == null) return null;
		
		return StringUtil.toString(value);

	}

}

package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.BooleanUtil;

public class ExprToBoolean extends AbstractExpression<Boolean> implements Expression<Boolean> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;

	public ExprToBoolean(Expression<?> expr) {
		super(Boolean.class);
		this.expr = expr;
	}

	@Override
	public Boolean evaluate(Evaluator eval) {

		Object value = expr.evaluate(eval);
		
		if (value == null) return null;
		
		return BooleanUtil.toBoolean(value);

	}

}

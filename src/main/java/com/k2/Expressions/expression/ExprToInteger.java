package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.IntegerUtil;

public class ExprToInteger extends AbstractExpression<Integer> implements Expression<Integer> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;

	public ExprToInteger(Expression<?> expr) {
		super(Integer.class);
		this.expr = expr;
	}

	@Override
	public Integer evaluate(Evaluator eval) {

		Object o = expr.evaluate(eval);

		if (o == null) return null;
		 
		return IntegerUtil.toInteger(o);
	}

}

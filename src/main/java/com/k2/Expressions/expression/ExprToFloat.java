package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.FloatUtil;

public class ExprToFloat extends AbstractExpression<Float> implements Expression<Float> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;

	public ExprToFloat(Expression<?> expr) {
		super(Float.class);
		this.expr = expr;
	}

	@Override
	public Float evaluate(Evaluator eval) {

		Object o = expr.evaluate(eval);

		if (o == null) return null;
		 
		return FloatUtil.toFloat(o);
	}

}

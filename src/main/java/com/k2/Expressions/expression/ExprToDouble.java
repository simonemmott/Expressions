package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.DoubleUtil;

public class ExprToDouble extends AbstractExpression<Double> implements Expression<Double> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;

	public ExprToDouble(Expression<?> expr) {
		super(Double.class);
		this.expr = expr;
	}

	@Override
	public Double evaluate(Evaluator eval) {

		Object o = expr.evaluate(eval);

		if (o == null) return null;
		 
		return DoubleUtil.toDouble(o);
	}

}

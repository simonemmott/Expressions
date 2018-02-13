package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.DoubleUtil;

public class ExprSqrt extends AbstractExpression<Double> implements Expression<Double> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<? extends Number> num;

	public ExprSqrt(Expression<? extends Number> num) {
		super(Double.class);
		this.num = num;
	}

	@Override
	public Double evaluate(Evaluator eval) {

		Object n = num.evaluate(eval);
		if (n == null) return (Double)null;
		
		if (n.getClass().equals(Integer.class)) {
			return DoubleUtil.round(Math.sqrt(new Double((Integer)n)),12);
		} else if (n.getClass().equals(Long.class)) {
			return DoubleUtil.round(Math.sqrt(new Double((Long)n)),12);
		} else if (n.getClass().equals(Float.class)) {
			return DoubleUtil.round(Math.sqrt(new Double((Float)n)),12);
		} else if (n.getClass().equals(Double.class)) {
			return DoubleUtil.round(Math.sqrt((Double)n),12);
		} else 
		return (Double)null;
	}

}

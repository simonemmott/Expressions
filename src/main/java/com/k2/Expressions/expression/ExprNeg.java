package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprNeg<N extends Number> extends AbstractExpression<N> implements Expression<N> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<N> num;

	public ExprNeg(Expression<N> num) {
		super(num.getJavaType());
		this.num = num;
	}

	@SuppressWarnings("unchecked")
	@Override
	public N evaluate(Evaluator eval) {

		N n = num.evaluate(eval);
		if (n.getClass().equals(Integer.class)) {
			return (N) new Integer(n.intValue() * -1);
		} else if (n.getClass().equals(Long.class)) {
			return (N) new Long(n.longValue() * -1);
		} else if (n.getClass().equals(Float.class)) {
			return (N) new Float(n.floatValue() * -1);
		} else if (n.getClass().equals(Double.class)) {
			return (N) new Double(n.doubleValue() * -1);
		} else 
		return n;
	}

}

package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprToBigInteger extends AbstractExpression<BigInteger> implements Expression<BigInteger> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<? extends Number> num;

	public ExprToBigInteger(Expression<? extends Number> num) {
		super(BigInteger.class);
		this.num = num;
	}

	@Override
	public BigInteger evaluate(Evaluator eval) {

		Number n = num.evaluate(eval);

		if (n.getClass().equals(Integer.class)) {
			return BigInteger.valueOf(n.intValue());
		} else if (n.getClass().equals(Long.class)) {
			return BigInteger.valueOf(n.longValue());
		} else if (n.getClass().equals(Float.class)) {
			return BigInteger.valueOf(n.longValue());
		} else if (n.getClass().equals(Double.class)) {
			return BigInteger.valueOf(n.longValue());
		} else 
		return (BigInteger)null;
	}

}

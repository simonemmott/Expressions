package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The to big integer expression converts the given numerical expression in to a BigInteger
 * 
 * @author simon
 *
 */
public class ExprToBigInteger extends AbstractExpression<BigInteger> implements K2Expression<BigInteger> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<? extends Number> num;

	/**
	 * Create a toBigInteger expression for the given numerical expression
	 * @param num	The numerical expression to convert into a BigIntger
	 */
	public ExprToBigInteger(K2Expression<? extends Number> num) {
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

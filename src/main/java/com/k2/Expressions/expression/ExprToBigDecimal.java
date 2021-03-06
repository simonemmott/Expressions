package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The to big decimal expression converts the given numerical expression in to a BigDecimal
 * 
 * @author simon
 *
 */
public class ExprToBigDecimal extends AbstractExpression<BigDecimal> implements K2Expression<BigDecimal> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<? extends Number> num;

	/**
	 * Create a toBigDcimal expression for the given numerical expression
	 * @param num	The numerical expression to convert into a BigDecimal
	 */
	public ExprToBigDecimal(K2Expression<? extends Number> num) {
		super(BigDecimal.class);
		this.num = num;
	}

	@Override
	public BigDecimal evaluate(Evaluator eval) {

		Number n = num.evaluate(eval);

		if (n.getClass().equals(Integer.class)) {
			return BigDecimal.valueOf(n.intValue());
		} else if (n.getClass().equals(Long.class)) {
			return BigDecimal.valueOf(n.longValue());
		} else if (n.getClass().equals(Float.class)) {
			return BigDecimal.valueOf(n.floatValue());
		} else if (n.getClass().equals(Double.class)) {
			return BigDecimal.valueOf(n.doubleValue());
		} else 
		return (BigDecimal)null;
	}

}

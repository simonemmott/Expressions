package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

/**
 * The negative expression returns the negative value of the numerical value it receives
 * 
 * @author simon
 *
 * @param <N>	The numeric type of this expression
 */
public class ExprNeg<N extends Number> extends AbstractExpression<N> implements K2Expression<N> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<N> num;

	/**
	 * Create a negative expression for the given numerical expression
	 * @param num	The numerical expression to convert to a numerical value
	 */
	public ExprNeg(K2Expression<N> num) {
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

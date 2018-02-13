package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.DoubleUtil;

public class ExprDifference<N extends Number> extends AbstractExpression<N> implements Expression<N> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<? extends N> numExp1 = null;
	Expression<? extends N> numExp2 = null;
	Number num1 = null;
	Number num2 = null;

	public ExprDifference(Expression<? extends N> numExp1, Expression<? extends N> numExp2) {
		super(numExp1.getJavaType());
		this.numExp1 = numExp1;
		this.numExp2 = numExp2;
	}

	public ExprDifference(Expression<? extends N> numExp1, N num2) {
		super(numExp1.getJavaType());
		this.numExp1 = numExp1;
		this.num2 = num2;
	}

	public ExprDifference(N num1, Expression<? extends N> numExp2) {
		super(numExp2.getJavaType());
		this.num1 = num1;
		this.numExp2 = numExp2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public N evaluate(Evaluator eval) {
		Number n1 = num1;
		Number n2 = num2;
		if (n1 == null) n1 = numExp1.evaluate(eval);
		if (n2 == null) n2 = numExp2.evaluate(eval);
		
		Double d = DoubleUtil.round(n1.doubleValue()-n2.doubleValue(), 12);

		logger.trace("Difference: {} - {} = {}", n1, n2, d);

		if (this.getJavaType().equals(Integer.class)) {
			return (N) new Integer(d.intValue());
		} else if (this.getJavaType().equals(Long.class)) {
			return (N) new Long(d.longValue());
		} else if (this.getJavaType().equals(Float.class)) {
			return (N) new Float(d.floatValue());
		} else if (this.getJavaType().equals(Double.class)) {
			return (N) d;
		} 
		
		return null;
	}

}

package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprProd<N extends Number> extends AbstractExpression<N> implements Expression<N> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<? extends N> num1Exp = null;
	Expression<? extends N> num2Exp = null;
	N num1 = null;
	N num2 = null;

	public ExprProd(Expression<? extends N> num1, Expression<? extends N> num2) {
		super(num1.getJavaType());
		this.num1Exp = num1;
		this.num2Exp = num2;
	}

	public ExprProd(Expression<? extends N> num1, N num2) {
		super(num1.getJavaType());
		this.num1Exp = num1;
		this.num2 = num2;
	}

	public ExprProd(N num1, Expression<? extends N> num2) {
		super(num2.getJavaType());
		this.num1 = num1;
		this.num2Exp = num2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public N evaluate(Evaluator eval) {

		N n1 = (num1Exp == null) ? num1: num1Exp.evaluate(eval);
		N n2 = (num2Exp == null) ? num2: num2Exp.evaluate(eval);
		
		if (n1 == null || n2 == null) return (N)null;
		Double d = n1.doubleValue() * n2.doubleValue();
		
		if (getJavaType().equals(Integer.class)) {
			return (N) new Integer(d.intValue());
		} else if (getJavaType().equals(Long.class)) {
			return (N) new Long(d.longValue());
		} else if (getJavaType().equals(Float.class)) {
			return (N) new Float(d.floatValue());
		} else if (getJavaType().equals(Double.class)) {
			return (N) d;
		} else 
		return (N)null;
	}

}

package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprCurrentDate extends AbstractExpression<Date> implements Expression<Date> {
	
	public ExprCurrentDate() {
		super(Date.class);
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Date evaluate(Evaluator eval) {
		return eval.getCurrentTime().getCurrentDate();
	}


}

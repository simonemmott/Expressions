package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.sql.Time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprCurrentTime extends AbstractExpression<Time> implements Expression<Time> {
	
	public ExprCurrentTime() {
		super(Time.class);
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Time evaluate(Evaluator eval) {
		return eval.getCurrentTime().getCurrentTime();
	}


}

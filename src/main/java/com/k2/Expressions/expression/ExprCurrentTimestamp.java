package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;

public class ExprCurrentTimestamp extends AbstractExpression<Timestamp> implements Expression<Timestamp> {
	
	public ExprCurrentTimestamp() {
		super(Timestamp.class);
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Timestamp evaluate(Evaluator eval) {
		return eval.getCurrentTime().getCurrentTimestamp();
	}


}

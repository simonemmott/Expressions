package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.DateUtil;
import com.k2.Util.StringUtil;

public class ExprToDate extends AbstractExpression<Date> implements Expression<Date> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;
	Expression<String> formatExpr;
	String format;

	public ExprToDate(Expression<?> expr) {
		super(Date.class);
		this.expr = expr;
	}

	public ExprToDate(Expression<?> expr, Expression<String> format) {
		super(Date.class);
		this.expr = expr;
		this.formatExpr = format;
	}

	public ExprToDate(Expression<?> expr, String format) {
		super(Date.class);
		this.expr = expr;
		this.format = format;
	}

	@Override
	public Date evaluate(Evaluator eval) {

		Object o = expr.evaluate(eval);
		
		if (o == null) return null;

		if (format != null || (formatExpr != null && formatExpr.evaluate(eval) != null)) {
			return StringUtil.toDate(StringUtil.toString(o), (format == null) ? formatExpr.evaluate(eval) : format);
		} else {
			 
			return DateUtil.toDate(o);
		}
	}

}

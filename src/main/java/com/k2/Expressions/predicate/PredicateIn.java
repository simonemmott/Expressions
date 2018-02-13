package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.*;

public class PredicateIn extends AbstractPredicate implements Predicate {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<?> expr;
	Object[] objects = null;
	Expression<?>[] expressions = null;
	Collection<?> collection = null;
	Expression<Collection<?>> collectionExpr = null;
	
	public PredicateIn(Expression<?> expr, Object[] objects) {
		logger.trace("in: list of objects");
		this.expr = expr;
		this.objects = objects;
	}

	public PredicateIn(Expression<?> expr, Expression<?>... expressions) {
		logger.trace("in: list of expressions");
		this.expr = expr;
		this.expressions = expressions;
	}

	public PredicateIn(Expression<?> expr, Collection<?> collection) {
		logger.trace("in: collection");
		this.expr = expr;
		this.collection = collection;
	}
	
	public PredicateIn(Expression<?> expr, Expression<Collection<?>> collectionExpr) {
		logger.trace("in: collection expression");
		this.expr = expr;
		this.collectionExpr = collectionExpr;
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if (expr instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)expr);
		if (objects != null) {
			return;
		} else if (expressions != null) {
			for (Expression<?> e : expressions) if (e instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)e);
		} else if (collection != null) {
			for (Object o : collection) if (o instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)o);
		} else if (collectionExpr != null) {
			for (Object o : collectionExpr.evaluate(eval)) if (o instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)o);
		}
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		Object value = expr.evaluate(eval);
		if (value == null) return false;
		if (isNegated()) {
			return !evaluateIn(value, eval);
		} else {
			return evaluateIn(value, eval);
		}
	}
	
	private boolean evaluateIn(Object value, Evaluator eval) {
		if (value == null) return false;

		if (objects != null) {
			for (Object o : objects) if (value.equals(o)) return true;
		} else if (expressions != null) {
			for (Expression<?> e : expressions) if (value.equals(e.evaluate(eval))) return true;
		} else if (collection != null) {
			for (Object o : collection) if (value.equals(o)) return true;
		} else if (collectionExpr != null) {
			for (Object o : collectionExpr.evaluate(eval)) if (value.equals(o)) return true;
		}

		return false;
	}



}

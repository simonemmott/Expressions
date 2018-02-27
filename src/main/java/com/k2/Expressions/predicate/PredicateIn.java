package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.*;

/**
 * The in predicate checks whether the first value is in the subsquent values
 * @author simon
 *
 */
public class PredicateIn extends AbstractPredicate implements K2Predicate {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Expression<?> expr;
	Object[] objects = null;
	K2Expression<?>[] expressions = null;
	Collection<?> collection = null;
	K2Expression<Collection<?>> collectionExpr = null;
	
	/**
	 * Create an in predicate to check whether the value of the expression is in the array of objects
	 * @param expr	The expression
	 * @param objects	The array of objects
	 */
	public PredicateIn(K2Expression<?> expr, Object[] objects) {
		logger.trace("in: list of objects");
		this.expr = expr;
		this.objects = objects;
	}

	/**
	 * Create an in predicate to check whether the value of the expression is in the values of the array of expressions
	 * @param expr		The expression to check
	 * @param expressions	The array of expressions
	 */
	public PredicateIn(K2Expression<?> expr, K2Expression<?>... expressions) {
		logger.trace("in: list of expressions");
		this.expr = expr;
		this.expressions = expressions;
	}

	/**
	 * Create an in predicate to check whether the value of the expression is in a collection
	 * @param expr	The expression
	 * @param collection		The collection
	 */
	public PredicateIn(K2Expression<?> expr, Collection<?> collection) {
		logger.trace("in: collection");
		this.expr = expr;
		this.collection = collection;
	}
	
	/**
	 * Create an in predicate to check whether the value of the expression in the the expressed collection
	 * @param expr	The expression to check
	 * @param collectionExpr		The expression supplying a collection
	 */
	public PredicateIn(K2Expression<?> expr, K2Expression<Collection<?>> collectionExpr) {
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
			for (K2Expression<?> e : expressions) if (e instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)e);
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
		return isNegatedRVal(evaluateIn(value, eval));
	}
	
	private boolean evaluateIn(Object value, Evaluator eval) {
		if (value == null) return false;

		if (objects != null) {
			for (Object o : objects) if (value.equals(o)) return true;
		} else if (expressions != null) {
			for (K2Expression<?> e : expressions) if (value.equals(e.evaluate(eval))) return true;
		} else if (collection != null) {
			for (Object o : collection) if (value.equals(o)) return true;
		} else if (collectionExpr != null) {
			for (Object o : collectionExpr.evaluate(eval)) if (value.equals(o)) return true;
		}

		return false;
	}



}

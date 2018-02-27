package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.ParameterExpression;

/**
 * The or predicate is a compound predicate and returns true if any of the boolean expressions in it are true
 * @author simon
 *
 */
public class PredicateOr extends AbstractPredicate implements K2Predicate {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Predicate[] predicates = null;
	K2Expression<Boolean> boolExpr1 = null;
	K2Expression<Boolean> boolExpr2 = null;
	
	/**
	 * Create an or predicate for the list of predicates
	 * @param predicates		The array of predicates that are child predicates for this or predicate
	 */
	public PredicateOr(K2Predicate... predicates) {
		super(BooleanOperator.OR);
		logger.trace("or: predicated list");
		for (K2Predicate p : predicates) addExpression(p);
		this.predicates = predicates;
	}

	/**
	 * Create an or predicate for the given pair of boolean expressions
	 * @param boolExpr1	The first boolean expression
	 * @param boolExpr2	The second boolean expression
	 */
	public PredicateOr(K2Expression<Boolean> boolExpr1, K2Expression<Boolean> boolExpr2) {
		super(BooleanOperator.OR);
		logger.trace("or: pair of expressions");
		this.boolExpr1 = boolExpr1;
		addExpression(boolExpr1);
		this.boolExpr2 = boolExpr2;
		addExpression(boolExpr2);
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if (predicates == null) {
			if(boolExpr1 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)boolExpr1);
			if(boolExpr2 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)boolExpr2);

		} else {
			for (K2Predicate p : predicates) {
				p.populateParameters(eval);
			}
		}
	}

	@Override
	public Boolean evaluate(Evaluator eval) {
		if (predicates == null) {
			Boolean b1 = boolExpr1.evaluate(eval);
			Boolean b2 = boolExpr2.evaluate(eval);
			
			logger.trace("{} or {} = {}", b1, b2, b1||b2);
			
			if (b1 == null || b2 == null) return false;
			return isNegatedRVal(b1 || b2);
			
		} else {
			for (K2Predicate p : predicates) {
				logger.trace("Or: {}", p.evaluate(eval));
				if (p.evaluate(eval)) {
					return isNegatedRVal(true);
				}
			}
			logger.trace("Or: nothing true");
			return isNegatedRVal(false);
		}
	}

}

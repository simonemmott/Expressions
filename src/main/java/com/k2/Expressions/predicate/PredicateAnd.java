package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.K2Expression;
import com.k2.Expressions.expression.K2ParameterExpression;

/**
 * This predicate is a compound predicate and returns true if all the boolean expressions in it are true
 * 
 * @author simon
 *
 */
public class PredicateAnd extends AbstractPredicate implements K2Predicate {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	K2Predicate[] predicates = null;
	K2Expression<Boolean> boolExpr1 = null;
	K2Expression<Boolean> boolExpr2 = null;
	
	/**
	 * Create an and predicate for the list of predicates
	 * @param predicates		The array of predicates that are child predicates for this and predicate
	 */
	public PredicateAnd(K2Predicate... predicates) {
		super(BooleanOperator.AND);
		this.predicates = predicates;
		for (K2Predicate p : predicates) {
			addExpression(p);
		}
	}

	/**
	 * Create an and predicate for the given pair of boolean expressions
	 * @param boolExpr1	The first boolean expression
	 * @param boolExpr2	The second boolean expression
	 */
	public PredicateAnd(K2Expression<Boolean> boolExpr1, K2Expression<Boolean> boolExpr2) {
		super(BooleanOperator.AND);
		this.boolExpr1 = boolExpr1;
		addExpression(boolExpr1);
		this.boolExpr2 = boolExpr2;
		addExpression(boolExpr2);
	}


	@Override
	public Boolean evaluate(Evaluator eval) {
		logger.trace("Evaluating AND");
		if (predicates == null) {
			logger.trace("predicates is null");
			Boolean b1 = boolExpr1.evaluate(eval);
			Boolean b2 = boolExpr2.evaluate(eval);
			if (b1 != null && b2 != null && b1  && b2) {
				return isNegatedRVal(true);
			} else {
				return isNegatedRVal(false);
			}
		} else {
			logger.trace("predicated is not null");
			for (K2Predicate p : predicates) {
				logger.trace("Evaluation passed to {}", p.getClass().getName());
				if (!p.evaluate(eval)) {
					return isNegatedRVal(false);
				}
			}
			return isNegatedRVal(true);
		}
	}

	@Override
	public void populateParameters(Evaluator eval) {
		if (predicates == null) {
			if(boolExpr1 instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)boolExpr1);
			if(boolExpr2 instanceof K2ParameterExpression<?>) eval.add((K2ParameterExpression<?>)boolExpr2);

		} else {
			for (K2Predicate p : predicates) {
				p.populateParameters(eval);
			}
		}
	}

}

package com.k2.Expressions.predicate;

import java.lang.invoke.MethodHandles;

import javax.persistence.criteria.Predicate.BooleanOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;

public class PredicateOr extends AbstractPredicate implements Predicate {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Predicate[] predicates = null;
	Expression<Boolean> boolExpr1 = null;
	Expression<Boolean> boolExpr2 = null;
	
	public PredicateOr(Predicate... predicates) {
		super(BooleanOperator.OR);
		logger.trace("or: predicated list");
		for (Predicate p : predicates) addExpression(p);
		this.predicates = predicates;
	}

	public PredicateOr(Expression<Boolean> boolExpr1, Expression<Boolean> boolExpr2) {
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
			for (Predicate p : predicates) {
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
			for (Predicate p : predicates) {
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

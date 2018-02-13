package com.k2.Expressions.predicate;

import javax.persistence.criteria.Predicate.BooleanOperator;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ParameterExpression;


public class PredicateAnd extends AbstractPredicate implements Predicate {

	Predicate[] predicates = null;
	Expression<Boolean> boolExpr1 = null;
	Expression<Boolean> boolExpr2 = null;
	
	public PredicateAnd(Predicate... predicates) {
		super(BooleanOperator.AND);
		for (Predicate p : predicates) addExpression(p);
		this.predicates = predicates;
	}

	public PredicateAnd(Expression<Boolean> boolExpr1, Expression<Boolean> boolExpr2) {
		super(BooleanOperator.AND);
		this.boolExpr1 = boolExpr1;
		addExpression(boolExpr1);
		this.boolExpr2 = boolExpr2;
		addExpression(boolExpr2);
	}


	@Override
	public Boolean evaluate(Evaluator eval) {
		if (predicates == null) {
			Boolean b1 = boolExpr1.evaluate(eval);
			Boolean b2 = boolExpr2.evaluate(eval);
			if (b1 != null && b2 != null && b1  && b2) {
					return isNegatedRVal(true);
				} else {
					return isNegatedRVal(false);
				}
			} else {
				for (Predicate p : predicates) {
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
			if(boolExpr1 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)boolExpr1);
			if(boolExpr2 instanceof ParameterExpression<?>) eval.add((ParameterExpression<?>)boolExpr2);

		} else {
			for (Predicate p : predicates) {
				p.populateParameters(eval);
			}
		}
	}

}

package com.k2.Expressions.predicate;

import java.util.List;

import javax.persistence.criteria.Predicate.BooleanOperator;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.expression.Expression;

public interface Predicate extends Expression<Boolean>{

	public boolean isNegated();

	public Predicate not();

	public List<Expression<Boolean>> getExpressions();

	public BooleanOperator getOperator();

	public void populateParameters(Evaluator eval);

}

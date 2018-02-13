package com.k2.Expressions;

import com.k2.Expressions.expression.CurrentTime;
import com.k2.Expressions.expression.ParameterExpression;
import com.k2.Expressions.predicate.Predicate;

public interface Evaluator {

	public void add(ParameterExpression<?> expr);
	
	public boolean checkParametersSet(Predicate ... predicates);
	
	public CurrentTime getCurrentTime();

}

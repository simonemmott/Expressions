package com.k2.Expressions.evaluators;


import com.k2.Expressions.Evaluator;
import com.k2.Expressions.GetterEvaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.expression.GetterExpression;

public class GenericEvaluator<E> extends SimpleParameterEvaluator implements Evaluator, ParameterEvaluator, GetterEvaluator<E> {

	E valueSource;
	
	public GenericEvaluator(E valueSource) {
		this.valueSource = valueSource;
	}

	@Override
	public <T> T valueOf(GetterExpression<E, T> getterExpression) {
		return getterExpression.getter().get(valueSource);
	}

}

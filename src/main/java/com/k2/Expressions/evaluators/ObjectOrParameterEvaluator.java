package com.k2.Expressions.evaluators;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.GetterEvaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.expression.GetterExpression;
import com.k2.Expressions.expression.ParameterExpression;
import com.k2.Util.ObjectUtil;

public class ObjectOrParameterEvaluator<E> extends GenericEvaluator<E> implements Evaluator, ParameterEvaluator, GetterEvaluator<E> {

	
	public ObjectOrParameterEvaluator(E valueSource) {
		super(valueSource);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T valueOf(GetterExpression<E, T> getterExpression) {
		ParameterExpression<?> searchExpr = new ParameterExpression(getterExpression.getJavaType(), getterExpression.getAlias());
		if (valueSource.getClass().isAssignableFrom(getterExpression.getter().getDeclaringClass())) return getterExpression.getter().get(valueSource);
		if (parameterValues.containsKey(searchExpr)) return (T) parameterValues.get(searchExpr);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T valueOf(ParameterExpression<T> param) {
		if (ObjectUtil.canGet(valueSource, param.getJavaType(), param.getAlias())) return ObjectUtil.get(valueSource, param.getJavaType(), param.getAlias());
		if (parameterValues.containsKey(param)) return (T) parameterValues.get(param);
		return null;
	}

}

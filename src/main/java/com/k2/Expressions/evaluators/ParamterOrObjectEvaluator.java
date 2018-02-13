package com.k2.Expressions.evaluators;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.GetterEvaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.expression.GetterExpression;
import com.k2.Expressions.expression.ParameterExpression;
import com.k2.Util.ObjectUtil;

public class ParamterOrObjectEvaluator<E> extends GenericEvaluator<E> implements Evaluator, ParameterEvaluator, GetterEvaluator<E> {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public ParamterOrObjectEvaluator(E valueSource) {
		super(valueSource);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T valueOf(GetterExpression<E, T> getterExpression) {
		ParameterExpression<?> searchExpr = new ParameterExpression(getterExpression.getJavaType(), getterExpression.getName());
		
		logger.trace("Recieved getter for class {} with alias {}", getterExpression.getJavaType().getName(), getterExpression.getName());
		
		if (parameterValues.containsKey(searchExpr)) return (T) parameterValues.get(searchExpr);
		if (valueSource.getClass().isAssignableFrom(getterExpression.getter().getDeclaringClass())) return getterExpression.getter().get(valueSource);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T valueOf(ParameterExpression<T> param) {
		if (parameterValues.containsKey(param)) return (T) parameterValues.get(param);
		return ObjectUtil.get(valueSource, param.getJavaType(), param.getAlias());
	}

}

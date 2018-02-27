package com.k2.Expressions.evaluators;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.GetterEvaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.expression.GetterExpression;
import com.k2.Expressions.expression.K2ParameterExpression;
import com.k2.Util.ObjectUtil;

/**
 * This class extends the generic evaluator and allows the values returned for parameter expressions to be fulfilled by values from 
 * the generic evaluators value source and the values set on the evaluator. It also allows getter expressions to be fulfilled by 
 * set parameter values.  
 * 
 * If a value is available from the generic evaluators value source then that value is returned for either
 * a suitable parameter expression or a getter expression. If a value is not available from the generic evaluators value source
 * then the set parameters are inspected for a suitable value for the requested parameter or getter expression.
 * 
 * @author simon
 *
 * @param <E>	The type of the object that provides values for getter expressions.
 */
public class ObjectOrParameterEvaluator<E> extends GenericEvaluator<E> implements Evaluator, ParameterEvaluator, GetterEvaluator<E> {

	
	public ObjectOrParameterEvaluator(E valueSource) {
		super(valueSource);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T valueOf(GetterExpression<E, T> getterExpression) {
		K2ParameterExpression<?> searchExpr = new K2ParameterExpression(getterExpression.getJavaType(), getterExpression.getAlias());
		if (valueSource.getClass().isAssignableFrom(getterExpression.getter().getDeclaringClass())) return getterExpression.getter().get(valueSource);
		if (parameterValues.containsKey(searchExpr)) return (T) parameterValues.get(searchExpr);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T valueOf(K2ParameterExpression<T> param) {
		if (ObjectUtil.canGet(valueSource, param.getJavaType(), param.getAlias())) return ObjectUtil.get(valueSource, param.getJavaType(), param.getAlias());
		if (parameterValues.containsKey(param)) return (T) parameterValues.get(param);
		return null;
	}

}

package com.k2.Expressions.evaluators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.expression.CurrentTime;
import com.k2.Expressions.expression.K2ParameterExpression;
import com.k2.Expressions.predicate.K2Predicate;

/**
 * This class provides an implementation of the Evaluator interface and the ParameterEvaluator interface
 * As such it is capable of providing values for parameter expressions
 * 
 * @author simon
 *
 */
public class SimpleParameterEvaluator implements Evaluator, ParameterEvaluator {

	Set<K2ParameterExpression<?>> requiredParameters = new HashSet<K2ParameterExpression<?>>();
	protected Map<K2ParameterExpression<?>, Object> parameterValues = new HashMap<K2ParameterExpression<?>, Object>();
	
	/**
	 * Create am instance of the SimpleParameterEvaluator
	 */
	public SimpleParameterEvaluator() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T valueOf(K2ParameterExpression<T> param) {
		return (T) parameterValues.get(param);
	}

	@Override
	public void add(K2ParameterExpression<?> param) {
		requiredParameters.add(param);
	}
	
	@Override
	public boolean checkParametersSet(K2Predicate... predicates) {
		for (K2Predicate p : predicates) {
			p.populateParameters(this);
		}
		for (K2ParameterExpression<?> param : requiredParameters) {
			if (!parameterValues.containsKey(param)) return false;
		}
		return true;
	}

	/**
	 * Set the value for the given parameter.
	 * Having set the value for the given parameter the evaluator will return the given value when the method valueOf is called
	 * for a parameter expression for the same class and alias as the given parameter expressions
	 * 
	 * @param param	The parameter expression for which to set the value
	 * @param value	The value to be returned for the given parameter expressions
	 * @param <T>	The type of the value required by the parameter expressions
	 * @return		This evaluator for method chaining
	 */
	public <T> SimpleParameterEvaluator set(K2ParameterExpression<T> param, T value) {
		parameterValues.put(param, value);
		return this;
	}

	public SimpleParameterEvaluator setRawParameter(K2ParameterExpression<?> param, Object value) {
		parameterValues.put(param, value);
		return this;
	}

	/**
	 * Set the value for parameters requiring values of the given class with the given alias
	 * 
	 * @param cls	The type of the value required by the prospective parameter expression
	 * @param alias	The alias of the prospective parameter expression
	 * @param value	The value to be returned for parameter expressions requiring the given class and alias
	 * @param <T> 	The type of the value required by the prospective parameter expression
	 * @return		This evaluator for method chaining
	 */
	public <T> SimpleParameterEvaluator set(Class<T> cls, String alias, T value) {
		parameterValues.put(new K2ParameterExpression<T>(cls, alias), value);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> SimpleParameterEvaluator setRawParameter(String alias, Object value) {
		return set((Class<T>)value.getClass(), alias, (T) value);
	}
	/**
	 * This field holds a reference to an instance of current time.
	 * This field is populated and returned on the first call to getCurrentTime() for this instance and then returned on 
	 * subsequently calls to get current time.
	 * 
	 * Consequently calls to get current time for a particular instance of SimpleParameterEvaluator will always return the same time
	 * This ensures that comparisons between times will always compare to the same value of current time for the same evaluator
	 */
	private CurrentTime currentTime;

	@Override
	public CurrentTime getCurrentTime() {
		if (currentTime == null) currentTime = new CurrentTime();
		return currentTime;
	}


}

package com.k2.Expressions.evaluators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.expression.CurrentTime;
import com.k2.Expressions.expression.ParameterExpression;
import com.k2.Expressions.predicate.Predicate;

public class SimpleParameterEvaluator implements Evaluator, ParameterEvaluator {

	Set<ParameterExpression<?>> requiredParameters = new HashSet<ParameterExpression<?>>();
	Map<ParameterExpression<?>, Object> parameterValues = new HashMap<ParameterExpression<?>, Object>();
	
	public SimpleParameterEvaluator() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T valueOf(ParameterExpression<T> param) {
		return (T) parameterValues.get(param);
	}

	@Override
	public void add(ParameterExpression<?> param) {
		requiredParameters.add(param);
	}
	
	public <V> void set(ParameterExpression<V> param, V value) {
		parameterValues.put(param, value);
	}

	@Override
	public boolean checkParametersSet(Predicate... predicates) {
		for (Predicate p : predicates) {
			p.populateParameters(this);
		}
		for (ParameterExpression<?> param : requiredParameters) {
			if (!parameterValues.containsKey(param)) return false;
		}
		return true;
	}

	public <T> SimpleParameterEvaluator set(Class<T> cls, String alias, T value) {
		parameterValues.put(new ParameterExpression<T>(cls, alias), value);
		return this;
	}
	
	private CurrentTime currentTime;

	@Override
	public CurrentTime getCurrentTime() {
		if (currentTime == null) currentTime = new CurrentTime();
		return currentTime;
	}


}

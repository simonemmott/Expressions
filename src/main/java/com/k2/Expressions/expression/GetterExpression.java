package com.k2.Expressions.expression;

import javax.persistence.TemporalType;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.GetterEvaluator;
import com.k2.Util.Getter;

/**
 * The getter expression derives its value through the evaluator to supply a value for the given getter
 * 
 * @author simon
 *
 * @param <E>	The type of the object through which the getter will get its value
 * @param <T>	The type of the value supplied by the getter and returned by this expression
 */
public class GetterExpression<E,T> extends AbstractExpression<T> implements Expression<T>{
	
	Getter<E,T> getter;
	TemporalType temporalType = TemporalType.DATE;
	
	/**
	 * Create a getter expression for the given getter
	 * @param getter		The getter that gets the value for this expression
	 */
	public GetterExpression(Getter<E,T> getter) {
		super(getter.getAlias(), getter.getJavaType());
		this.getter = getter;
	}

	/**
	 * Get the getter for this expression
	 * @return	The getter that supplies the value for this expression
	 */
	public Getter<E,T> getter() { return getter; }

	/**
	 * Set the temporal type for this getter expression
	 * @param temporalType	The temporal type to set for this getter expression
	 * @return	This expression for method chaining
	 */
	public GetterExpression<E,T> setTemporalType(TemporalType temporalType) {
		this.temporalType = temporalType;
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T evaluate(Evaluator eval) {
		if (eval instanceof GetterEvaluator) {
			return (T) ((GetterEvaluator)eval).valueOf(this);
		}
		return (T) null;
	}


}

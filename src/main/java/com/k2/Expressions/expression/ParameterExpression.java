package com.k2.Expressions.expression;

import javax.persistence.TemporalType;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.ParameterEvaluator;

/**
 * Parameter expressions derive their value from the parameters set on the evaluator
 * 
 * @author simon
 *
 * @param <T>	The type of the value returned by this parameter expression
 */
public class ParameterExpression<T> extends AbstractExpression<T> implements Expression<T>{
	
	Integer pos;
	TemporalType temporalType = TemporalType.DATE;
	
	/**
	 * Create a parameter expression for the given class and name
	 * @param cls	The class of the parameter expression
	 * @param name	The name of the parameter expression
	 */
	public ParameterExpression(Class<T> cls, String name) {
		super(name, cls);
	}

	/**
	 * Set the position of this parameter expression
	 * @param pos	The integer position of this parameter
	 */
	public void setPosition(Integer pos) {
		if (this.pos == null) this.pos = pos;
	}
	
	/**
	 * Get the integer postion of this parameter expression if it has been set
	 * @return	The integer postion of this parameter exprerssion
	 */
	public Integer getPosition() {
		return pos;
	}

	/**
	 * Get the java type of this parameter expression
	 * @return	The java type of this parameter expression
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getParameterType() {
		return (Class<T>) super.getJavaType();
	}

	/**
	 * Set the temporal type of this parameter expression
	 * @param temporalType	The parameter type to set for this parameter expression
	 * @return	This parameter expression for method chaining
	 */
	public ParameterExpression<T> setTemporalType(TemporalType temporalType) {
		this.temporalType = temporalType;
		return this;
	}

	@Override
	public T evaluate(Evaluator eval) {
		if (eval instanceof ParameterEvaluator) {
			return ((ParameterEvaluator)eval).valueOf(this);
		}
		return (T) null;
	}


}

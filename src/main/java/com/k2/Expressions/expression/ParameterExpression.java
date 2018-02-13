package com.k2.Expressions.expression;

import java.util.Collection;

import javax.persistence.TemporalType;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.predicate.*;

public class ParameterExpression<T> implements Expression<T>{
	
	Class<T> cls;
	String alias;
	String name;
	Integer pos;
	TemporalType temporalType = TemporalType.DATE;
	
	public ParameterExpression(Class<T> cls, String name) {
		this.cls = cls;
		this.name = name;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
		result = prime * result + ((cls == null) ? 0 : cls.getName().hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParameterExpression<?> other = (ParameterExpression<?>) obj;
		if (getAlias() == null) {
			if (other.getAlias() != null)
				return false;
		} else if (!getAlias().equals(other.getAlias()))
			return false;
		if (cls == null) {
			if (other.cls != null)
				return false;
		} else if (!cls.getName().equals(other.cls.getName()))
			return false;
		return true;
	}


	public String getName() {
		return name;
	}

	public void setPosition(Integer pos) {
		if (this.pos == null) this.pos = pos;
	}
	public Integer getPosition() {
		return pos;
	}

	public Class<T> getParameterType() {
		return cls;
	}

	@Override
	public Predicate isNull() {
		return new PredicateNull(this);
	}

	@Override
	public Predicate isNotNull() {
		return new PredicateNotNull(this);
	}

	@Override
	public Predicate in(Object... values) {
		return new PredicateIn(this, values);
	}

	@Override
	public Predicate in(Expression<?>... values) {
		return new PredicateIn(this, values);
	}

	@Override
	public Predicate in(Collection<?> values) {
		return new PredicateIn(this, values);
	}

	@Override
	public Predicate in(Expression<Collection<?>> values) {
		return new PredicateIn(this, values);
	}

	@Override
	public <X> Expression<X> as(Class<X> type) {
		return new GenericExpression<X>(this, type);
	}

	@Override
	public Expression<T> alias(String alias) {
		this.alias = alias;
		return this;
	}

	@Override
	public Class<? extends T> getJavaType() {
		return cls;
	}

	@Override
	public String getAlias() {
		return (alias == null) ? name : alias;
	}

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

package com.k2.Expressions.expression;

import java.util.Collection;

import javax.persistence.TemporalType;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.GetterEvaluator;
import com.k2.Expressions.predicate.*;
import com.k2.Util.Getter;

public class GetterExpression<E,T> implements Expression<T>{
	
	Getter<E,T> getter;
	String alias;
	TemporalType temporalType = TemporalType.DATE;
	
	public GetterExpression(Getter<E,T> getter) {
		this.getter = getter;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((getter == null) ? 0 : getter.hashCode());
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
		GetterExpression<?,?> other = (GetterExpression<?,?>) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (getter == null) {
			if (other.getter != null)
				return false;
		} else if (!getter.equals(other.getter))
			return false;
		return true;
	}


	public String getName() {
		return getter.getAlias();
	}
	
	public Getter<E,T> getter() { return getter; }

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
		return getter.getJavaType();
	}

	@Override
	public String getAlias() {
		return alias;
	}

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

package com.k2.Expressions.expression;

import java.util.Collection;

import com.k2.Expressions.predicate.*;


public abstract class AbstractExpression<T> implements Expression<T>{
	
	private String alias;
	private String name;
	
	private Class<? extends T> javaType;
	
	
	protected AbstractExpression(String name, Class<? extends T> javaType) {
		this.name = name;
		this.javaType = javaType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
		result = prime * result + ((javaType == null) ? 0 : javaType.getName().hashCode());
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
		AbstractExpression<?> other = (AbstractExpression<?>) obj;
		if (getAlias() == null) {
			if (other.getAlias() != null)
				return false;
		} else if (!getAlias().equals(other.getAlias()))
			return false;
		if (javaType == null) {
			if (other.javaType != null)
				return false;
		} else if (!javaType.getName().equals(other.javaType.getName()))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	protected AbstractExpression(Class<? extends T> javaType) {
		this.javaType = javaType;
	}

	protected AbstractExpression(String alias) {
		this.alias = alias;
	}

	public <X> Expression<X> as(Class<X> cls) {
		return new GenericExpression<X>(this, cls);
	}

	public Expression<T> alias(String alias) {
		this.alias = alias;
		return this;
	}

	public String getAlias() {
		return (alias == null) ? name : alias;
	}

	public Class<? extends T> getJavaType() {
		return javaType;
	}


	public Predicate getTrue() { return AbstractPredicate.getTrue(); }
	public Predicate getFalse() { return AbstractPredicate.getFalse(); }

	public Predicate in(Object... objects) {
		return new PredicateIn(this, objects);
	}

	public Predicate in(Expression<?>... expressions) {
		return new PredicateIn(this, expressions);
	}

	public Predicate in(Collection<?> collection) {
		return new PredicateIn(this, collection);
	}

	public Predicate in(Expression<Collection<?>> expr) {
		return new PredicateIn(this, expr);
	}

	public Predicate isNotNull() {
		return new PredicateNotNull(this);
	}

	public Predicate isNull() {
		return new PredicateNull(this);
	}



}

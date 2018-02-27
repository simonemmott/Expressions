package com.k2.Expressions.expression;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.k2.Expressions.exceptions.ExpressionError;
import com.k2.Expressions.predicate.PredicateIn;
import com.k2.Expressions.predicate.PredicateNotNull;
import com.k2.Expressions.predicate.PredicateNull;

/**
 * This abstract class forms the basis of the Expression classes
 * 
 * This class implemnts the hashCode() and equals(Object) methods using the name of the java type returned by the expression
 * and the alias value returned by the getAlias() method. Consequently expressions are considered equal if their implementing 
 * classes are equal and they return the same data type and their getAlias() methods return the same value.
 * 
 * @author simon
 *
 * @param <T>	The type of the value returned by the expression
 */
public abstract class AbstractExpression<T> implements K2Expression<T>{
	
	private String alias;
	private String name;
	
	private Class<? extends T> javaType;
	
	/**
	 * Create an instance of the abstract class setting the name of the expression and the returning data type
	 * @param name		The name of the expression
	 * @param javaType	The returning type of the expression
	 */
	protected AbstractExpression(String name, Class<? extends T> javaType) {
		this.name = name;
		this.javaType = javaType;
	}
	
	/**
	 * Create an instance of the abstract class setting the returning data type
	 * @param javaType	The returning type of the expression
	 */
	protected AbstractExpression(Class<? extends T> javaType) {
		this.javaType = javaType;
	}

	/**
	 * The hashCode is derived from the value returned by the getAlias() method and the class name of the returning type
	 * of the expression
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
		result = prime * result + ((javaType == null) ? 0 : javaType.getName().hashCode());
		return result;
	}


	/**
	 * Object equality is identified from the value returned by the getAlias() method and the class name of the returning type
	 * of the expression
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!obj.getClass().isAssignableFrom(getClass()))
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

//	@Override
//	public String getName() {
//		return name;
//	}

	@Override
	public <X> K2Expression<X> as(Class<X> cls) {
		if (cls.isAssignableFrom(javaType)) return new ExprAs<X>(cls, this);
		throw new ExpressionError("The required type {} cannot be cast from the type of the supplied expression {}", cls.getName(), javaType.getName());
	}

	@Override
	public K2Expression<T> alias(String alias) {
		this.alias = alias;
		return this;
	}

	@Override
	public String getAlias() {
		return (alias == null) ? name : alias;
	}

	@Override
	public Class<? extends T> getJavaType() {
		return javaType;
	}

	@Override
	public Predicate in(Object... objects) {
		return new PredicateIn(this, objects);
	}

	@Override
	public Predicate in(Expression<?>... expressions) {
		return new PredicateIn(this, expressions);
	}

	@Override
	public Predicate in(Collection<?> collection) {
		return new PredicateIn(this, collection);
	}

	@Override
	public Predicate in(Expression<Collection<?>> expr) {
		return new PredicateIn(this, (K2Expression<Collection<?>>)expr);
	}

	@Override
	public Predicate isNotNull() {
		return new PredicateNotNull(this);
	}

	@Override
	public Predicate isNull() {
		return new PredicateNull(this);
	}

	@Override
	public boolean isCompoundSelection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Selection<?>> getCompoundSelectionItems() {
		// TODO Auto-generated method stub
		return null;
	}



}

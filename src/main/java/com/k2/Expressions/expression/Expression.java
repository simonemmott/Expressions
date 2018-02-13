package com.k2.Expressions.expression;

import java.util.Collection;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.predicate.Predicate;

public interface Expression<T> {

	public T evaluate(Evaluator eval);
	
	public String getName();

	public Class<? extends T> getJavaType();

	public Predicate isNull();

	public Predicate isNotNull();

	public Predicate in(Object ... values);

	public Predicate in(Expression<?> ... values);

	public Predicate in(Collection<?> values);

	public Predicate in(Expression<Collection<?>> values);

	public <X> Expression<X> as(Class<X> type);

	public Expression<T> alias(String alias);

	public String getAlias();

}

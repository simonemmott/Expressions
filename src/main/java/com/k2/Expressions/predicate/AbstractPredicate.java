package com.k2.Expressions.predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Predicate.BooleanOperator;

import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.GenericExpression;

public abstract class AbstractPredicate implements Predicate {
	
	private String alias;
	private String name;
	private boolean negated = false;
	private BooleanOperator operator = null;
	private List<Expression<Boolean>> expressions = null;
	
	AbstractPredicate() {
	}

	AbstractPredicate(String name) {
		this.name = name;
	}

	AbstractPredicate(BooleanOperator operator) {
		this.operator = operator;
	}

	AbstractPredicate(String alias, BooleanOperator operator) {
		this.alias = alias;
		this.operator = operator;
	}
	
	public String getName() {
		return name;
	}
	
	void addExpression(Expression<Boolean> expr) {
		if (expressions == null) expressions = new ArrayList<Expression<Boolean>>();
		expressions.add(expr);
	}

	@Override
	public List<Expression<Boolean>> getExpressions(){
		if (expressions == null) return new ArrayList<Expression<Boolean>>();
		return expressions;
	}


	boolean isNegatedRVal(boolean rVal) {
		return (isNegated()) ? !rVal: rVal;
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
	public Predicate isNotNull() {
		return new PredicateNotNull(this);
	}

	@Override
	public Predicate isNull() {
		return new PredicateNull(this);
	}

	@Override
	public <X> Expression<X> as(Class<X> cls) {
		return new GenericExpression<X>(this, cls);
	}

	public static Predicate getTrue() { return new PredicateTrue(); }
	public static Predicate getFalse() { return new PredicateFalse(); }

	@Override
	public Expression<Boolean> alias(String alias) {
		this.alias = alias;
		return this;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public Class<? extends Boolean> getJavaType() {
		return Boolean.class;
	}

	@Override
	public boolean isNegated() {
		return negated;
	}

	@Override
	public Predicate not() {
		negated = true;
		return this;
	}

	@Override
	public BooleanOperator getOperator() {
		return operator;
	}

}

package com.k2.Expressions.predicate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate.BooleanOperator;

import com.k2.Expressions.expression.AbstractExpression;
import com.k2.Expressions.expression.Expression;

/**
 * This abstract class forms the basis of all the predicate classes
 * @author simon
 *
 */
public abstract class AbstractPredicate extends AbstractExpression<Boolean> implements Predicate {
	
	private boolean negated = false;
	private BooleanOperator operator = BooleanOperator.AND;
	private List<Expression<Boolean>> expressions = null;
	
	/**
	 * Create a predicate
	 */
	AbstractPredicate() {
		super(Boolean.class);
	}

	/**
	 * Create a predicate with the given name
	 * @param name
	 */
	AbstractPredicate(String name) {
		super(name, Boolean.class);
	}

	/**
	 * Create a given predicate for the given boolean operator
	 * @param operator	The boolean operator for the predicate
	 */
	AbstractPredicate(BooleanOperator operator) {
		super(Boolean.class);
		this.operator = operator;
	}

	/**
	 * Create a predicate for the given name and boolan operator
	 * @param name		The name of this predicate
	 * @param operator	The operator for this predicate
	 */
	AbstractPredicate(String name, BooleanOperator operator) {
		super(name, Boolean.class);
		this.operator = operator;
	}
	
	/**
	 * Add the expression to this predicates list of expressions
	 * @param expr	The expression that is a child boolean expression of this predicate
	 */
	void addExpression(Expression<Boolean> expr) {
		if (expressions == null) expressions = new ArrayList<Expression<Boolean>>();
		expressions.add(expr);
	}

	@Override
	public List<Expression<Boolean>> getExpressions(){
		if (expressions == null) return new ArrayList<Expression<Boolean>>();
		return expressions;
	}

	/**
	 * This method reverses the boolean value of this predicate if the predicate has been negated
	 * 
	 * @param rVal	The boolean value evaluated for this predicate irrespective of whether the predicate has been negated
	 * @return	The negated variant of the given value if this predicate has been negated or the given value if it has not beed negated
	 */
	boolean isNegatedRVal(boolean rVal) {
		return (isNegated()) ? !rVal: rVal;
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

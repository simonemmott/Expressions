package com.k2.Expressions.expression;

import java.util.Collection;

import com.k2.Expressions.Evaluator;
import com.k2.Expressions.predicate.Predicate;

/**
 * This interface defines the methods available on all Expressions
 * Expressions provide an abstracted value derived from an Evaluator. This construct allows the structure
 * of complex expressions to be defined in isolation of the source of the values used to determine the 
 * value of the complex expression.
 * 
 * @author simon
 *
 * @param <T>	The type of the value returned by the expression
 */
public interface Expression<T> {

	/**
	 * This method extracts the value of this expression from the given evaluator
	 * 
	 * @param eval	The evaluator to be used to derive values for this expression and its sub expressions
	 * @return	The value of this expression as derived from the given evaluator
	 */
	public T evaluate(Evaluator eval);
	
	/**
	 * The name of the expression if it has been defined
	 * @return	The name of the expression
	 */
	public String getName();

	/**
	 * This method returns the java type returned by this expression
	 * @return	The java type of the value returned by this expression
	 */
	public Class<? extends T> getJavaType();

	/**
	 * This method returns a predicate that checks whether the value returned by this expression is null
	 * @return	A predicate to check whether the value of this expression is null
	 */
	public Predicate isNull();

	/**
	 * This method returns a predicate that checks whether the value returned by this expression is not null
	 * @return	A predicate to check whether the value of this expression is not null
	 */
	public Predicate isNotNull();

	/**
	 * This method returns a predicate that checks whether the value returned by this expression is in the given values
	 * @param values		The array of values to check whether it contains the value of this expression
	 * @return	A predicate to check whether the value of this expression is in the given values
	 */
	public Predicate in(Object ... values);

	/**
	 * This method returns a predicate that checks whether the value returned by this expression is in the given expressed values
	 * @param values		The array of expression to check whether it contains the value of this expression
	 * @return	A predicate to check whether the value of this expression is in the given expressed values
	 */
	public Predicate in(Expression<?> ... values);

	/**
	 * This method returns a predicate that checks whether the value returned by this expression is in the given collection
	 * @param values		The collection of values to check whether it contains the value of this expression
	 * @return	A predicate to check whether the value of this expression is in the given collection
	 */
	public Predicate in(Collection<?> values);

	/**
	 * This method returns a predicate that checks whether the value returned by this expression is in the given expressed collection
	 * @param values		The expression returning a collection of values to check whether it contains the value of this expression
	 * @return	A predicate to check whether the value of this expression is in the given expressed collection
	 */
	public Predicate in(Expression<Collection<?>> values);

	/**
	 * This method returns a expression that casts the the returned value of this expression into the desired class
	 * @param type	The type of he desired evaluated value
	 * @return	An Expression that casts the value evaluated from this expression into the desired class. NOTE A ClassCastException will
	 * be thrown if the desired class is incompatible with the return value of this expression
	 * @param <X> The class to which to cast the expressions return value
	 */
	public <X> Expression<X> as(Class<X> type);

	/**
	 * This method sets an alias for this expression.  The equality of an expression is dependant on the alias of the expression if it
	 * has been set or the name if it has not been set.
	 * @param alias		The alias to use to identify this expression
	 * @return			This expression for method chaining
	 */
	public Expression<T> alias(String alias);

	/**
	 * This method returns the current alias of this expression or the name is the alias has not been set. This method is used in 
	 * equality checks for expressions
	 * 
	 * @return	The alias of this expression if it has been set or the name if it has not been set.
	 */
	public String getAlias();

}

package com.k2.Expressions.predicate;

import java.util.Collection;

import com.k2.Expressions.expression.Expression;

/**
 * The predicate builder provides factory methods to create the different types of predicates
 * 
 * @author simon
 *
 */
public class PredicateBuilder {

	/**
	 * Create an instance of the predicate builder
	 */
	public PredicateBuilder() { }
	
	/**
	 * Create a predicate that performs a conjunction operation on the list of predicates
	 * @param predicates		The child predicates for the and predicate
	 * @return	An and predicate
	 */
	public Predicate and(Predicate... predicates) {
		return new PredicateAnd(predicates);
	}

	/**
	 * Create a predicate that performs a conjunction operation on the given expressions
	 * @param boolExpr1	The first boolean expression
	 * @param boolExpr2	The second boolean expression
	 * @return		A predicate to check whether a value is between two values
	 */
	public Predicate and(Expression<Boolean> boolExpr1, Expression<Boolean> boolExpr2) {
		return new PredicateAnd(boolExpr1, boolExpr2);
	}

	/**
	 * Create a predicate that checks whether a value is between a beginning and end value
	 * @param check		The value to check
	 * @param beginExpr	The beginning value
	 * @param endExpr	The end value
	 * @return		A predicate to check whether a value is between two values
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate between(	Expression<? extends Y> check,
																Expression<? extends Y> beginExpr, 
																Expression<? extends Y> endExpr) {
		return new PredicateBetween(check, beginExpr, endExpr);
	}
	
	/**
	 * Create a predicate that checks whether a value is between a beginning and end value
	 * @param check		The value to check
	 * @param begin		The beginning value
	 * @param end		The end value
	 * @return		A predicate to check whether a value is between two values
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> check, Y begin, Y end) {
		return new PredicateBetween(check, begin, end);
	}

	/**
	 * Create a predicate to test for equality between two values
	 * @param expr1	The first value
	 * @param expr2	The second value
	 * @return	A predicate that check for equality of the two value
	 */
	public  Predicate equals(Expression<?> expr1, Expression<?> expr2) {
		return new PredicateEquals(expr1, expr2);
	}

	/**
	 * Create a predicate to test for equality between two values
	 * @param expr	The first value
	 * @param obj	The second value
	 * @return	A predicate that check for equality of the two value
	 */
	public  Predicate equals(Expression<?> expr, Object obj) {
		return new PredicateEquals(expr, obj);
	}
	
	/**
	 * Create a predicate to test whether a numeric value is greater than or equal to another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than or equal to the second value
	 */
	public Predicate ge(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateGE(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is greater than or equal to another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than or equal to the second value
	 */
	public Predicate ge(Expression<? extends Number> expr, Number num) {
		return new PredicateGE(expr, num);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateGreaterThan(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> expr, Y comp) {
		return new PredicateGreaterThan(expr, comp);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than or equal to the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateGreaterThanOrEqualTo(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than or equal to the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> expr, Y comp) {
		return new PredicateGreaterThanOrEqualTo(expr, comp);
	}

	/**
	 * Create a predicate to test whether a numeric value is greater than another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than the second value
	 */
	public Predicate gt(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateGT(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is greater than another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than the second value
	 */
	public Predicate gt(Expression<? extends Number> expr, Number num) {
		return new PredicateGT(expr, num);
	}

	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param objects	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public Predicate in(Expression<?> expr, Object ... objects) {
		return new PredicateIn(expr, objects);
	}
	
	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param expressions	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public Predicate in(Expression<?> expr, Expression<?>... expressions) {
		return new PredicateIn(expr, expressions);
	}
	
	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param collection	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public Predicate in(Expression<?> expr, Collection<?> collection) {
		return new PredicateIn(expr, collection);
	}
	
	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param collectionExpr	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public Predicate in(Expression<?> expr, Expression<Collection<?>> collectionExpr) {
		return new PredicateIn(expr, collectionExpr);
	}
	
	/**
	 * Create a predicate that returns a false value
	 * @return	A false predicate
	 */
	public Predicate isFalse() {
		return new PredicateFalse();
	}
	
	/**
	 * Create a predicate to test whether the given boolean value is false
	 * @param bool	The boolean value to check
	 * @return		A predicate to test for a false value
	 */
	public Predicate isFalse(Expression<Boolean> bool) {
		return new PredicateIsFalse(bool);
	}
	
	/**
	 * Create a predicate to return a true value
	 * @return	A true predicate
	 */
	public Predicate isTrue() {
		return new PredicateTrue();
	}

	/**
	 * Create a predicate that checks whether a boolean value is true
	 * @param bool	The boolean value to check
	 * @return	A predicate checks whether a boolean value is true
	 */
	public Predicate isTrue(Expression<Boolean> bool) {
		return new PredicateIsTrue(bool);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than or equals to another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is less that or equal to the second value
	 */
	public Predicate le(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateLE(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than or equals to another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is less that or equal to the second value
	 */
	public Predicate le(Expression<? extends Number> expr, Number num) {
		return new PredicateLE(expr, num);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is less than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateLessThan(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is less than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> expr, Y comp) {
		return new PredicateLessThan(expr, comp);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than or equal to the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is less than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateLessThanOrEqualTo(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than or equal to the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is less than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> expr, Y comp) {
		return new PredicateLessThanOrEqualTo(expr, comp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param patternExp		The regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public Predicate like(Expression<String> stringExp, Expression<String> patternExp) {
		return new PredicateLike(stringExp, patternExp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param pattern	The regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public Predicate like(Expression<String> stringExp, String pattern) {
		return new PredicateLike(stringExp, pattern);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param patternExp		The regular expression
	 * @param escapeCharExp	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public Predicate like(Expression<String> stringExp, Expression<String> patternExp, Expression<Character> escapeCharExp) {
		return new PredicateLike(stringExp, patternExp, escapeCharExp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param patternExp		The regular expression
	 * @param escapeChar	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public Predicate like(Expression<String> stringExp, Expression<String> patternExp, char escapeChar) {
		return new PredicateLike(stringExp, patternExp, escapeChar);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param pattern		The regular expression
	 * @param escapeCharExp	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public Predicate like(Expression<String> stringExp, String pattern, Expression<Character> escapeCharExp) {
		return new PredicateLike(stringExp, pattern, escapeCharExp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param pattern		The regular expression
	 * @param escapeChar	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public Predicate like(Expression<String> stringExp, String pattern, char escapeChar) {
		return new PredicateLike(stringExp, pattern, escapeChar);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is less that the second value
	 */
	public Predicate lt(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateLT(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is less that the second value
	 */
	public Predicate lt(Expression<? extends Number> expr, Number num) {
		return new PredicateLT(expr, num);
	}
	
	/**
	 * Create a predicate to negate a boolean expression
	 * @param expr	The boolean expression to negate
	 * @return	A predicate to negate a boolean expression
	 */
	public Predicate not(Expression<Boolean> expr) {
		return new PredicateNot(expr);
	}

	/**
	 * Create a predicate to test whether a value is not null
	 * @param expr	The value to check for null
	 * @return	A predicate to test whther a value is not null
	 */
	public Predicate isNotNull(Expression<?> expr) {
		return new PredicateNotNull(expr);
	}

	/**
	 * Create a predicate to test whether a value is null
	 * @param expr	The value to check for null
	 * @return	A predicate that tests whether a value is null
	 */
	public Predicate isNull(Expression<?> expr) {
		return new PredicateNull(expr);
	}

	/**
	 * Create a compound disjoint predicate
	 * @param predicates		The list of predicates to be disjointed together
	 * @return	A compound disjoint predicate
	 */
	public Predicate or(Predicate... predicates) {
		return new PredicateOr(predicates);
	}

	/**
	 * Create a compound disjoint predicate
	 * @param boolExpr1	The first boolean expression
	 * @param boolExpr2	The second boolean expression
	 * @return	A compound disjoint predicate
	 */
	public Predicate or(Expression<Boolean> boolExpr1, Expression<Boolean> boolExpr2) {
		return new PredicateOr(boolExpr1, boolExpr2);
	}



}

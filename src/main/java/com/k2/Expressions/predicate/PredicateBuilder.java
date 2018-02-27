package com.k2.Expressions.predicate;

import java.util.Collection;

import com.k2.Expressions.expression.K2Expression;

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
	public K2Predicate and(K2Predicate... predicates) {
		return new PredicateAnd(predicates);
	}

	/**
	 * Create a predicate that performs a conjunction operation on the given expressions
	 * @param boolExpr1	The first boolean expression
	 * @param boolExpr2	The second boolean expression
	 * @return		A predicate to check whether a value is between two values
	 */
	public K2Predicate and(K2Expression<Boolean> boolExpr1, K2Expression<Boolean> boolExpr2) {
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
	public <Y extends Comparable<? super Y>> K2Predicate between(	K2Expression<? extends Y> check,
																K2Expression<? extends Y> beginExpr, 
																K2Expression<? extends Y> endExpr) {
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
	public <Y extends Comparable<? super Y>> K2Predicate between(K2Expression<? extends Y> check, Y begin, Y end) {
		return new PredicateBetween(check, begin, end);
	}

	/**
	 * Create a predicate to test for equality between two values
	 * @param expr1	The first value
	 * @param expr2	The second value
	 * @return	A predicate that check for equality of the two value
	 */
	public  K2Predicate equals(K2Expression<?> expr1, K2Expression<?> expr2) {
		return new PredicateEquals(expr1, expr2);
	}

	/**
	 * Create a predicate to test for equality between two values
	 * @param expr	The first value
	 * @param obj	The second value
	 * @return	A predicate that check for equality of the two value
	 */
	public  K2Predicate equals(K2Expression<?> expr, Object obj) {
		return new PredicateEquals(expr, obj);
	}
	
	/**
	 * Create a predicate to test whether a numeric value is greater than or equal to another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than or equal to the second value
	 */
	public K2Predicate ge(K2Expression<? extends Number> expr1, K2Expression<? extends Number> expr2) {
		return new PredicateGE(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is greater than or equal to another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than or equal to the second value
	 */
	public K2Predicate ge(K2Expression<? extends Number> expr, Number num) {
		return new PredicateGE(expr, num);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate greaterThan(K2Expression<? extends Y> expr1, K2Expression<? extends Y> expr2) {
		return new PredicateGreaterThan(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate greaterThan(K2Expression<? extends Y> expr, Y comp) {
		return new PredicateGreaterThan(expr, comp);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than or equal to the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate greaterThanOrEqualTo(K2Expression<? extends Y> expr1, K2Expression<? extends Y> expr2) {
		return new PredicateGreaterThanOrEqualTo(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is greater than or equal to the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is greater than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate greaterThanOrEqualTo(K2Expression<? extends Y> expr, Y comp) {
		return new PredicateGreaterThanOrEqualTo(expr, comp);
	}

	/**
	 * Create a predicate to test whether a numeric value is greater than another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than the second value
	 */
	public K2Predicate gt(K2Expression<? extends Number> expr1, K2Expression<? extends Number> expr2) {
		return new PredicateGT(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is greater than another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is greater than the second value
	 */
	public K2Predicate gt(K2Expression<? extends Number> expr, Number num) {
		return new PredicateGT(expr, num);
	}

	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param objects	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public K2Predicate in(K2Expression<?> expr, Object ... objects) {
		return new PredicateIn(expr, objects);
	}
	
	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param expressions	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public K2Predicate in(K2Expression<?> expr, K2Expression<?>... expressions) {
		return new PredicateIn(expr, expressions);
	}
	
	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param collection	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public K2Predicate in(K2Expression<?> expr, Collection<?> collection) {
		return new PredicateIn(expr, collection);
	}
	
	/**
	 * Create a predicate to test whether a value is in a list of values
	 * @param expr		The value to check for presence in the list of values
	 * @param collectionExpr	The list of values to check in
	 * @return	An predicate to test whether the check value is in the list of values
	 */
	public K2Predicate in(K2Expression<?> expr, K2Expression<Collection<?>> collectionExpr) {
		return new PredicateIn(expr, collectionExpr);
	}
	
	/**
	 * Create a predicate that returns a false value
	 * @return	A false predicate
	 */
	public K2Predicate isFalse() {
		return new PredicateFalse();
	}
	
	/**
	 * Create a predicate to test whether the given boolean value is false
	 * @param bool	The boolean value to check
	 * @return		A predicate to test for a false value
	 */
	public K2Predicate isFalse(K2Expression<Boolean> bool) {
		return new PredicateIsFalse(bool);
	}
	
	/**
	 * Create a predicate to return a true value
	 * @return	A true predicate
	 */
	public K2Predicate isTrue() {
		return new PredicateTrue();
	}

	/**
	 * Create a predicate that checks whether a boolean value is true
	 * @param bool	The boolean value to check
	 * @return	A predicate checks whether a boolean value is true
	 */
	public K2Predicate isTrue(K2Expression<Boolean> bool) {
		return new PredicateIsTrue(bool);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than or equals to another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is less that or equal to the second value
	 */
	public K2Predicate le(K2Expression<? extends Number> expr1, K2Expression<? extends Number> expr2) {
		return new PredicateLE(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than or equals to another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is less that or equal to the second value
	 */
	public K2Predicate le(K2Expression<? extends Number> expr, Number num) {
		return new PredicateLE(expr, num);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is less than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate lessThan(K2Expression<? extends Y> expr1, K2Expression<? extends Y> expr2) {
		return new PredicateLessThan(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is less than the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate lessThan(K2Expression<? extends Y> expr, Y comp) {
		return new PredicateLessThan(expr, comp);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than or equal to the second comparable value
	 * @param expr1	The first comparable value
	 * @param expr2	The second comparable value
	 * @return	A predicate that checks whether the first value is less than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate lessThanOrEqualTo(K2Expression<? extends Y> expr1, K2Expression<? extends Y> expr2) {
		return new PredicateLessThanOrEqualTo(expr1, expr2);
	}

	/**
	 * Create a predicate that tests whether the first comparable value is less than or equal to the second comparable value
	 * @param expr	The first comparable value
	 * @param comp	The second comparable value
	 * @return	A predicate that checks whether the first value is less than or equal to the second value
	 * @param <Y> The comparable type of the values being compared by this predicate
	 */
	public <Y extends Comparable<? super Y>> K2Predicate lessThanOrEqualTo(K2Expression<? extends Y> expr, Y comp) {
		return new PredicateLessThanOrEqualTo(expr, comp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param patternExp		The regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public K2Predicate like(K2Expression<String> stringExp, K2Expression<String> patternExp) {
		return new PredicateLike(stringExp, patternExp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param pattern	The regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public K2Predicate like(K2Expression<String> stringExp, String pattern) {
		return new PredicateLike(stringExp, pattern);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param patternExp		The regular expression
	 * @param escapeCharExp	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public K2Predicate like(K2Expression<String> stringExp, K2Expression<String> patternExp, K2Expression<Character> escapeCharExp) {
		return new PredicateLike(stringExp, patternExp, escapeCharExp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param patternExp		The regular expression
	 * @param escapeChar	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public K2Predicate like(K2Expression<String> stringExp, K2Expression<String> patternExp, char escapeChar) {
		return new PredicateLike(stringExp, patternExp, escapeChar);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param pattern		The regular expression
	 * @param escapeCharExp	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public K2Predicate like(K2Expression<String> stringExp, String pattern, K2Expression<Character> escapeCharExp) {
		return new PredicateLike(stringExp, pattern, escapeCharExp);
	}

	/**
	 * Create a predicate that tests whether the string value matches the regular expression
	 * @param stringExp		The string to compare
	 * @param pattern		The regular expression
	 * @param escapeChar	The escape character of the regular expression
	 * @return	A predicate that check for a match between a string and a regular expression
	 */
	public K2Predicate like(K2Expression<String> stringExp, String pattern, char escapeChar) {
		return new PredicateLike(stringExp, pattern, escapeChar);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than another numeric value
	 * @param expr1	The first numeric value
	 * @param expr2	The second numeric value
	 * @return	A predicate that tests whether the first value is less that the second value
	 */
	public K2Predicate lt(K2Expression<? extends Number> expr1, K2Expression<? extends Number> expr2) {
		return new PredicateLT(expr1, expr2);
	}

	/**
	 * Create a predicate to test whether a numeric value is less than another numeric value
	 * @param expr	The first numeric value
	 * @param num	The second numeric value
	 * @return	A predicate that tests whether the first value is less that the second value
	 */
	public K2Predicate lt(K2Expression<? extends Number> expr, Number num) {
		return new PredicateLT(expr, num);
	}
	
	/**
	 * Create a predicate to negate a boolean expression
	 * @param expr	The boolean expression to negate
	 * @return	A predicate to negate a boolean expression
	 */
	public K2Predicate not(K2Expression<Boolean> expr) {
		return new PredicateNot(expr);
	}

	/**
	 * Create a predicate to test whether a value is not null
	 * @param expr	The value to check for null
	 * @return	A predicate to test whther a value is not null
	 */
	public K2Predicate isNotNull(K2Expression<?> expr) {
		return new PredicateNotNull(expr);
	}

	/**
	 * Create a predicate to test whether a value is null
	 * @param expr	The value to check for null
	 * @return	A predicate that tests whether a value is null
	 */
	public K2Predicate isNull(K2Expression<?> expr) {
		return new PredicateNull(expr);
	}

	/**
	 * Create a compound disjoint predicate
	 * @param predicates		The list of predicates to be disjointed together
	 * @return	A compound disjoint predicate
	 */
	public K2Predicate or(K2Predicate... predicates) {
		return new PredicateOr(predicates);
	}

	/**
	 * Create a compound disjoint predicate
	 * @param boolExpr1	The first boolean expression
	 * @param boolExpr2	The second boolean expression
	 * @return	A compound disjoint predicate
	 */
	public K2Predicate or(K2Expression<Boolean> boolExpr1, K2Expression<Boolean> boolExpr2) {
		return new PredicateOr(boolExpr1, boolExpr2);
	}



}

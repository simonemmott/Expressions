package com.k2.Expressions.predicate;

import java.util.Collection;

import com.k2.Expressions.expression.Expression;

public class PredicateBuilder {

	public PredicateBuilder() { }
	
	public Predicate and(Predicate... predicates) {
		return new PredicateAnd(predicates);
	}

	public Predicate and(Expression<Boolean> boolExpr1, Expression<Boolean> boolExpr2) {
		return new PredicateAnd(boolExpr1, boolExpr2);
	}

	public <Y extends Comparable<? super Y>> Predicate between(	Expression<? extends Y> check,
																Expression<? extends Y> beginExpr, 
																Expression<? extends Y> endExpr) {
		return new PredicateBetween(check, beginExpr, endExpr);
	}
	
	public <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> check, Y begin, Y end) {
		return new PredicateBetween(check, begin, end);
	}

	public  Predicate equals(Expression<?> expr1, Expression<?> expr2) {
		return new PredicateEquals(expr1, expr2);
	}

	public  Predicate equals(Expression<?> expr, Object obj) {
		return new PredicateEquals(expr, obj);
	}
	
	public Predicate ge(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateGE(expr1, expr2);
	}

	public Predicate ge(Expression<? extends Number> expr, Number num) {
		return new PredicateGE(expr, num);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateGreaterThan(expr1, expr2);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> expr, Y comp) {
		return new PredicateGreaterThan(expr, comp);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateGreaterThanOrEqualTo(expr1, expr2);
	}

	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> expr, Y comp) {
		return new PredicateGreaterThanOrEqualTo(expr, comp);
	}

	public Predicate gt(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateGT(expr1, expr2);
	}

	public Predicate gt(Expression<? extends Number> expr, Number num) {
		return new PredicateGT(expr, num);
	}

	public Predicate in(Expression<?> expr, Object ... objects) {
		return new PredicateIn(expr, objects);
	}
	
	public Predicate in(Expression<?> expr, Expression<?>... expressions) {
		return new PredicateIn(expr, expressions);
	}
	
	public Predicate in(Expression<?> expr, Collection<?> collection) {
		return new PredicateIn(expr, collection);
	}
	
	public Predicate in(Expression<?> expr, Expression<Collection<?>> collectionExpr) {
		return new PredicateIn(expr, collectionExpr);
	}
	
	public Predicate isFalse() {
		return new PredicateFalse();
	}
	
	public Predicate isFalse(Expression<Boolean> bool) {
		return new PredicateIsFalse(bool);
	}
	
	public Predicate isTrue() {
		return new PredicateTrue();
	}

	public Predicate isTrue(Expression<Boolean> bool) {
		return new PredicateIsTrue(bool);
	}

	public Predicate le(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateLE(expr1, expr2);
	}

	public Predicate le(Expression<? extends Number> expr, Number num) {
		return new PredicateLE(expr, num);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateLessThan(expr1, expr2);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> expr, Y comp) {
		return new PredicateLessThan(expr, comp);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> expr1, Expression<? extends Y> expr2) {
		return new PredicateLessThanOrEqualTo(expr1, expr2);
	}

	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> expr, Y comp) {
		return new PredicateLessThanOrEqualTo(expr, comp);
	}

	public Predicate like(Expression<String> stringExp, Expression<String> patternExp) {
		return new PredicateLike(stringExp, patternExp);
	}

	public Predicate like(Expression<String> stringExp, String pattern) {
		return new PredicateLike(stringExp, pattern);
	}

	public Predicate like(Expression<String> stringExp, Expression<String> patternExp, Expression<Character> escapeCharExp) {
		return new PredicateLike(stringExp, patternExp, escapeCharExp);
	}

	public Predicate like(Expression<String> stringExp, Expression<String> patternExp, char escapeChar) {
		return new PredicateLike(stringExp, patternExp, escapeChar);
	}

	public Predicate like(Expression<String> stringExp, String pattern, Expression<Character> escapeCharExp) {
		return new PredicateLike(stringExp, pattern, escapeCharExp);
	}

	public Predicate like(Expression<String> stringExp, String pattern, char escapeChar) {
		return new PredicateLike(stringExp, pattern, escapeChar);
	}

	public Predicate lt(Expression<? extends Number> expr1, Expression<? extends Number> expr2) {
		return new PredicateLT(expr1, expr2);
	}

	public Predicate lt(Expression<? extends Number> expr, Number num) {
		return new PredicateLT(expr, num);
	}
	
	public Predicate not(Expression<Boolean> expr) {
		return new PredicateNot(expr);
	}

	public Predicate isNotNull(Expression<?> expr) {
		return new PredicateNotNull(expr);
	}

	public Predicate isNull(Expression<?> expr) {
		return new PredicateNull(expr);
	}

	public Predicate or(Predicate... predicates) {
		return new PredicateOr(predicates);
	}

	public Predicate or(Expression<Boolean> boolExpr1, Expression<Boolean> boolExpr2) {
		return new PredicateOr(boolExpr1, boolExpr2);
	}



}

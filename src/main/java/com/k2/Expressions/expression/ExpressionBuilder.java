package com.k2.Expressions.expression;

import javax.persistence.criteria.CriteriaBuilder.Trimspec;

import com.k2.Util.ClassUtil;

public class ExpressionBuilder {

	public ExpressionBuilder() {}
	
	public <T> ParameterExpression<T> param(Class<T> cls, String name) {
		return new ParameterExpression<T>(cls, name);
	}
	
	public <N extends Number> ExprAbsolute<N> abs(Expression<N> num) {
		return new ExprAbsolute<N>(num);
	}
	
	public ExprConcatenate concatenate(Expression<String> stringExp1, Expression<String> stringExp2) {
		return new ExprConcatenate(stringExp1, stringExp2);
	}

	public ExprConcatenate concatenate(Expression<String> stringExp1, String string2) {
		return new ExprConcatenate(stringExp1, string2);
	}

	public ExprConcatenate concatenate(String string1, Expression<String> stringExp2) {
		return new ExprConcatenate(string1, stringExp2);
	}
	
	public ExprCurrentDate currentDate() {
		return new ExprCurrentDate();
	}
	
	public ExprCurrentTime currentTime() {
		return new ExprCurrentTime();
	}
	
	public ExprCurrentTimestamp currentTimestamp() {
		return new ExprCurrentTimestamp();
	}
	
	public <N extends Number> ExprDifference<? extends Number> diff(Expression<? extends N> numExp1, Expression<? extends N> numExp2) {
		return new ExprDifference<N>(numExp1, numExp2);
	}
	
	public <N extends Number> ExprDifference<N> diff(Expression<? extends N> numExp1, N num2) {
		return new ExprDifference<N>(numExp1, num2);
	}
	
	public <N extends Number> ExprDifference<N> diff(N num1, Expression<? extends N> numExp2) {
		return new ExprDifference<N>(num1, numExp2);
	}
	
	public ExprLength length(Expression<String> string) {
		return new ExprLength(string);
	}
	
	public <T> ExprLiteral<T> literal(T literal) {
		return new ExprLiteral<T>(literal);
	}

	public ExprLocate locate(Expression<String> string, Expression<String> pattern) {
		return new ExprLocate(string, pattern);
	}

	public ExprLocate locate(Expression<String> string, String pattern) {
		return new ExprLocate(string, pattern);
	}

	public ExprLocate locate(Expression<String> string, Expression<String> pattern, Expression<Integer> from) {
		return new ExprLocate(string, pattern, from);
	}

	public ExprLocate locate(Expression<String> string, String pattern, int from) {
		return new ExprLocate(string, pattern, from);
	}

	public ExprLower lower(Expression<String> string) {
		return new ExprLower(string);
	}

	public ExprMod mod(Expression<Integer> num1, Expression<Integer> num2) {
		return new ExprMod(num1, num2);
	}

	public ExprMod mod(Expression<Integer> num1, Integer num2) {
		return new ExprMod(num1, num2);
	}

	public ExprMod mod(Integer num1, Expression<Integer> num2) {
		return new ExprMod(num1, num2);
	}

	public <N extends Number> ExprNeg<N> neg(Expression<N> num) {
		return new ExprNeg<N>(num);
	}

	public <Y> ExprNullIf<Y> nullIf(Expression<Y> expr1, Expression<?> expr2) {
		return new  ExprNullIf<Y>(expr1, expr2);
	}

	public <Y> ExprNullIf<Y> nullIf(Expression<Y> expr1, Y value2) {
		return new  ExprNullIf<Y>(expr1, value2);
	}

	public <T> ExprNullLiteral<T> nullLiteral(Class<T> cls) {
		return new  ExprNullLiteral<T>(cls);
	}

	public <N extends Number> ExprProd<N> prod(Expression<? extends N> num1, Expression<? extends N> num2) {
		return new  ExprProd<N>(num1, num2);
	}

	public <N extends Number> ExprProd<N> prod(Expression<? extends N> num1, N num2) {
		return new  ExprProd<N>(num1, num2);
	}

	public <N extends Number> ExprProd<N> prod(N num1, Expression<? extends N> num2) {
		return new  ExprProd<N>(num1, num2);
	}

	public ExprQuot quot(Expression<? extends Number> num1, Expression<? extends Number> num2) {
		return new  ExprQuot(num1, num2);
	}

	public ExprQuot quot(Expression<? extends Number> num1, Number num2) {
		return new  ExprQuot(num1, num2);
	}

	public ExprQuot quot(Number num1, Expression<? extends Number> num2) {
		return new  ExprQuot(num1, num2);
	}

	public ExprSqrt sqrt(Expression<? extends Number> num) {
		return new  ExprSqrt(num);
	}

	public ExprSubstring substring(Expression<String> string, Expression<Integer> from) {
		return new  ExprSubstring(string, from);
	}

	public ExprSubstring substring(Expression<String> string, int from) {
		return new  ExprSubstring(string, from);
	}

	public ExprSubstring substring(Expression<String> string, Expression<Integer> from, Expression<Integer> length) {
		return new  ExprSubstring(string, from, length);
	}

	public ExprSubstring substring(Expression<String> string, int from, int length) {
		return new  ExprSubstring(string, from, length);
	}

	public ExprToBigDecimal toBigDecimal(Expression<? extends Number> num) {
		return new  ExprToBigDecimal(num);
	}

	public ExprToBigInteger toBigInteger(Expression<? extends Number> num) {
		return new  ExprToBigInteger(num);
	}

	public ExprToBoolean toBoolean(Expression<?> expr) {
		return new  ExprToBoolean(expr);
	}

	public ExprToDate toDate(Expression<?> expr) {
		return new  ExprToDate(expr);
	}

	public ExprToDate toDate(Expression<?> expr, Expression<String> format) {
		return new  ExprToDate(expr, format);
	}

	public ExprToDate toDate(Expression<?> expr, String format) {
		return new  ExprToDate(expr, format);
	}

	public ExprToDouble toDouble(Expression<?> expr) {
		return new  ExprToDouble(expr);
	}

	public ExprToFloat toFloat(Expression<?> expr) {
		return new  ExprToFloat(expr);
	}

	public ExprToInteger toInteger(Expression<?> expr) {
		return new  ExprToInteger(expr);
	}

	public ExprToLong toLong(Expression<?> expr) {
		return new  ExprToLong(expr);
	}

	public ExprToString toString(Expression<?> expr) {
		return new  ExprToString(expr);
	}

	public ExprTrim trim(Expression<String> expr) {
		return new  ExprTrim(expr);
	}

	public ExprTrim trim(Expression<Character> trimChar, Expression<String> string) {
		return new  ExprTrim(trimChar, string);
	}

	public ExprTrim trim(char trimChar, Expression<String> string) {
		return new  ExprTrim(trimChar, string);
	}

	public ExprTrim trim(Trimspec trimSpec, Expression<Character> trimChar, Expression<String> string) {
		return new  ExprTrim(trimSpec, trimChar, string);
	}

	public ExprTrim trim(Trimspec trimSpec, char trimChar, Expression<String> string) {
		return new  ExprTrim(trimSpec, trimChar, string);
	}

	public ExprTrim trim(Trimspec trimSpec, Expression<String> string) {
		return new  ExprTrim(trimSpec, string);
	}

	public ExprUpper upper(Expression<String> string) {
		return new ExprUpper(string);
	}

	public <E,T> Expression<T> get(Class<E> entityCls, Class<T> fieldCls, String alias) {
		return new GetterExpression<E,T>(ClassUtil.getGetter(entityCls, fieldCls, alias));
	}



	

}

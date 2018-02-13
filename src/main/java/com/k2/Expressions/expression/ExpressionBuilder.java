package com.k2.Expressions.expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.Trimspec;

import com.k2.Util.ClassUtil;

public class ExpressionBuilder {

	public ExpressionBuilder() {}
	
	public <T> ParameterExpression<T> param(Class<T> cls, String name) {
		return new ParameterExpression<T>(cls, name);
	}
	
	public <N extends Number> Expression<N> abs(Expression<N> num) {
		return new ExprAbsolute<N>(num);
	}
	
	public Expression<String> concatenate(Expression<String> stringExp1, Expression<String> stringExp2) {
		return new ExprConcatenate(stringExp1, stringExp2);
	}

	public Expression<String> concatenate(Expression<String> stringExp1, String string2) {
		return new ExprConcatenate(stringExp1, string2);
	}

	public Expression<String> concatenate(String string1, Expression<String> stringExp2) {
		return new ExprConcatenate(string1, stringExp2);
	}
	
	public Expression<Date> currentDate() {
		return new ExprCurrentDate();
	}
	
	public Expression<Time> currentTime() {
		return new ExprCurrentTime();
	}
	
	public Expression<Timestamp> currentTimestamp() {
		return new ExprCurrentTimestamp();
	}
	
	public <N extends Number> Expression<? extends Number> diff(Expression<? extends N> numExp1, Expression<? extends N> numExp2) {
		return new ExprDifference<N>(numExp1, numExp2);
	}
	
	public <N extends Number> Expression<N> diff(Expression<? extends N> numExp1, N num2) {
		return new ExprDifference<N>(numExp1, num2);
	}
	
	public <N extends Number> Expression<N> diff(N num1, Expression<? extends N> numExp2) {
		return new ExprDifference<N>(num1, numExp2);
	}
	
	public Expression<Integer> length(Expression<String> string) {
		return new ExprLength(string);
	}
	
	public <T> Expression<T> literal(T literal) {
		return new ExprLiteral<T>(literal);
	}

	public Expression<Integer> locate(Expression<String> string, Expression<String> pattern) {
		return new ExprLocate(string, pattern);
	}

	public Expression<Integer> locate(Expression<String> string, String pattern) {
		return new ExprLocate(string, pattern);
	}

	public Expression<Integer> locate(Expression<String> string, Expression<String> pattern, Expression<Integer> from) {
		return new ExprLocate(string, pattern, from);
	}

	public Expression<Integer> locate(Expression<String> string, String pattern, int from) {
		return new ExprLocate(string, pattern, from);
	}

	public Expression<String> lower(Expression<String> string) {
		return new ExprLower(string);
	}

	public Expression<Integer> mod(Expression<Integer> num1, Expression<Integer> num2) {
		return new ExprMod(num1, num2);
	}

	public Expression<Integer> mod(Expression<Integer> num1, Integer num2) {
		return new ExprMod(num1, num2);
	}

	public Expression<Integer> mod(Integer num1, Expression<Integer> num2) {
		return new ExprMod(num1, num2);
	}

	public <N extends Number> Expression<N> neg(Expression<N> num) {
		return new ExprNeg<N>(num);
	}

	public <Y> Expression<Y> nullIf(Expression<Y> expr1, Expression<?> expr2) {
		return new  ExprNullIf<Y>(expr1, expr2);
	}

	public <Y> Expression<Y> nullIf(Expression<Y> expr1, Y value2) {
		return new  ExprNullIf<Y>(expr1, value2);
	}

	public <T> Expression<T> nullLiteral(Class<T> cls) {
		return new  ExprNullLiteral<T>(cls);
	}

	public <N extends Number> Expression<N> prod(Expression<? extends N> num1, Expression<? extends N> num2) {
		return new  ExprProd<N>(num1, num2);
	}

	public <N extends Number> Expression<N> prod(Expression<? extends N> num1, N num2) {
		return new  ExprProd<N>(num1, num2);
	}

	public <N extends Number> Expression<N> prod(N num1, Expression<? extends N> num2) {
		return new  ExprProd<N>(num1, num2);
	}

	public Expression<Double> quot(Expression<? extends Number> num1, Expression<? extends Number> num2) {
		return new  ExprQuot(num1, num2);
	}

	public Expression<Double> quot(Expression<? extends Number> num1, Number num2) {
		return new  ExprQuot(num1, num2);
	}

	public Expression<Double> quot(Number num1, Expression<? extends Number> num2) {
		return new  ExprQuot(num1, num2);
	}

	public Expression<Double> sqrt(Expression<? extends Number> num) {
		return new  ExprSqrt(num);
	}

	public Expression<String> substring(Expression<String> string, Expression<Integer> from) {
		return new  ExprSubstring(string, from);
	}

	public Expression<String> substring(Expression<String> string, int from) {
		return new  ExprSubstring(string, from);
	}

	public Expression<String> substring(Expression<String> string, Expression<Integer> from, Expression<Integer> length) {
		return new  ExprSubstring(string, from, length);
	}

	public Expression<String> substring(Expression<String> string, int from, int length) {
		return new  ExprSubstring(string, from, length);
	}

	public Expression<BigDecimal> toBigDecimal(Expression<? extends Number> num) {
		return new  ExprToBigDecimal(num);
	}

	public Expression<BigInteger> toBigInteger(Expression<? extends Number> num) {
		return new  ExprToBigInteger(num);
	}

	public Expression<Boolean> toBoolean(Expression<?> expr) {
		return new  ExprToBoolean(expr);
	}

	public Expression<Date> toDate(Expression<?> expr) {
		return new  ExprToDate(expr);
	}

	public Expression<Date> toDate(Expression<?> expr, Expression<String> format) {
		return new  ExprToDate(expr, format);
	}

	public Expression<Date> toDate(Expression<?> expr, String format) {
		return new  ExprToDate(expr, format);
	}

	public Expression<Double> toDouble(Expression<?> expr) {
		return new  ExprToDouble(expr);
	}

	public Expression<Float> toFloat(Expression<?> expr) {
		return new  ExprToFloat(expr);
	}

	public Expression<Integer> toInteger(Expression<?> expr) {
		return new  ExprToInteger(expr);
	}

	public Expression<Long> toLong(Expression<?> expr) {
		return new  ExprToLong(expr);
	}

	public Expression<String> toString(Expression<?> expr) {
		return new  ExprToString(expr);
	}

	public Expression<String> trim(Expression<String> expr) {
		return new  ExprTrim(expr);
	}

	public Expression<String> trim(Expression<Character> trimChar, Expression<String> string) {
		return new  ExprTrim(trimChar, string);
	}

	public Expression<String> trim(char trimChar, Expression<String> string) {
		return new  ExprTrim(trimChar, string);
	}

	public Expression<String> trim(Trimspec trimSpec, Expression<Character> trimChar, Expression<String> string) {
		return new  ExprTrim(trimSpec, trimChar, string);
	}

	public Expression<String> trim(Trimspec trimSpec, char trimChar, Expression<String> string) {
		return new  ExprTrim(trimSpec, trimChar, string);
	}

	public Expression<String> trim(Trimspec trimSpec, Expression<String> string) {
		return new  ExprTrim(trimSpec, string);
	}

	public Expression<String> upper(Expression<String> string) {
		return new ExprUpper(string);
	}

	public <E,T> Expression<T> get(Class<E> entityCls, Class<T> fieldCls, String alias) {
		return new GetterExpression<E,T>(ClassUtil.getGetter(entityCls, fieldCls, alias));
	}



	

}

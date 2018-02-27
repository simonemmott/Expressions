package com.k2.Expressions.expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.Trimspec;

import com.k2.Util.classes.ClassUtil;

/**
 * The expression builder provides factory methods to create implementations of the Expression interface that provide
 * known functions
 * 
 * @author simon
 *
 */
public class ExpressionBuilder {

	/**
	 * Create an instance of the expression builder
	 */
	public ExpressionBuilder() {}
	
	/**
	 * Create a parameter expression for the given class and name
	 * @param cls	The class of the value represented by the parameter expression
	 * @param name	The name of the parameter expression
	 * @return		A parameter expression for the given values
	 * @param <T>	The class of the value represented by this parameter expression
	 */
	public <T> ParameterExpression<T> param(Class<T> cls, String name) {
		return new ParameterExpression<T>(cls, name);
	}
	
	/**
	 * Create an expression that derives its value from the getter provided by a GetterEvaluator
	 * @param entityCls	The class of the object through which the getter will be evaluated
	 * @param fieldCls	The class of the field that is to be got by the getter expression
	 * @param alias		The alias of the field that is to be got by the getter expression
	 * @return			An expression that derives its values from the getter provided by a GetterEvaluator
	 * @param <E>	The class of the object through which the getter will be evaluated
	 * @param <T>	The class of the field that is to be got by the getter expression
	 */
	public <E,T> K2Expression<T> get(Class<E> entityCls, Class<T> fieldCls, String alias) {
		return new GetterExpression<E,T>(ClassUtil.getGetter(entityCls, fieldCls, alias));
	}

	/**
	 * Create an expression that provides a mathematical absolute function
	 * @param num	The expression whose expressed value is to be converted to its absolute value
	 * @return		An expression to execute a mathematical absolute function
	 * @param <N>	The numeric type of the expression
	 */
	public <N extends Number> K2Expression<N> abs(K2Expression<N> num) {
		return new ExprAbsolute<N>(num);
	}
	
	/**
	 * Create a concatenation expression that concatenates the string expressions together
	 * @param stringExp1		The first string expression
	 * @param stringExp2		The second string expression
	 * @return				An expression the concatenates the expressed values together
	 */
	public K2Expression<String> concatenate(K2Expression<String> stringExp1, K2Expression<String> stringExp2) {
		return new ExprConcatenate(stringExp1, stringExp2);
	}

	/**
	 *  Create a concatenation expression that concatenates the string expressions together
	 * @param stringExp1		The string expression
	 * @param string2		The string literal
	 * @return				An expression the concatenates the expressed values together
	 */
	public K2Expression<String> concatenate(K2Expression<String> stringExp1, String string2) {
		return new ExprConcatenate(stringExp1, string2);
	}

	/**
	 *  Create a concatenation expression that concatenates the string expressions together
	 * @param string1		The string literal
	 * @param stringExp2		The string expression
	 * @return	An expression the concatenates the expressed values together
	 */
	public K2Expression<String> concatenate(String string1, K2Expression<String> stringExp2) {
		return new ExprConcatenate(string1, stringExp2);
	}
	
	/**
	 * Create a concatenation expression that concatenates the given objects together
	 * @param stringSources	An array of objects whose values will be concatenated together
	 * @return	An expression the concatenates the expressed values together
	 */
	public K2Expression<String> concatenate(Object ... stringSources) {
		return new ExprConcatenate(stringSources);
	}
	
	/**
	 * Create an expression to return the current date
	 * @return	An expression that returns the current date
	 */
	public K2Expression<Date> currentDate() {
		return new ExprCurrentDate();
	}
	
	/**
	 * Create an expression to return the current time
	 * @return	An expression that returns the current time
	 */
	public K2Expression<Time> currentTime() {
		return new ExprCurrentTime();
	}
	
	/**
	 * Create an expression to return the current timestamp
	 * @return	An expression that returns the current timestamp
	 */
	public K2Expression<Timestamp> currentTimestamp() {
		return new ExprCurrentTimestamp();
	}
	
	/**
	 * Create an expression to return the difference between two values
	 * @param numExp1	The first numerical expression
	 * @param numExp2	The second numerical expression
	 * @return	An expression that returns the difference between the two values
	 * @param <N> The type of the numerical value
	 */
	public <N extends Number> K2Expression<? extends Number> diff(K2Expression<? extends N> numExp1, K2Expression<? extends N> numExp2) {
		return new ExprDifference<N>(numExp1, numExp2);
	}
	
	/**
	 * Create an expression to return the difference between two values
	 * @param numExp1	The numerical expression
	 * @param num2	The numerical literal
	 * @return	An expression that returns the difference between the two values
	 * @param <N> The type of the numerical value
	 */
	public <N extends Number> K2Expression<N> diff(K2Expression<? extends N> numExp1, N num2) {
		return new ExprDifference<N>(numExp1, num2);
	}
	
	/**
	 * Create an expression to return the difference between two values
	 * @param num1	The numerical literal
	 * @param numExp2	The numerical expression
	 * @return	An expression that returns the difference between the two values
	 * @param <N> The type of the numerical value
	 */
	public <N extends Number> K2Expression<N> diff(N num1, K2Expression<? extends N> numExp2) {
		return new ExprDifference<N>(num1, numExp2);
	}
	
	/**
	 * Create an expression to return the length of a String 
	 * @param string		The Sting expression
	 * @return			An expression to return the length of a string
	 */
	public K2Expression<Integer> length(K2Expression<String> string) {
		return new ExprLength(string);
	}
	
	/**
	 * Create an expressed literal value
	 * @param literal	The literal value to encapsulate as an expression
	 * @return	An expression that provides a literal value
	 * @param <T> The type of the literal value
	 */
	public <T> K2Expression<T> literal(T literal) {
		return new ExprLiteral<T>(literal);
	}

	/**
	 * Create an expression to return the location of one string in another or 0 if the pattern is not contained in the string
	 * @param string		The expression providing the string in which to search for the pattern
	 * @param pattern	The expression providing the pattern to search for in the string
	 * @return		An expression to search for a pattern in a string. The integer returned by this expression identifies the 
	 * beginning of the string as 1 and a 0 value indicates that the pattern does not exist in the string
	 */
	public K2Expression<Integer> locate(K2Expression<String> string, K2Expression<String> pattern) {
		return new ExprLocate(string, pattern);
	}

	/**
	 * Create an expression to return the location of one string in another or 0 if the pattern is not contained in the string
	 * @param string		The expression providing the string in which to search for the pattern
	 * @param pattern	The pattern to search for in the string
	 * @return		An expression to search for a pattern in a string. The integer returned by this expression identifies the 
	 * beginning of the string as 1 and a 0 value indicates that the pattern does not exist in the string
	 */
	public K2Expression<Integer> locate(K2Expression<String> string, String pattern) {
		return new ExprLocate(string, pattern);
	}

	/**
	 * Create an expression to return the location of one string in another or 0 if the pattern is not contained in the string
	 * @param string		The expression providing the string in which to search for the pattern
	 * @param pattern	The expression providing the pattern to search for in the string
	 * @param from		The expression providing the location in the string from which to start the search
	 * @return		An expression to search for a pattern in a string. The integer returned by this expression identifies the 
	 * beginning of the string as 1 and a 0 value indicates that the pattern does not exist in the string
	 */
	public K2Expression<Integer> locate(K2Expression<String> string, K2Expression<String> pattern, K2Expression<Integer> from) {
		return new ExprLocate(string, pattern, from);
	}

	/**
	 * Create an expression to return the location of one string in another or 0 if the pattern is not contained in the string
	 * @param string		The expression providing the string in which to search for the pattern
	 * @param pattern	The pattern to search for in the string
	 * @param from		The location in the string from which to start the search
	 * @return		An expression to search for a pattern in a string. The integer returned by this expression identifies the 
	 * beginning of the string as 1 and a 0 value indicates that the pattern does not exist in the string
	 */
	public K2Expression<Integer> locate(K2Expression<String> string, String pattern, int from) {
		return new ExprLocate(string, pattern, from);
	}

	/**
	 * Create an expression to convert a sting to lowercase
	 * @param string		An expression providing the string to convert to lower case
	 * @return			An expression to convert a string to lower case
	 */
	public K2Expression<String> lower(K2Expression<String> string) {
		return new ExprLower(string);
	}

	/**
	 * Create an expression to provide the mathematical modulus function
	 * @param num1	The numerator
	 * @param num2	The denominator
	 * @return	An expression providing the mathematical modulus function
	 */
	public K2Expression<Integer> mod(K2Expression<Integer> num1, K2Expression<Integer> num2) {
		return new ExprMod(num1, num2);
	}

	/**
	 * Create an expression to provide the mathematical modulus function
	 * @param num1	The numerator
	 * @param num2	The denominator
	 * @return	An expression providing the mathematical modulus function
	 */
	public K2Expression<Integer> mod(K2Expression<Integer> num1, Integer num2) {
		return new ExprMod(num1, num2);
	}

	/**
	 * Create an expression to provide the mathematical modulus function
	 * @param num1	The numerator
	 * @param num2	The denominator
	 * @return	An expression providing the mathematical modulus function
	 */
	public K2Expression<Integer> mod(Integer num1, K2Expression<Integer> num2) {
		return new ExprMod(num1, num2);
	}

	/**
	 * Create an expression to provide the negative value of a number
	 * @param num	The expression providing the number to negate
	 * @return		An expression that calculates the negative of a number
	 * @param <N> The numerical type of the expression
	 */
	public <N extends Number> K2Expression<N> neg(K2Expression<N> num) {
		return new ExprNeg<N>(num);
	}

	/**
	 * Create an expression to provide the nullIf function
	 * 
	 * The nullIf function returns null if the first argument is null or the two arguments are equal, otherwise it returns the first 
	 * argument
	 * @param expr1	An expression providing the first argument
	 * @param expr2	An expression providing the second argument
	 * @return	An expression providing the nullIf function
	 * @param <Y> The type of the value returned by the nullIf expression
	 */
	public <Y> K2Expression<Y> nullIf(K2Expression<Y> expr1, K2Expression<?> expr2) {
		return new  ExprNullIf<Y>(expr1, expr2);
	}

	/**
	 * Create an expression to provide the nullIf function
	 * 
	 * The nullIf function returns null if the first argument is null or the two arguments are equal, otherwise it returns the first 
	 * argument
	 * @param expr1		An expression providing the first argument
	 * @param value2		An literal value providing the second argument
	 * @return	An expression providing the nullIf function
	 * @param <Y> The type of the value returned by the nullIf expression
	 */
	public <Y> K2Expression<Y> nullIf(K2Expression<Y> expr1, Y value2) {
		return new  ExprNullIf<Y>(expr1, value2);
	}

	/**
	 * Create an expression to return a typed null value
	 * @param cls	The type of the null value required
	 * @return	A expression returning a typed null value
	 * @param <T> The type of the null value returned by the expression
	 */
	public <T> K2Expression<T> nullLiteral(Class<T> cls) {
		return new  ExprNullLiteral<T>(cls);
	}

	/**
	 * Create an expression to provide the product of two values
	 * @param num1	The first value
	 * @param num2	The second value
	 * @return	An expression that returns the product of two values
	 * @param <N> The numerical type of value returned by the expression
	 */
	public <N extends Number> K2Expression<N> prod(K2Expression<? extends N> num1, K2Expression<? extends N> num2) {
		return new  ExprProd<N>(num1, num2);
	}

	/**
	 * Create an expression to provide the product of two values
	 * @param num1	The first value
	 * @param num2	The second value
	 * @return	An expression that returns the product of two values
	 * @param <N> The numerical type of value returned by the expression
	 */
	public <N extends Number> K2Expression<N> prod(K2Expression<? extends N> num1, N num2) {
		return new  ExprProd<N>(num1, num2);
	}

	/**
	 * Create an expression to provide the product of two values
	 * @param num1	The first value
	 * @param num2	The second value
	 * @return	An expression that returns the product of two values
	 * @param <N> The numerical type of value returned by the expression
	 */
	public <N extends Number> K2Expression<N> prod(N num1, K2Expression<? extends N> num2) {
		return new  ExprProd<N>(num1, num2);
	}

	/**
	 * Create an expression to provide the quotient of two values
	 * @param num1	The first value
	 * @param num2	The second value
	 * @return	An expression that returns the quotient of two values
	 */
	public K2Expression<Double> quot(K2Expression<? extends Number> num1, K2Expression<? extends Number> num2) {
		return new  ExprQuot(num1, num2);
	}

	/**
	 * Create an expression to provide the quotient of two values
	 * @param num1	The first value
	 * @param num2	The second value
	 * @return	An expression that returns the quotient of two values
	 */
	public K2Expression<Double> quot(K2Expression<? extends Number> num1, Number num2) {
		return new  ExprQuot(num1, num2);
	}

	/**
	 * Create an expression to provide the quotient of two values
	 * @param num1	The first value
	 * @param num2	The second value
	 * @return	An expression that returns the quotient of two values
	 */
	public K2Expression<Double> quot(Number num1, K2Expression<? extends Number> num2) {
		return new  ExprQuot(num1, num2);
	}

	/**
	 * Create an expression to provide the square root of a numerical value
	 * @param num	An expression providing the numerical value of which to calculate the square root
	 * @return	An expression that calculates the square root of a number
	 */
	public K2Expression<Double> sqrt(K2Expression<? extends Number> num) {
		return new  ExprSqrt(num);
	}

	/**
	 * Create an expression to extract a substring from a given string
	 * @param string		The string from which to extract the substring
	 * @param from		The location within the string from which the extracted substring starts
	 * @return	An expression that extracts a substring from a string
	 */
	public K2Expression<String> substring(K2Expression<String> string, K2Expression<Integer> from) {
		return new  ExprSubstring(string, from);
	}

	/**
	 * Create an expression to extract a substring from a given string
	 * @param string		The string from which to extract the substring
	 * @param from		The location within the string from which the extracted substring starts
	 * @return	An expression that extracts a substring from a string
	 */
	public K2Expression<String> substring(K2Expression<String> string, int from) {
		return new  ExprSubstring(string, from);
	}

	/**
	 * Create an expression to extract a substring from a given string
	 * @param string		The string from which to extract the substring
	 * @param from		The location within the string from which the extracted substring starts
	 * @param length		The length of the substring to extract
	 * @return	An expression that extracts a substring from a string
	 */
	public K2Expression<String> substring(K2Expression<String> string, K2Expression<Integer> from, K2Expression<Integer> length) {
		return new  ExprSubstring(string, from, length);
	}

	/**
	 * Create an expression to extract a substring from a given string
	 * @param string		The string from which to extract the substring
	 * @param from		The location within the string from which the extracted substring starts
	 * @param length		The length of the substring to extract
	 * @return	An expression that extracts a substring from a string
	 */
	public K2Expression<String> substring(K2Expression<String> string, int from, int length) {
		return new  ExprSubstring(string, from, length);
	}

	/**
	 * Create an expression to convert a number into a BigDecimal value
	 * @param num	The number to convert into a BigDecimal
	 * @return	An expression to generate a BigDecimal from a numeric value
	 */
	public K2Expression<BigDecimal> toBigDecimal(K2Expression<? extends Number> num) {
		return new  ExprToBigDecimal(num);
	}

	/**
	 * Create an expression to convert a number into a BigInteger value
	 * @param num	The number to convert into a BigInteger
	 * @return	An expression to generate a BigInteger from a numeric value
	 */
	public K2Expression<BigInteger> toBigInteger(K2Expression<? extends Number> num) {
		return new  ExprToBigInteger(num);
	}

	/**
	 * Create an expression to convert a expressed value into a Boolean value
	 * @param expr	The expression to convert into a Boolean
	 * @return	An expression to generate a Boolean from an expressed value
	 */
	public K2Expression<Boolean> toBoolean(K2Expression<?> expr) {
		return new  ExprToBoolean(expr);
	}

	/**
	 * Create an expression to convert a expressed value into a Date value
	 * @param expr	The expression to convert into a Date
	 * @return	An expression to generate a Date from an expressed value
	 */
	public K2Expression<Date> toDate(K2Expression<?> expr) {
		return new  ExprToDate(expr);
	}

	/**
	 * Create an expression to convert a expressed value into a Date value
	 * @param expr		The expression to convert into a Date
	 * @param format		The format through which the expressed value will be converted to a date
	 * @return	An expression to generate a Date from an expressed value
	 */
	public K2Expression<Date> toDate(K2Expression<?> expr, K2Expression<String> format) {
		return new  ExprToDate(expr, format);
	}

	/**
	 * Create an expression to convert a expressed value into a Date value
	 * @param expr		The expression to convert into a Date
	 * @param format		The format through which the expressed value will be converted to a date
	 * @return	An expression to generate a Date from an expressed value
	 */
	public K2Expression<Date> toDate(K2Expression<?> expr, String format) {
		return new  ExprToDate(expr, format);
	}

	/**
	 * Create an expression to convert a expressed value into a Double value
	 * @param expr	The expression to convert into a Double
	 * @return	An expression to generate a Double from an expressed value
	 */
	public K2Expression<Double> toDouble(K2Expression<?> expr) {
		return new  ExprToDouble(expr);
	}

	/**
	 * Create an expression to convert a expressed value into a Float value
	 * @param expr	The expression to convert into a Float
	 * @return	An expression to generate a Float from an expressed value
	 */
	public K2Expression<Float> toFloat(K2Expression<?> expr) {
		return new  ExprToFloat(expr);
	}

	/**
	 * Create an expression to convert a expressed value into an Integer value
	 * @param expr	The expression to convert into an Integer
	 * @return	An expression to generate an Integer from an expressed value
	 */
	public K2Expression<Integer> toInteger(K2Expression<?> expr) {
		return new  ExprToInteger(expr);
	}

	/**
	 * Create an expression to convert a expressed value into a Long value
	 * @param expr	The expression to convert into a Long
	 * @return	An expression to generate a Long from an expressed value
	 */
	public K2Expression<Long> toLong(K2Expression<?> expr) {
		return new  ExprToLong(expr);
	}

	/**
	 * Create an expression to convert a expressed value into a String value
	 * @param expr	The expression to convert into a String
	 * @return	An expression to generate a String from an expressed value
	 */
	public K2Expression<String> toString(K2Expression<?> expr) {
		return new  ExprToString(expr);
	}

	/**
	 * Create an expression to trim leading and trailing space characters from a string
	 * @param expr	The string to trim
	 * @return		An expression that trims leading and trailing space characters from a string
	 */
	public K2Expression<String> trim(K2Expression<String> expr) {
		return new  ExprTrim(expr);
	}

	/**
	 * Create an expression to trim leading and trailing characters from a string
	 * @param trimChar	The character to trim from the string
	 * @param string		The string to trim
	 * @return		An expression that trims leading and trailing characters from a string
	 */
	public K2Expression<String> trim(K2Expression<Character> trimChar, K2Expression<String> string) {
		return new  ExprTrim(trimChar, string);
	}

	/**
	 * Create an expression to trim leading and trailing characters from a string
	 * @param trimChar	The character to trim from the string
	 * @param string		The string to trim
	 * @return		An expression that trims leading and trailing characters from a string
	 */
	public K2Expression<String> trim(char trimChar, K2Expression<String> string) {
		return new  ExprTrim(trimChar, string);
	}

	/**
	 * Create an expression to trim leading and/or trailing characters from a string
	 * @param trimSpec	The enumeration that identifies whether to trim leading characters, trailing characters or both from the string
	 * @param trimChar	The characters to trim from the string
	 * @param string		The string to trim
	 * @return		An expression that trims leading and/or trailing characters from a string
	 */
	public K2Expression<String> trim(Trimspec trimSpec, K2Expression<Character> trimChar, K2Expression<String> string) {
		return new  ExprTrim(trimSpec, trimChar, string);
	}

	/**
	 * Create an expression to trim leading and/or trailing characters from a string
	 * @param trimSpec	The enumeration that identifies whether to trim leading characters, trailing characters or both from the string
	 * @param trimChar	The characters to trim from the string
	 * @param string		The string to trim
	 * @return		An expression that trims leading and/or trailing characters from a string
	 */
	public K2Expression<String> trim(Trimspec trimSpec, char trimChar, K2Expression<String> string) {
		return new  ExprTrim(trimSpec, trimChar, string);
	}

	/**
	 * Create an expression to trim leading and/or trailing space characters from a string
	 * @param trimSpec	The enumeration that identifies whether to trim leading characters, trailing characters or both from the string
	 * @param string		The string to trim
	 * @return		An expression that trims leading and/or trailing space characters from a string
	 */
	public K2Expression<String> trim(Trimspec trimSpec, K2Expression<String> string) {
		return new  ExprTrim(trimSpec, string);
	}

	/**
	 * Create an expression to convert a string into upper case
	 * @param string		The string to convert to upper case
	 * @return	An expression to convert a string into upper case
	 */
	public K2Expression<String> upper(K2Expression<String> string) {
		return new ExprUpper(string);
	}

}

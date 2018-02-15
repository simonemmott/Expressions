package com.k2.Expressions.expression;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.Evaluator;
import com.k2.Util.StringUtil;

/**
 * This implementation of the Expression interface provides implements an concatenate method that returns the
 * given values concatenated together
 * 
 * @author simon
 *
 */
public class ExprConcatenate extends AbstractExpression<String> implements Expression<String> {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Expression<String> stringExp1 = null;
	Expression<String> stringExp2 = null;
	String string1 = null;
	String string2 = null;
	Object[] stringSources = null;

	/**
	 * Create a concatenation expression for the given string expressions
	 * 
	 * @param stringExp1		The first string expression whose value is to be concatenated
	 * @param stringExp2		The second string expression whose value is to be concatenated
	 */
	public ExprConcatenate(Expression<String> stringExp1, Expression<String> stringExp2) {
		super(String.class);
		this.stringExp1 = stringExp1;
		this.stringExp2 = stringExp2;
	}

	/**
	 * Create a concatenation expression for the given string expression and literal string
	 * 	
	 * @param stringExp1		The string expression whose value is to be concatenated
	 * @param string2		The literal string value to be concatenated
	 */
	public ExprConcatenate(Expression<String> stringExp1, String string2) {
		super(String.class);
		this.stringExp1 = stringExp1;
		this.string2 = string2;
	}

	/**
	 * Create a concatenation expression for the given string expression and literal string
	 * 
	 * @param string1		The literal string value to be concatenated
	 * @param stringExp2		The string expression whose value is to be concatenated
	 */
	public ExprConcatenate(String string1, Expression<String> stringExp2) {
		super(String.class);
		this.string1 = string1;
		this.stringExp2 = stringExp2;
	}

	/**
	 * Create a concatenation expression for the given objects
	 * 
	 * When concatenating objects if the objects are String they are added to the concatenation
	 * If the object is a collection then each item in the collection is added to the concatenation
	 * Note the handling of collections does not recurse. i.e. collections of collections will not
	 * concatenate the contents of the sub collection
	 * If the object is an expression then the value of the expression is extracted and the value added 
	 * to the concatenation. If the expression returns a collection then the contents of the expressed
	 * collection are added to the concatenation as above
	 * If the object is none of the above or the collection is not a collection of strings then the 
	 * object is converted to a string using the StingUtil.toString(Object) method and the result added to
	 * the collection.
	 * 
	 * @param stringSources The Objects to be concatenated together.
	 */
	public ExprConcatenate(Object ... stringSources) {
		super(String.class);
		this.stringSources = stringSources;
	}

	@Override
	public String evaluate(Evaluator eval) {
		
		if (stringSources == null) {
			String s1 = string1;
			String s2 = string2;
			if (s1 == null) s1 = stringExp1.evaluate(eval);
			if (s2 == null) s2 = stringExp2.evaluate(eval);
			
			logger.trace("Concatenate: {} + {} = {}", s1, s2, s1+s2);
			
			return ((s1==null)?"":s1)+((s2==null)?"":s2);
		} else {
			StringBuilder sb = new StringBuilder();
			for (Object source : stringSources) {
				if (source == null) continue;
				if (source instanceof String) {
					sb.append((String)source);
				} else if (source instanceof Expression) {
					Object exprValue = ((Expression<?>)source).evaluate(eval);
					if (exprValue == null) continue;
					if (exprValue instanceof String) {
						sb.append((String)exprValue);
					} else if (exprValue instanceof Collection) {
						Iterator<?> i = ((Collection<?>)exprValue).iterator();
						while (i.hasNext()) {
							Object cObj = i.next();
							if (cObj == null) continue;
							if (cObj instanceof String) {
								sb.append((String)cObj);
							} else {
								sb.append(StringUtil.toString(cObj));
							}
						}
					} else {
						sb.append(StringUtil.toString(exprValue));
					}
				} else if (source instanceof Collection) {
					Iterator<?> i = ((Collection<?>)source).iterator();
					while (i.hasNext()) {
						Object cObj = i.next();
						if (cObj == null) continue;
						if (cObj instanceof String) {
							sb.append((String)cObj);
						} else {
							sb.append(StringUtil.toString(cObj));
						}
					}
				} else {
					sb.append(StringUtil.toString(source));
				}
			}
			return sb.toString();
		}
	}

}

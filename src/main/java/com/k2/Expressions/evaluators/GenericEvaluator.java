package com.k2.Expressions.evaluators;


import com.k2.Expressions.Evaluator;
import com.k2.Expressions.GetterEvaluator;
import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.expression.GetterExpression;
/**
 * The generic evaluator provides an implementation of the GetterEvaluator interface. It extends the SimpleParameterEvaluator
 * and as such can provide values for parameter expressions and getter expressions.
 * 
 * @author simon
 *
 * @param <E>	The type of the value source from which this evaluator will daw the values for getter expressions.
 */
public class GenericEvaluator<E> extends SimpleParameterEvaluator implements Evaluator, ParameterEvaluator, GetterEvaluator<E> {

	protected E valueSource;
	
	/**
	 * Create an instance of the generic evaluator without setting it's value source
	 */
	public GenericEvaluator () {}
	
	/**
	 * Create an instance of the generic evaluator setting its getter value source to the given object.
	 * 
	 * @param valueSource	The object from which to extract the values for getter expressions
	 */
	public GenericEvaluator(E valueSource) {
		this.valueSource = valueSource;
	}
	
	/**
	 * Change the value source of this generic evaluator
	 * 
	 * @param 	valueSource The new source of values for getter expressions
	 * @return	Returns this generic evaluator for method chaining
	 */
	public GenericEvaluator<E> with(E valueSource) {
		this.valueSource = valueSource;
		return this;
	}

	@Override
	public <T> T valueOf(GetterExpression<E, T> getterExpression) {
		return getterExpression.getter().get(valueSource);
	}

}

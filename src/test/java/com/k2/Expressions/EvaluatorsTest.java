package com.k2.Expressions;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.evaluators.GenericEvaluator;
import com.k2.Expressions.evaluators.ObjectOrParameterEvaluator;
import com.k2.Expressions.evaluators.ParamterOrObjectEvaluator;
import com.k2.Expressions.expression.ExpressionBuilder;
import com.k2.Expressions.predicate.Predicate;
import com.k2.Expressions.predicate.PredicateBuilder;
import com.k2.Util.StringUtil;

public class EvaluatorsTest {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private ExpressionBuilder xb = new ExpressionBuilder();
	private PredicateBuilder pb = new PredicateBuilder();
	
	class Foo {
		Long id;
		Integer intVal;
		Long longVal;
		Date dateVal;
		Boolean boolVal;
		Float floatVal;
		Double doubleVal;
		String strVal;
		
		Foo (	Long id,
				Integer intVal,
				Long longVal,
				Date dateVal,
				Boolean boolVal,
				Float floatVal,
				Double doubleVal,
				String strVal) {
			this.id = id;
			this.intVal = intVal;
			this.longVal = longVal;
			this.dateVal = dateVal;
			this.boolVal = boolVal;
			this.floatVal = floatVal;
			this.doubleVal = doubleVal;
			this.strVal = strVal;
		}
	}

	@Test
	public void genericEvaluatorTest()
    {
		Predicate predId = pb.equals(xb.get(Foo.class, Long.class, "id"), 1L);
		Predicate predIntVal = pb.equals(xb.get(Foo.class, Integer.class, "intVal"), 10);
		Predicate predLongVal = pb.equals(xb.get(Foo.class, Long.class, "longVal"), 123456789012345L);
		Predicate predDateVal = pb.equals(xb.get(Foo.class, Date.class, "dateVal"), StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		Predicate predBoolVal = pb.equals(xb.get(Foo.class, Boolean.class, "boolVal"), true);
		Predicate predFloatVal = pb.equals(xb.get(Foo.class, Float.class, "floatVal"), (float)1.234);
		Predicate predDoubleVal = pb.equals(xb.get(Foo.class, Double.class, "doubleVal"), 1234.5678);
		Predicate predStrVal = pb.equals(xb.get(Foo.class, String.class, "strVal"), xb.param(String.class, "p1"));
		
		Foo foo = new Foo(	1L, 
							10, 
							123456789012345L,
							StringUtil.toDate("2018/01/01", "yyyy/MM/dd"),
							true,
							(float)1.234,
							1234.5678,
							"Hello World!");
		
		GenericEvaluator<Foo> ge = new GenericEvaluator<Foo>(foo);
		
		ge.set(String.class, "p1", "Hello World!");
		
		assertTrue(predId.evaluate(ge));
		assertTrue(predIntVal.evaluate(ge));
		assertTrue(predLongVal.evaluate(ge));
		assertTrue(predDateVal.evaluate(ge));
		assertTrue(predBoolVal.evaluate(ge));
		assertTrue(predFloatVal.evaluate(ge));
		assertTrue(predDoubleVal.evaluate(ge));
		assertTrue(predStrVal.evaluate(ge));

    }

	@Test
	public void parameterOrObjectEvaluatorTest()
    {
		Predicate predId = pb.equals(xb.get(Foo.class, Long.class, "id"), 1L);
		Predicate predIntVal = pb.equals(xb.get(Foo.class, Integer.class, "intVal"), 10);
		Predicate predLongVal = pb.equals(xb.get(Foo.class, Long.class, "longVal"), 1000L);
		Predicate predDateVal = pb.equals(xb.get(Foo.class, Date.class, "dateVal"), StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		Predicate predBoolVal = pb.equals(xb.get(Foo.class, Boolean.class, "boolVal"), true);
		Predicate predFloatVal = pb.equals(xb.get(Foo.class, Float.class, "floatVal"), (float)1.234);
		Predicate predDoubleVal = pb.equals(xb.get(Foo.class, Double.class, "doubleVal"), 1234.5678);
		Predicate predStrVal = pb.equals(xb.get(Foo.class, String.class, "strVal"), xb.param(String.class, "p1"));
		
		Foo foo = new Foo(	1L, 
							10, 
							123456789012345L,
							StringUtil.toDate("2018/01/01", "yyyy/MM/dd"),
							true,
							(float)1.234,
							1234.5678,
							"Hello World!");
		
		ParamterOrObjectEvaluator<Foo> eval = new ParamterOrObjectEvaluator<Foo>(foo);
		
		eval.set(String.class, "p1", "Hello World!");
		eval.set(Long.class, "longVal", 1000L);
		
		assertTrue(predId.evaluate(eval));
		assertTrue(predIntVal.evaluate(eval));
		assertTrue(predLongVal.evaluate(eval));
		assertTrue(predDateVal.evaluate(eval));
		assertTrue(predBoolVal.evaluate(eval));
		assertTrue(predFloatVal.evaluate(eval));
		assertTrue(predDoubleVal.evaluate(eval));
		assertTrue(predStrVal.evaluate(eval));

    }

	@Test
	public void objectOrParameterEvaluatorTest()
    {
		Predicate predId = pb.equals(xb.get(Foo.class, Long.class, "id"), 1L);
		Predicate predIntVal = pb.equals(xb.get(Foo.class, Integer.class, "intVal"), 10);
		Predicate predLongVal = pb.equals(xb.param(Long.class, "longVal"), 123456789012345L);
		Predicate predDateVal = pb.equals(xb.get(Foo.class, Date.class, "dateVal"), StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		Predicate predBoolVal = pb.equals(xb.get(Foo.class, Boolean.class, "boolVal"), true);
		Predicate predFloatVal = pb.equals(xb.get(Foo.class, Float.class, "floatVal"), (float)1.234);
		Predicate predDoubleVal = pb.equals(xb.get(Foo.class, Double.class, "doubleVal"), 1234.5678);
		Predicate predStrVal = pb.equals(xb.get(Foo.class, String.class, "strVal"), xb.param(String.class, "p1"));
		
		Foo foo = new Foo(	1L, 
							10, 
							123456789012345L,
							StringUtil.toDate("2018/01/01", "yyyy/MM/dd"),
							true,
							(float)1.234,
							1234.5678,
							"Hello World!");
		
		ObjectOrParameterEvaluator<Foo> eval = new ObjectOrParameterEvaluator<Foo>(foo);
		
		eval.set(String.class, "p1", "Hello World!");
		eval.set(Long.class, "longVal", 1000L);
		
		assertTrue(predId.evaluate(eval));
		assertTrue(predIntVal.evaluate(eval));
		assertTrue(predLongVal.evaluate(eval));
		assertTrue(predDateVal.evaluate(eval));
		assertTrue(predBoolVal.evaluate(eval));
		assertTrue(predFloatVal.evaluate(eval));
		assertTrue(predDoubleVal.evaluate(eval));
		assertTrue(predStrVal.evaluate(eval));

    }



}

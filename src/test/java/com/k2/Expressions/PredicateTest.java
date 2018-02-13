package com.k2.Expressions;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.evaluators.SimpleParameterEvaluator;
import com.k2.Expressions.expression.ExpressionBuilder;
import com.k2.Expressions.predicate.Predicate;
import com.k2.Expressions.predicate.PredicateBuilder;
import com.k2.Util.StringUtil;

public class PredicateTest {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private ExpressionBuilder xb = new ExpressionBuilder();
	private PredicateBuilder pb = new PredicateBuilder();

	@Test
	public void andTest()
    {
		Predicate pred1 = pb.and(pb.isTrue(), pb.isTrue());
		Predicate pred2 = pb.and(pb.isTrue(), pb.isTrue(), pb.isFalse());
		Predicate pred3 = pb.and(pb.isTrue(), pb.isTrue()).not();
		Predicate pred4 = pb.and(pb.isTrue(), pb.isTrue(), pb.isFalse()).not();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "Hello World!");
		
		assertTrue(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));
		assertTrue(pred4.evaluate(spe));

    }

	@Test
	public void betweenTest()
    {
		Predicate pred1 = pb.between(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), 
										xb.literal(StringUtil.toDate("2017/01/01", "yyyy/MM/dd")), 
										xb.literal(StringUtil.toDate("2019/01/01", "yyyy/MM/dd")));
		Predicate pred2 = pb.between(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), 
										StringUtil.toDate("2017/01/01", "yyyy/MM/dd"), 
										StringUtil.toDate("2019/01/01", "yyyy/MM/dd"));
		Predicate pred3 = pb.between(	xb.literal(StringUtil.toDate("2017/01/01", "yyyy/MM/dd")), 
										xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), 
										xb.literal(StringUtil.toDate("2019/01/01", "yyyy/MM/dd"))).not();
		Predicate pred4 = pb.between(	xb.literal(StringUtil.toDate("2017/01/01", "yyyy/MM/dd")), 
										StringUtil.toDate("2018/01/01", "yyyy/MM/dd"), 
										StringUtil.toDate("2019/01/01", "yyyy/MM/dd")).not();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));
		assertTrue(pred4.evaluate(spe));

    }

	@Test
	public void equalsTest()
    {
		Predicate pred1 = pb.equals(	xb.literal(10), xb.literal(10));
		Predicate pred2 = pb.equals(	xb.literal(10), 10);
		Predicate pred3 = pb.equals(	xb.literal(10), xb.literal(11)).not();
		Predicate pred4 = pb.equals(	xb.literal(10), 11);
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));
		assertFalse(pred4.evaluate(spe));

    }

	@Test
	public void geTest()
    {
		Predicate pred1 = pb.ge(	xb.literal(10), xb.literal(10));
		Predicate pred2 = pb.ge(	xb.literal(10), 10);
		Predicate pred3 = pb.ge(	xb.literal(10), xb.literal(11));
		Predicate pred4 = pb.ge(	xb.literal(10), 11);
		Predicate pred5 = pb.ge(	xb.literal(10), xb.literal(9));
		Predicate pred6 = pb.ge(	xb.literal(10), 9);
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));
		assertFalse(pred4.evaluate(spe));
		assertTrue(pred5.evaluate(spe));
		assertTrue(pred6.evaluate(spe));

    }

	@Test
	public void greaterThanTest()
    {
		Predicate pred1 = pb.greaterThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2017/01/01", "yyyy/MM/dd")));
		Predicate pred2 = pb.greaterThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2017/01/01", "yyyy/MM/dd"));
		Predicate pred3 = pb.greaterThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")));
		Predicate pred4 = pb.greaterThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		Predicate pred5 = pb.greaterThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2019/01/01", "yyyy/MM/dd")));
		Predicate pred6 = pb.greaterThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2019/01/01", "yyyy/MM/dd"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));
		assertFalse(pred4.evaluate(spe));
		assertFalse(pred5.evaluate(spe));
		assertFalse(pred6.evaluate(spe));

    }

	@Test
	public void greaterThanOrEqualToTest()
    {
		Predicate pred1 = pb.greaterThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2017/01/01", "yyyy/MM/dd")));
		Predicate pred2 = pb.greaterThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2017/01/01", "yyyy/MM/dd"));
		Predicate pred3 = pb.greaterThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")));
		Predicate pred4 = pb.greaterThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		Predicate pred5 = pb.greaterThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2019/01/01", "yyyy/MM/dd")));
		Predicate pred6 = pb.greaterThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2019/01/01", "yyyy/MM/dd"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));
		assertTrue(pred4.evaluate(spe));
		assertFalse(pred5.evaluate(spe));
		assertFalse(pred6.evaluate(spe));

    }

	@Test
	public void gtTest()
    {
		Predicate pred1 = pb.gt(	xb.literal(10), xb.literal(10));
		Predicate pred2 = pb.gt(	xb.literal(10), 10);
		Predicate pred3 = pb.gt(	xb.literal(10), xb.literal(11));
		Predicate pred4 = pb.gt(	xb.literal(10), 11);
		Predicate pred5 = pb.gt(	xb.literal(10), xb.literal(9));
		Predicate pred6 = pb.gt(	xb.literal(10), 9);
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertFalse(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));
		assertFalse(pred4.evaluate(spe));
		assertTrue(pred5.evaluate(spe));
		assertTrue(pred6.evaluate(spe));

    }

	@Test
	public void inTest()
    {
		Set<Integer> list = new HashSet<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		Predicate pred1 = pb.in(	xb.literal(10), 8, 9, 10, 11, 12, 13);
		Predicate pred2 = pb.in(	xb.literal(10), xb.literal(8), xb.literal(9), xb.literal(10), xb.literal(11), xb.literal(12), xb.literal(13));
		Predicate pred3 = pb.in(	xb.literal(10), list);
		Predicate pred4 = pb.in(	xb.literal(10), xb.literal(list));
		Predicate pred5 = pb.in(	xb.literal(5), 8, 9, 10, 11, 12, 13);
		Predicate pred6 = pb.in(	xb.literal(5), xb.literal(8), xb.literal(9), xb.literal(10), xb.literal(11), xb.literal(12), xb.literal(13));
		Predicate pred7 = pb.in(	xb.literal(5), list);
		Predicate pred8 = pb.in(	xb.literal(5), xb.literal(list));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));
		assertFalse(pred4.evaluate(spe));
		assertFalse(pred5.evaluate(spe));
		assertFalse(pred6.evaluate(spe));
		assertTrue(pred7.evaluate(spe));
		assertTrue(pred8.evaluate(spe));

    }

	@Test
	public void isFalseTest()
    {
		Predicate pred1 = pb.isFalse(	xb.literal(true));
		Predicate pred2 = pb.isFalse(	xb.literal(false));
		Predicate pred3 = pb.isFalse();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertFalse(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));

    }

	@Test
	public void isTrueTest()
    {
		Predicate pred1 = pb.isTrue(	xb.literal(true));
		Predicate pred2 = pb.isTrue(	xb.literal(false));
		Predicate pred3 = pb.isTrue();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));

    }

	@Test
	public void leTest()
    {
		Predicate pred1 = pb.le(	xb.literal(10), xb.literal(10));
		Predicate pred2 = pb.le(	xb.literal(10), 10);
		Predicate pred3 = pb.le(	xb.literal(10), xb.literal(11));
		Predicate pred4 = pb.le(	xb.literal(10), 11);
		Predicate pred5 = pb.le(	xb.literal(10), xb.literal(9));
		Predicate pred6 = pb.le(	xb.literal(10), 9);
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));
		assertTrue(pred4.evaluate(spe));
		assertFalse(pred5.evaluate(spe));
		assertFalse(pred6.evaluate(spe));

    }

	@Test
	public void lessThanTest()
    {
		Predicate pred1 = pb.lessThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2017/01/01", "yyyy/MM/dd")));
		Predicate pred2 = pb.lessThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2017/01/01", "yyyy/MM/dd"));
		Predicate pred3 = pb.lessThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")));
		Predicate pred4 = pb.lessThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		Predicate pred5 = pb.lessThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2019/01/01", "yyyy/MM/dd")));
		Predicate pred6 = pb.lessThan(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2019/01/01", "yyyy/MM/dd"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertFalse(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));
		assertFalse(pred4.evaluate(spe));
		assertTrue(pred5.evaluate(spe));
		assertTrue(pred6.evaluate(spe));

    }

	@Test
	public void lessThanOrEqualToTest()
    {
		Predicate pred1 = pb.lessThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2017/01/01", "yyyy/MM/dd")));
		Predicate pred2 = pb.lessThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2017/01/01", "yyyy/MM/dd"));
		Predicate pred3 = pb.lessThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")));
		Predicate pred4 = pb.lessThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		Predicate pred5 = pb.lessThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), xb.literal(StringUtil.toDate("2019/01/01", "yyyy/MM/dd")));
		Predicate pred6 = pb.lessThanOrEqualTo(	xb.literal(StringUtil.toDate("2018/01/01", "yyyy/MM/dd")), StringUtil.toDate("2019/01/01", "yyyy/MM/dd"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertFalse(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));
		assertTrue(pred4.evaluate(spe));
		assertTrue(pred5.evaluate(spe));
		assertTrue(pred6.evaluate(spe));

    }

	@Test
	public void likeTest()
    {
		Predicate pred1 = pb.like(	xb.literal("This is a string"), xb.literal("%is a%"));
		Predicate pred2 = pb.like(	xb.literal("This is a string"), "%is not a%");
		Predicate pred3 = pb.like(	xb.literal("This is a string"), xb.literal("%is a%"), xb.literal('-'));
		Predicate pred4 = pb.like(	xb.literal("This is a string"), xb.literal("%is a%"), '-');
		Predicate pred5 = pb.like(	xb.literal("This is a string"), "%is a%", xb.literal('-'));
		Predicate pred6 = pb.like(	xb.literal("This is a string"), "%is a%", '-');
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));
		assertTrue(pred4.evaluate(spe));
		assertTrue(pred5.evaluate(spe));
		assertTrue(pred6.evaluate(spe));

    }

	@Test
	public void ltTest()
    {
		Predicate pred1 = pb.lt(	xb.literal(10), xb.literal(10));
		Predicate pred2 = pb.lt(	xb.literal(10), 10);
		Predicate pred3 = pb.lt(	xb.literal(10), xb.literal(11));
		Predicate pred4 = pb.lt(	xb.literal(10), 11);
		Predicate pred5 = pb.lt(	xb.literal(10), xb.literal(9));
		Predicate pred6 = pb.lt(	xb.literal(10), 9);
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertFalse(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));
		assertTrue(pred3.evaluate(spe));
		assertTrue(pred4.evaluate(spe));
		assertFalse(pred5.evaluate(spe));
		assertFalse(pred6.evaluate(spe));

    }

	@Test
	public void notTest()
    {
		Predicate pred1 = pb.not(	xb.literal(true));
		Predicate pred2 = pb.not(	xb.literal(false));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertFalse(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));

    }

	@Test
	public void isNotNullTest()
    {
		Predicate pred1 = pb.isNotNull(	xb.nullLiteral(Integer.class));
		Predicate pred2 = pb.isNotNull(	xb.literal(false));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertFalse(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));

    }

	@Test
	public void isNullTest()
    {
		Predicate pred1 = pb.isNull(	xb.nullLiteral(Integer.class));
		Predicate pred2 = pb.isNull(	xb.literal(false));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		assertTrue(pred1.evaluate(spe));
		assertFalse(pred2.evaluate(spe));

    }

	@Test
	public void orTest()
    {
		Predicate pred1 = pb.or(pb.isTrue(), pb.isFalse());
		Predicate pred2 = pb.or(pb.isTrue(), pb.isTrue(), pb.isFalse());
		Predicate pred3 = pb.or(pb.isFalse(), pb.isFalse());
		Predicate pred4 = pb.or(pb.isTrue(), pb.isTrue(), pb.isFalse()).not();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "Hello World!");
		
		assertTrue(pred1.evaluate(spe));
		assertTrue(pred2.evaluate(spe));
		assertFalse(pred3.evaluate(spe));
		assertFalse(pred4.evaluate(spe));

    }

	



}

package com.k2.Expressions;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.Trimspec;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.evaluators.SimpleParameterEvaluator;
import com.k2.Expressions.expression.Expression;
import com.k2.Expressions.expression.ExpressionBuilder;
import com.k2.Util.DoubleUtil;
import com.k2.Util.StringUtil;

public class SimpleParameterEvaluatorTest {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private ExpressionBuilder xb = new ExpressionBuilder();

	@Test
	public void absoluteTest()
    {
		Expression<Integer> expr1 = xb.abs(xb.literal(1));
		Expression<Integer> expr2 = xb.abs(xb.literal(-1));
		Expression<Double> expr3 = xb.abs(xb.literal(1.123));
		Expression<Double> expr4 = xb.abs(xb.literal(-1.123));
		Expression<Integer> expr5 = xb.abs(xb.param(Integer.class, "p1"));
		Expression<Double> expr6 = xb.abs(xb.param(Double.class, "p2"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Integer.class, "p1", -1);
		spe.set(Double.class, "p2", -9.876);
		
		assertEquals(new Integer(1), expr1.evaluate(spe));
		assertEquals(new Integer(1), expr2.evaluate(spe));
		assertEquals(new Double(1.123), expr3.evaluate(spe));
		assertEquals(new Double(1.123), expr4.evaluate(spe));
		assertEquals(new Integer(1), expr5.evaluate(spe));
		assertEquals(new Double(9.876), expr6.evaluate(spe));

    }
	
	@Test
	public void concatenateTest()
    {
		Expression<String> expr1 = xb.concatenate("Hello ", xb.param(String.class, "p1"));
		Expression<String> expr2 = xb.concatenate(xb.param(String.class, "p1"), " Hello");
		Expression<String> expr3 = xb.concatenate(xb.param(String.class, "p1"), xb.param(String.class, "p2"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "World!");
		spe.set(String.class, "p2", " Oh my");
		
		assertEquals("Hello World!", expr1.evaluate(spe));
		assertEquals("World! Hello", expr2.evaluate(spe));
		assertEquals("World! Oh my", expr3.evaluate(spe));

    }

	@Test
	public void currentDateTest()
    {
		Expression<Date> expr1 = xb.currentDate();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		Date currentDate = spe.getCurrentTime().getCurrentDate();
		
		logger.trace("Current date is: {}", expr1.evaluate(spe));
		
		assertEquals(currentDate, expr1.evaluate(spe));

    }
	
	@Test
	public void currentTimeTest()
    {
		
		Expression<Time> expr1 = xb.currentTime();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		Time currentTime = spe.getCurrentTime().getCurrentTime();

		logger.trace("Current time is: {}", expr1.evaluate(spe));
		
		assertEquals(currentTime, expr1.evaluate(spe));

    }
	
	@Test
	public void currentTimestampTest()
    {
		
		Expression<Timestamp> expr1 = xb.currentTimestamp();
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		Timestamp currentTimestamp = spe.getCurrentTime().getCurrentTimestamp();

		logger.trace("Current timestamp is: {}", expr1.evaluate(spe));
		
		assertEquals(currentTimestamp, expr1.evaluate(spe));

    }
	
	@Test
	public void differnceTest()
    {
		Expression<? extends Number> expr1 = xb.diff(xb.param(Double.class, "p1"), xb.param(Integer.class, "p2"));
		Expression<? extends Number> expr2 = xb.diff(xb.param(Integer.class, "p2"), 4);
		Expression<? extends Number> expr3 = xb.diff(2.345, xb.param(Double.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Double.class, "p1", 1.234);
		spe.set(Integer.class, "p2", 1);
		
		assertEquals(0.234, expr1.evaluate(spe));
		assertEquals(-3, expr2.evaluate(spe));
		assertEquals(1.111, expr3.evaluate(spe));

    }

	@Test
	public void lengthTest()
    {
		Expression<Integer> expr1 = xb.length(xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "Hello World!");
		
		assertEquals(new Integer(12), expr1.evaluate(spe));

    }

	@Test
	public void literalTest()
    {
		Expression<String> expr1 = xb.literal("Hello World!");
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "Hello World!");
		
		assertEquals("Hello World!", expr1.evaluate(spe));

    }

	@Test
	public void locateTest()
    {
		Expression<Integer> expr1 = xb.locate(xb.param(String.class, "string"), xb.param(String.class, "pattern"));
		Expression<Integer> expr2 = xb.locate(xb.param(String.class, "string"), "efg");
		Expression<Integer> expr3 = xb.locate(xb.param(String.class, "string"), xb.param(String.class, "pattern"), xb.param(Integer.class, "from"));
		Expression<Integer> expr4 = xb.locate(xb.param(String.class, "string"), "efg", 10);
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "string", "abcdefghijklmnabcdef");
		spe.set(String.class, "pattern", "def");
		spe.set(Integer.class, "from", 10);
		
		assertEquals(new Integer(4), expr1.evaluate(spe));
		assertEquals(new Integer(5), expr2.evaluate(spe));
		assertEquals(new Integer(18), expr3.evaluate(spe));
		assertEquals(new Integer(0), expr4.evaluate(spe));

    }

	@Test
	public void lowerTest()
    {
		Expression<String> expr1 = xb.lower(xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "Hello World!");
		
		assertEquals("hello world!", expr1.evaluate(spe));

    }

	@Test
	public void modTest()
    {
		Expression<Integer> expr1 = xb.mod(xb.param(Integer.class, "p1"), xb.param(Integer.class, "p2"));
		Expression<Integer> expr2 = xb.mod(xb.param(Integer.class, "p1"), 5);
		Expression<Integer> expr3 = xb.mod(13, xb.param(Integer.class, "p2"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Integer.class, "p1", 13);
		spe.set(Integer.class, "p2", 5);
		
		assertEquals(new Integer(3), expr1.evaluate(spe));
		assertEquals(new Integer(3), expr2.evaluate(spe));
		assertEquals(new Integer(3), expr3.evaluate(spe));

    }

	@Test
	public void negTest()
    {
		Expression<Integer> expr1 = xb.neg(xb.param(Integer.class, "p1"));
		Expression<Long> expr2 = xb.neg(xb.param(Long.class, "p2"));
		Expression<Float> expr3 = xb.neg(xb.param(Float.class, "p3"));
		Expression<Double> expr4 = xb.neg(xb.param(Double.class, "p4"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Integer.class, "p1", 1);
		spe.set(Long.class, "p2", 1234567890123456789L);
		spe.set(Float.class, "p3", new Float(1.2345));
		spe.set(Double.class, "p4", new Double(12345.6789));
		
		assertEquals(new Integer(-1), expr1.evaluate(spe));
		assertEquals(new Long(-1234567890123456789L), expr2.evaluate(spe));
		assertEquals(new Float(-1.2345), expr3.evaluate(spe));
		assertEquals(new Double(-12345.6789), expr4.evaluate(spe));

    }

	@Test
	public void nullIfTest()
    {
		Expression<Integer> expr1 = xb.nullIf(xb.param(Integer.class, "p1"), xb.param(Integer.class, "p2"));
		Expression<Integer> expr2 = xb.nullIf(xb.param(Integer.class, "p1"), 10);
		Expression<Integer> expr3 = xb.nullIf(xb.nullLiteral(Integer.class), xb.param(Integer.class, "p2"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Integer.class, "p1", 10);
		spe.set(Integer.class, "p2", 5);
		
		assertEquals(new Integer(10), expr1.evaluate(spe));
		assertNull(expr2.evaluate(spe));
		assertNull(expr3.evaluate(spe));

    }

	@Test
	public void prodTest()
    {
		Expression<Integer> expr1 = xb.prod(xb.param(Integer.class, "p1"), xb.param(Integer.class, "p2"));
		Expression<Integer> expr2 = xb.prod(xb.param(Integer.class, "p1"), 10);
		Expression<Double> expr3 = xb.prod(1.234, xb.param(Double.class, "p3"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Integer.class, "p1", 10);
		spe.set(Integer.class, "p2", 5);
		spe.set(Double.class, "p3", 1.234);
		
		assertEquals(new Integer(50), expr1.evaluate(spe));
		assertEquals(new Integer(100), expr2.evaluate(spe));
		assertEquals(new Double(1.522756), expr3.evaluate(spe));

    }

	@Test
	public void quotTest()
    {
		Expression<Double> expr1 = xb.quot(xb.param(Integer.class, "p1"), xb.param(Integer.class, "p2"));
		Expression<Double> expr2 = xb.quot(xb.param(Integer.class, "p1"), 10);
		Expression<Double> expr3 = xb.quot(1.234, xb.param(Double.class, "p3"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Integer.class, "p1", 10);
		spe.set(Integer.class, "p2", 5);
		spe.set(Double.class, "p3", 1.234);
		
		assertEquals(new Double(2), expr1.evaluate(spe));
		assertEquals(new Double(1), expr2.evaluate(spe));
		assertEquals(new Double(1), expr3.evaluate(spe));

    }

	@Test
	public void sqrtTest()
    {
		Expression<Double> expr1 = xb.sqrt(xb.param(Integer.class, "p1"));
		Expression<Double> expr2 = xb.sqrt(xb.param(Long.class, "p2"));
		Expression<Double> expr3 = xb.sqrt(xb.param(Float.class, "p3"));
		Expression<Double> expr4 = xb.sqrt(xb.literal(1234.5678));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Integer.class, "p1", 10);
		spe.set(Long.class, "p2", new Long(10000));
		spe.set(Float.class, "p3", new Float(1.234));
		
		assertEquals(new Double(3.162277660168), expr1.evaluate(spe));
		assertEquals(new Double(100), expr2.evaluate(spe));
		assertEquals(new Double(1.1108555), DoubleUtil.round(expr3.evaluate(spe), 7));
		assertEquals(new Double(35.136417005722), expr4.evaluate(spe));

    }

	@Test
	public void substringTest()
    {
		Expression<String> expr1 = xb.substring(xb.param(String.class, "string"), xb.param(Integer.class, "pos"));
		Expression<String> expr2 = xb.substring(xb.param(String.class, "string"), 5);
		Expression<String> expr3 = xb.substring(xb.param(String.class, "string"), xb.param(Integer.class, "pos"), xb.param(Integer.class, "length"));
		Expression<String> expr4 = xb.substring(xb.param(String.class, "string"), 5, 5);
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "string", "abcdefghighjklm");
		spe.set(Integer.class, "pos", 10);
		spe.set(Integer.class, "length", 2);
		
		assertEquals("ghjklm", expr1.evaluate(spe));
		assertEquals("efghighjklm", expr2.evaluate(spe));
		assertEquals("gh", expr3.evaluate(spe));
		assertEquals("efghi", expr4.evaluate(spe));

    }

	@Test
	public void toBigDecimalTest()
    {
		Expression<BigDecimal> expr1 = xb.toBigDecimal(xb.param(Double.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Double.class, "p1", 1234.5678);
		
		assertEquals(BigDecimal.valueOf(1234.5678), expr1.evaluate(spe));

    }

	@Test
	public void toBigIntegerTest()
    {
		Expression<BigInteger> expr1 = xb.toBigInteger(xb.param(Double.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Double.class, "p1", 1234.5678);
		
		assertEquals(BigInteger.valueOf(1234), expr1.evaluate(spe));

    }

	@Test
	public void toBooleanTest()
    {
		Expression<Boolean> expr1 = xb.toBoolean(xb.param(Double.class, "p1"));
		Expression<Boolean> expr2 = xb.toBoolean(xb.param(String.class, "p2"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Double.class, "p1", 1234.5678);
		spe.set(String.class, "p2", "false");
		
		assertTrue(expr1.evaluate(spe));
		assertFalse(expr2.evaluate(spe));

    }

	@Test
	public void toDateTest()
    {
		Expression<Date> expr1 = xb.toDate(xb.param(String.class, "p1"));
		Expression<Date> expr2 = xb.toDate(xb.param(String.class, "p2"), xb.param(String.class, "p3"));
		Expression<Date> expr3 = xb.toDate(xb.param(String.class, "p2"), "yyyy/MM/dd");
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "2018-01-01 00:00:00");
		spe.set(String.class, "p2", "2018/10/01");
		spe.set(String.class, "p3", "yyyy/MM/dd");
		
		assertEquals(StringUtil.toDate("2018-01-01 00:00:00"), expr1.evaluate(spe));
		assertEquals(StringUtil.toDate("2018-10-01 00:00:00"), expr2.evaluate(spe));
		assertEquals(StringUtil.toDate("2018-10-01 00:00:00"), expr3.evaluate(spe));

    }

	@Test
	public void toDoubleTest()
    {
		Expression<Double> expr1 = xb.toDouble(xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "1234.5678");
		
		assertEquals(Double.valueOf(1234.5678), expr1.evaluate(spe));

    }

	@Test
	public void toFloatTest()
    {
		Expression<Float> expr1 = xb.toFloat(xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "1234.5678");
		
		assertEquals(Float.valueOf((float) 1234.5678), expr1.evaluate(spe));

    }

	@Test
	public void toIntegerTest()
    {
		Expression<Integer> expr1 = xb.toInteger(xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "1234.5678");
		
		assertEquals(Integer.valueOf(1234), expr1.evaluate(spe));

    }

	@Test
	public void toLongTest()
    {
		Expression<Long> expr1 = xb.toLong(xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "1234.5678");
		
		assertEquals(Long.valueOf(1234), expr1.evaluate(spe));

    }

	@Test
	public void toStringTest()
    {
		Expression<String> expr1 = xb.toString(xb.param(Date.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(Date.class, "p1", StringUtil.toDate("2018/01/01", "yyyy/MM/dd"));
		
		assertEquals("2018-01-01 00:00:00", expr1.evaluate(spe));

    }

	@Test
	public void trimTest()
    {
		Expression<String> expr1 = xb.trim(xb.param(String.class, "p1"));
		Expression<String> expr2 = xb.trim(xb.param(Character.class, "p2"), xb.param(String.class, "p3"));
		Expression<String> expr3 = xb.trim('_', xb.param(String.class, "p3"));
		Expression<String> expr4 = xb.trim(Trimspec.LEADING, xb.param(Character.class, "p2"), xb.param(String.class, "p3"));
		Expression<String> expr5 = xb.trim(Trimspec.TRAILING, '_', xb.param(String.class, "p3"));
		Expression<String> expr6 = xb.trim(Trimspec.BOTH, xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "   XXXX   ");
		spe.set(Character.class, "p2", '_');
		spe.set(String.class, "p3", "__ XXXX __");
		
		assertEquals("XXXX", expr1.evaluate(spe));
		assertEquals(" XXXX ", expr2.evaluate(spe));
		assertEquals(" XXXX ", expr3.evaluate(spe));
		assertEquals(" XXXX __", expr4.evaluate(spe));
		assertEquals("__ XXXX ", expr5.evaluate(spe));
		assertEquals("XXXX", expr6.evaluate(spe));

    }

	@Test
	public void upperTest()
    {
		Expression<String> expr1 = xb.upper(xb.param(String.class, "p1"));
		
		SimpleParameterEvaluator spe = new SimpleParameterEvaluator();
		
		spe.set(String.class, "p1", "Hello World!");
		
		assertEquals("HELLO WORLD!", expr1.evaluate(spe));

    }


}

package com.k2.Expressions;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Expressions.criteria.CriteriaBuilderImpl;
import com.k2.Expressions.criteria.CriteriaQueryImpl;
import com.k2.Expressions.evaluators.QueryEvaluator;
import com.k2.Expressions.evaluators.SimpleParameterEvaluator;
import com.k2.Expressions.expression.ExpressionBuilder;
import com.k2.Expressions.metamodel.MetamodelImpl;
import com.k2.Expressions.predicate.K2Predicate;
import com.k2.Expressions.predicate.PredicateBuilder;
import com.k2.Util.StringUtil;

public class QueryTest {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Entity
	class Foo {
		@Id @Column Long id;
		@Column Integer intVal;
		@Column Long longVal;
		@Column Date dateVal;
		@Column Boolean boolVal;
		@Column Float floatVal;
		@Column Double doubleVal;
		@Column String strVal;
		
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
	public void basicQueryTest()
    {
		MetamodelImpl mm = new MetamodelImpl(Foo.class);
		CriteriaBuilderImpl cb = new CriteriaBuilderImpl(mm);
		CriteriaQueryImpl<Foo> qry = (CriteriaQueryImpl<Foo>) cb.createQuery(Foo.class);
		
		Root<Foo> root = qry.from(Foo.class);
		qry.where(cb.equal(root.get("strVal"), cb.parameter(String.class, "strValParm")));
	
		Foo match = new Foo(0L,1,10L,new Date(), true, 1.234f, 234.5678, "match");
		Foo noMatch = new Foo(0L,1,10L,new Date(), true, 1.234f, 234.5678, "noMatch");
	
		QueryEvaluator<Foo> qe = new QueryEvaluator<Foo>(qry);
		
		qe.set(String.class, "strValParm", "match");
		
		assertTrue(qe.matches(match));
		assertFalse(qe.matches(noMatch));

    }

	



}

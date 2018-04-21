package com.k2.Expressions.evaluators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.k2.Expressions.ParameterEvaluator;
import com.k2.Expressions.criteria.CriteriaQueryImpl;
import com.k2.Expressions.criteria.PathEvaluator;
import com.k2.Expressions.criteria.PathImpl;
import com.k2.Expressions.exceptions.ExpressionError;
import com.k2.Expressions.predicate.K2Predicate;

public class QueryEvaluator<T> extends SimpleParameterEvaluator implements PathEvaluator, ParameterEvaluator{

	private final CriteriaQueryImpl<T> qry;

	private T matchRoot = null;

	public QueryEvaluator(CriteriaQueryImpl<T> qry) {
		this.qry = qry;
	}
	
	public boolean matches(T obj) {
		Predicate p = qry.getRestriction();
		if (p == null) return true;
		if (p instanceof K2Predicate) {
			K2Predicate kp = (K2Predicate)p;
			matchRoot = obj;
			boolean matched = kp.evaluate(this);
			matchRoot = null;
			return matched;
		}
		
		throw new ExpressionError("The QueryEvaluator can only evaluate K2 Predicates");
	}
	
	public List<T> filter(List<T> list) {
		List<T> fList = new ArrayList<T>();
		
		for (T t : list) 
			if (matches(t))
				fList.add(t);
		
		return fList;
	}
	
	
	@Override
	public <V> V valueOf(PathImpl<V> path) {
		return path.getValueFromRoot(matchRoot);
	}


}

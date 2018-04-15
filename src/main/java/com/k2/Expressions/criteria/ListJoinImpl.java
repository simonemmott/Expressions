package com.k2.Expressions.criteria;


import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.ListJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ListAttribute;

public class ListJoinImpl<R,T> extends JoinImpl<R,T> implements ListJoin<R, T> {

	@SuppressWarnings("unchecked")
	public ListJoinImpl(PathImpl<R> parentPath, ListAttribute<? super R, T> attribute) {
		super(parentPath, (Attribute<? super R, T>)attribute);
	}

	@SuppressWarnings("unchecked")
	public ListJoinImpl(PathImpl<R> parentPath, ListAttribute<? super R, T> attribute, JoinType joinType) {
		super(parentPath, (Attribute<? super R, T>)attribute, joinType);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ListAttribute<? super R,T> getModel() {
		return (ListAttribute<? super R, T>) super.getAttribute();
	}

	@Override
	public ListJoin<R,T> on(Expression<Boolean> restriction) {
		return (ListJoin<R, T>) super.on(restriction);
	}
	
	@Override
	public ListJoin<R,T> on(Predicate... restrictions) {
		return (ListJoin<R, T>) super.on(restrictions);
	}

	@Override
	public Expression<Integer> index() {
		// TODO Auto-generated method stub
		return null;
	}

}

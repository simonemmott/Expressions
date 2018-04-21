package com.k2.Expressions.criteria;


import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;

import com.k2.Expressions.metamodel.MetamodelImpl;

public class CollectionJoinImpl<R,T> extends JoinImpl<R,T> implements CollectionJoin<R, T> {

	@SuppressWarnings("unchecked")
	public CollectionJoinImpl(MetamodelImpl metamodel, PathImpl<R> parentPath, CollectionAttribute<? super R, T> attribute) {
		super(metamodel, parentPath, (Attribute<? super R, T>)attribute);
	}

	@SuppressWarnings("unchecked")
	public CollectionJoinImpl(MetamodelImpl metamodel, PathImpl<R> parentPath, CollectionAttribute<? super R, T> attribute, JoinType joinType) {
		super(metamodel, parentPath, (Attribute<? super R, T>)attribute, joinType);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CollectionAttribute<? super R,T> getModel() {
		return (CollectionAttribute<? super R, T>) super.getAttribute();
	}

	@Override
	public CollectionJoin<R,T> on(Expression<Boolean> restriction) {
		return (CollectionJoin<R, T>) super.on(restriction);
	}
	
	@Override
	public CollectionJoin<R,T> on(Predicate... restrictions) {
		return (CollectionJoin<R, T>) super.on(restrictions);
	}

}

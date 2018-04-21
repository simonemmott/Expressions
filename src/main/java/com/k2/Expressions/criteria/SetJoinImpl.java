package com.k2.Expressions.criteria;


import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;

import com.k2.Expressions.metamodel.MetamodelImpl;

public class SetJoinImpl<R,T> extends JoinImpl<R,T> implements SetJoin<R, T> {

	@SuppressWarnings("unchecked")
	public SetJoinImpl(MetamodelImpl metamodel, PathImpl<R> parentPath, SetAttribute<? super R, T> attribute) {
		super(metamodel, parentPath, (Attribute<? super R, T>)attribute);
	}

	@SuppressWarnings("unchecked")
	public SetJoinImpl(MetamodelImpl metamodel, PathImpl<R> parentPath, SetAttribute<? super R, T> attribute, JoinType joinType) {
		super(metamodel, parentPath, (Attribute<? super R, T>)attribute, joinType);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SetAttribute<? super R,T> getModel() {
		return (SetAttribute<? super R, T>) super.getAttribute();
	}

	@Override
	public SetJoin<R,T> on(Expression<Boolean> restriction) {
		return (SetJoin<R, T>) super.on(restriction);
	}
	
	@Override
	public SetJoin<R,T> on(Predicate... restrictions) {
		return (SetJoin<R, T>) super.on(restrictions);
	}

}

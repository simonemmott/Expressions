package com.k2.Expressions.criteria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import com.k2.Expressions.metamodel.ManagedTypeImpl;
import com.k2.Expressions.metamodel.MetamodelImpl;

public class FromImpl<R,T> extends PathImpl<T> implements From<R,T> {
	protected MetamodelImpl metamodel;
	
	FromImpl(MetamodelImpl metamodel, PathImpl<?> parentPath, Attribute<? super R, T> attr) {
		super(parentPath, attr);
		this.metamodel = metamodel;
	}

	public FromImpl(MetamodelImpl metamodel, Class<T> cls) {
		super(cls);
		this.metamodel = metamodel;
	}

	@Override
	public Set<Fetch<T, ?>> getFetches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Fetch<T, Y> fetch(SingularAttribute<? super T, Y> attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Fetch<T, Y> fetch(SingularAttribute<? super T, Y> attribute, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Fetch<T, Y> fetch(PluralAttribute<? super T, ?, Y> attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Fetch<T, Y> fetch(PluralAttribute<? super T, ?, Y> attribute, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> Fetch<X, Y> fetch(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> Fetch<X, Y> fetch(String attributeName, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Set<Join<T,?>> joins = new HashSet<Join<T,?>>();
	
	private JoinImpl<T,?> getCurrentJoin(Attribute<? super T,?> attribute) {
		for (Join<T,?> j : joins) {
			if (j.getAttribute().equals(attribute)) return (JoinImpl<T, ?>) j;
		}
		return null;
	}

	@Override
	public Set<Join<T, ?>> getJoins() {
		return joins;
	}

	@Override
	public boolean isCorrelated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public From<R, T> getCorrelationParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> JoinImpl<T, Y> join(SingularAttribute<? super T, Y> attribute) {
		JoinImpl<T,Y> j = (JoinImpl<T, Y>) getCurrentJoin(attribute);
		if (j == null) {
			j = new JoinImpl<T,Y>(metamodel, this, attribute);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> JoinImpl<T, Y> join(SingularAttribute<? super T, Y> attribute, JoinType jt) {
		JoinImpl<T,Y> j = (JoinImpl<T, Y>) getCurrentJoin(attribute);
		if (j == null) {
			j = new JoinImpl<T,Y>(metamodel, this, attribute, jt);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> CollectionJoinImpl<T, Y> join(CollectionAttribute<? super T, Y> collection) {
		CollectionJoinImpl<T,Y> j = (CollectionJoinImpl<T, Y>) getCurrentJoin(collection);
		if (j == null) {
			j = new CollectionJoinImpl<T,Y>(metamodel, this, collection);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> SetJoinImpl<T, Y> join(SetAttribute<? super T, Y> set) {
		SetJoinImpl<T,Y> j = (SetJoinImpl<T, Y>) getCurrentJoin(set);
		if (j == null) {
			j = new SetJoinImpl<T,Y>(metamodel, this, set);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> ListJoinImpl<T, Y> join(ListAttribute<? super T, Y> list) {
		ListJoinImpl<T,Y> j = (ListJoinImpl<T, Y>) getCurrentJoin(list);
		if (j == null) {
			j = new ListJoinImpl<T,Y>(metamodel, this, list);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> MapJoinImpl<T, K, V> join(MapAttribute<? super T, K, V> map) {
		MapJoinImpl<T,K,V> j = (MapJoinImpl<T,K,V>) getCurrentJoin(map);
		if (j == null) {
			j = new MapJoinImpl<T,K,V>(metamodel, this, map);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> CollectionJoinImpl<T, Y> join(CollectionAttribute<? super T, Y> collection, JoinType jt) {
		CollectionJoinImpl<T,Y> j = (CollectionJoinImpl<T, Y>) getCurrentJoin(collection);
		if (j == null) {
			j = new CollectionJoinImpl<T,Y>(metamodel, this, collection, jt);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> SetJoinImpl<T, Y> join(SetAttribute<? super T, Y> set, JoinType jt) {
		SetJoinImpl<T,Y> j = (SetJoinImpl<T, Y>) getCurrentJoin(set);
		if (j == null) {
			j = new SetJoinImpl<T,Y>(metamodel, this, set, jt);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Y> ListJoinImpl<T, Y> join(ListAttribute<? super T, Y> list, JoinType jt) {
		ListJoinImpl<T,Y> j = (ListJoinImpl<T, Y>) getCurrentJoin(list);
		if (j == null) {
			j = new ListJoinImpl<T,Y>(metamodel, this, list, jt);
			joins.add(j);
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> MapJoin<T, K, V> join(MapAttribute<? super T, K, V> map, JoinType jt) {
		MapJoinImpl<T,K,V> j = (MapJoinImpl<T,K,V>) getCurrentJoin(map);
		if (j == null) {
			j = new MapJoinImpl<T,K,V>(metamodel, this, map, jt);
			joins.add(j);
		}
		return j;
	}

	@Override
	public <X, Y> Join<X, Y> join(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> SetJoin<X, Y> joinSet(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> ListJoin<X, Y> joinList(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, K, V> MapJoin<X, K, V> joinMap(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> Join<X, Y> join(String attributeName, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> SetJoin<X, Y> joinSet(String attributeName, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, Y> ListJoin<X, Y> joinList(String attributeName, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X, K, V> MapJoin<X, K, V> joinMap(String attributeName, JoinType jt) {
		// TODO Auto-generated method stub
		return null;
	}

}

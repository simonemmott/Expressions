package com.k2.Expressions.metamodel;

import javax.persistence.metamodel.Type;

public class TypeImpl<T> implements Type<T> {
	Class<T> javaType;
	TypeImpl(Class<T> javaType) {
		this.javaType = javaType;
	}

	@Override
	public Class<T> getJavaType() {
		return javaType;
	}

	@Override
	public PersistenceType getPersistenceType() {
		return PersistenceType.BASIC;
	}

}

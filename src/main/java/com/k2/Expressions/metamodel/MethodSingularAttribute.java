package com.k2.Expressions.metamodel;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodSingularAttribute<X, T> extends MethodAttribute<X,T> implements SingularAttribute<X, T> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	

	public MethodSingularAttribute(ManagedTypeImpl<X> managedType, Method method) {
		super(managedType, method);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getBindableJavaType() {
		return (Class<T>) method.getReturnType();
	}

	@Override
	public BindableType getBindableType() {
		return BindableType.SINGULAR_ATTRIBUTE;
	}

	private Type<T> type;
	@SuppressWarnings("unchecked")
	@Override
	public Type<T> getType() {
		if (type != null) return type;
		type = managedType.metamodel.managedType((Class<T>)method.getReturnType());
		if (type == null) {
			type =  new Type<T>() {
	
				@Override
				public PersistenceType getPersistenceType() {
					return PersistenceType.BASIC;
				}
	
				@Override
				public Class<T> getJavaType() {
					return (Class<T>) method.getReturnType();
				}};
		}
		return type;
	}


	@Override
	public boolean isId() {
		if (method.isAnnotationPresent(Id.class))
			return true;
		if (method.isAnnotationPresent(EmbeddedId.class))
			return true;
		return false;
	}

	@Override
	public boolean isOptional() {
		if (method.isAnnotationPresent(Column.class))
			if (method.getAnnotation(Column.class).nullable())
				return true;
			else
				return false;
		if (method.isAnnotationPresent(ManyToOne.class))
			if (method.getAnnotation(ManyToOne.class).optional())
				return true;
			else
				return false;
		return true;
	}

	@Override
	public boolean isVersion() {
		if (method.isAnnotationPresent(Version.class))
			return true;
		return false;
	}

}

package com.k2.Expressions.metamodel;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FieldSingularAttribute<X, T> extends FieldAttribute<X,T> implements SingularAttribute<X, T> {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public FieldSingularAttribute(ManagedTypeImpl<X> managedType, Field field) {
		super(managedType, field);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getBindableJavaType() {
		return (Class<T>) field.getType();
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
		type = managedType.metamodel.managedType((Class<T>)field.getType());
		if (type == null) {
			type =  new TypeImpl<T>((Class<T>) field.getType());
		}
		return type;
	}

	@Override
	public boolean isId() {
		if (field.isAnnotationPresent(Id.class))
			return true;
		if (field.isAnnotationPresent(EmbeddedId.class))
			return true;
		return false;
	}

	@Override
	public boolean isOptional() {
		if (field.isAnnotationPresent(Column.class))
			if (field.getAnnotation(Column.class).nullable())
				return true;
			else
				return false;
		if (field.isAnnotationPresent(ManyToOne.class))
			if (field.getAnnotation(ManyToOne.class).optional())
				return true;
			else
				return false;
		return true;
	}

	@Override
	public boolean isVersion() {
		if (field.isAnnotationPresent(Version.class))
			return true;
		return false;
	}

}

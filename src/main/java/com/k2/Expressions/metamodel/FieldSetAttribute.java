package com.k2.Expressions.metamodel;

import java.lang.reflect.Field;
import java.util.Set;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.Type;

import com.k2.Util.classes.ClassUtil;

public class FieldSetAttribute<X, T> extends FieldAttribute<X,Set<T>> implements SetAttribute<X, T> {
	

	public FieldSetAttribute(ManagedTypeImpl<X> managedType, Field field) {
		super(managedType, field);
	}

	@Override
	public CollectionType getCollectionType() {
		return CollectionType.SET;
	}

	Type<T> elementType;
	@Override
	public Type<T> getElementType() {
		if (elementType != null) return elementType;
		elementType = managedType.metamodel.managedType(getBindableJavaType());
		if (elementType == null) {
			elementType = new TypeImpl<T>(getBindableJavaType());
		}
		return elementType;
	}

	public boolean isCollection() {
		return true;
	}

	Class<T> elementClass;
	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getBindableJavaType() {
		if  (elementClass != null) return elementClass;
		elementClass = ClassUtil.getFieldGenericTypeClass(field, 0);
		return elementClass;
	}

	@Override
	public BindableType getBindableType() {
		return BindableType.PLURAL_ATTRIBUTE;
	}


}

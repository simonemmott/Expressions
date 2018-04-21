package com.k2.Expressions.metamodel;

import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.ManagedType;

public class EmbeddableTypeImpl<X> extends ManagedTypeImpl<X> implements ManagedType<X>, EmbeddableType<X> {

	EmbeddableTypeImpl(MetamodelImpl metamodel, Class<X> classType) {
		super(metamodel, classType);
	}

}

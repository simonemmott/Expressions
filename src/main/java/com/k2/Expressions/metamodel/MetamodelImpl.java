package com.k2.Expressions.metamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import com.k2.Expressions.exceptions.ExpressionError;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.ClassUtil.AnnotationCheck;

public class MetamodelImpl implements Metamodel {
	
	Map<Class<?>, ManagedTypeImpl<?>> managedTypes = new HashMap<Class<?>, ManagedTypeImpl<?>>();
	Map<Class<?>, EntityTypeImpl<?>> entityTypes = new HashMap<Class<?>, EntityTypeImpl<?>>();
	Map<Class<?>, EmbeddableTypeImpl<?>> embeddableTypes = new HashMap<Class<?>, EmbeddableTypeImpl<?>>();
	
	public MetamodelImpl() {}
	
	public MetamodelImpl(Class<?> ...classes) {
		for (Class<?> cls : classes)
			manage(cls);
	}
	
	public MetamodelImpl(String ...packageNames) {
		for (String packageName : packageNames) 
			for (Class<?> cls : ClassUtil.getClasses(packageName, AnnotationCheck.ANY, Entity.class, Embeddable.class))
				manage(cls);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public <X> ManagedType<X> manage(Class<X> cls) {
		ManagedTypeImpl<X> mt = (ManagedTypeImpl<X>) managedTypes.get(cls);
		if (mt != null) return mt;
		if (cls.getSuperclass() != null && cls.getSuperclass() != Object.class)
			manage(cls.getSuperclass());
		if (cls.isAnnotationPresent(Entity.class)) {
			mt = new EntityTypeImpl<X>(this, cls);
			add(mt);
			return mt;
		}
		if (cls.isAnnotationPresent(Embeddable.class)) {
			mt = new EmbeddableTypeImpl<X>(this, cls);
			add(mt);
			return mt;
		}
		throw new ExpressionError("The class {} is not a manageable type", cls.getName()); 
	}
	
	<X> void add(ManagedTypeImpl<X> managedType) {
		managedTypes.put(managedType.getJavaType(), managedType);
		switch (managedType.getPersistenceType()) {
		case BASIC:
			if (managedType instanceof EmbeddableTypeImpl)
				embeddableTypes.put(managedType.getJavaType(), (EmbeddableTypeImpl<X>)managedType);
			
			if (managedType instanceof EntityTypeImpl) 
				entityTypes.put(managedType.getJavaType(), (EntityTypeImpl<X>)managedType);

			break;
		case EMBEDDABLE:
			if (managedType instanceof EmbeddableTypeImpl) {
				embeddableTypes.put(managedType.getJavaType(), (EmbeddableTypeImpl<X>)managedType);
			} else {
				throw new ExpressionError("Managed types with persistence type EMBEDDABLE must implement EmbeddableType");
			}
			break;
		case ENTITY:
			if (managedType instanceof EntityTypeImpl) {
				entityTypes.put(managedType.getJavaType(), (EntityTypeImpl<X>)managedType);
			} else {
				throw new ExpressionError("Managed types with persistence type ENTITY must implement EntityType");
			}
			break;
		case MAPPED_SUPERCLASS:
			break;
		default:
			break;
		
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> EntityType<X> entity(Class<X> cls) {
		if ( ! cls.isAnnotationPresent(Entity.class) )
			throw new ExpressionError("The class {} is not an entity class", cls.getName());
		EntityType<X> et = (EntityType<X>) entityTypes.get(cls);
		if (et == null) 
			throw new ExpressionError("This entity manager factory is not managing instances of the entity class {}", cls.getName());
		return et;
	}
	
	@SuppressWarnings("unchecked")
	public <X> ManagedTypeImpl<? super X> managedSuperType(Class<X> cls) {
		if (cls == null) return null;
		Class<? super X> superX = cls;
		ManagedTypeImpl<? super X> mt = null;
		while (superX != null && superX.getSuperclass() != Object.class) {
			superX = cls.getSuperclass();
			mt = (ManagedTypeImpl<? super X>) managedTypes.get(superX);
			if (mt != null) return mt;
		}
		return null;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <X> ManagedTypeImpl<X> managedType(Class<X> cls) {
		return (ManagedTypeImpl<X>) managedTypes.get(cls);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> EmbeddableType<X> embeddable(Class<X> cls) {
		if ( ! cls.isAnnotationPresent(Embeddable.class) )
			throw new ExpressionError("The class {} is not an embeddable class", cls.getName());
		EmbeddableType<X> et = (EmbeddableType<X>) embeddableTypes.get(cls);
		if (et == null) 
			throw new ExpressionError("This entity manager factory is not managing instances of the embeddable class {}", cls.getName());
		return et;
	}

	@Override
	public Set<ManagedType<?>> getManagedTypes() {
		return new HashSet<ManagedType<?>>(managedTypes.values());
	}

	@Override
	public Set<EntityType<?>> getEntities() {
		return new HashSet<EntityType<?>>(entityTypes.values());
	}

	@Override
	public Set<EmbeddableType<?>> getEmbeddables() {
		return new HashSet<EmbeddableType<?>>(embeddableTypes.values());
	}

	public boolean isModelled(Class<?> cls) { return managedTypes.containsKey(cls); }

}

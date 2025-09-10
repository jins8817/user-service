package com.intesi.usermanagement.domain;

import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(AbstractAudingEntity.class)
public abstract class AbstractAudingEntity_ {

	public static final String CREATED_DATE = "createdDate";
	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";

	
	/**
	 * @see com.intesi.usermanagement.domain.AbstractAudingEntity#createdDate
	 **/
	public static volatile SingularAttribute<AbstractAudingEntity, Instant> createdDate;
	
	/**
	 * @see com.intesi.usermanagement.domain.AbstractAudingEntity#lastModifiedDate
	 **/
	public static volatile SingularAttribute<AbstractAudingEntity, Instant> lastModifiedDate;
	
	/**
	 * @see com.intesi.usermanagement.domain.AbstractAudingEntity
	 **/
	public static volatile MappedSuperclassType<AbstractAudingEntity> class_;

}


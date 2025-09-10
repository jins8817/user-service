package com.intesi.usermanagement.domain;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static final String NAME = "name";
	public static final String ID = "id";

	
	/**
	 * @see com.intesi.usermanagement.domain.Role#name
	 **/
	public static volatile SingularAttribute<Role, RoleName> name;
	
	/**
	 * @see com.intesi.usermanagement.domain.Role#id
	 **/
	public static volatile SingularAttribute<Role, Long> id;
	
	/**
	 * @see com.intesi.usermanagement.domain.Role
	 **/
	public static volatile EntityType<Role> class_;

}


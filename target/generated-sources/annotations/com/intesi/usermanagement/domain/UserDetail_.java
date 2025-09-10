package com.intesi.usermanagement.domain;

import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserDetail.class)
public abstract class UserDetail_ extends com.intesi.usermanagement.domain.AbstractAudingEntity_ {

	public static final String COGNOME = "cognome";
	public static final String ROLES = "roles";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String CODICE_FISCALE = "codiceFiscale";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail#cognome
	 **/
	public static volatile SingularAttribute<UserDetail, String> cognome;
	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail#roles
	 **/
	public static volatile SetAttribute<UserDetail, Role> roles;
	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail#nome
	 **/
	public static volatile SingularAttribute<UserDetail, String> nome;
	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail#id
	 **/
	public static volatile SingularAttribute<UserDetail, Long> id;
	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail
	 **/
	public static volatile EntityType<UserDetail> class_;
	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail#codiceFiscale
	 **/
	public static volatile SingularAttribute<UserDetail, String> codiceFiscale;
	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail#email
	 **/
	public static volatile SingularAttribute<UserDetail, String> email;
	
	/**
	 * @see com.intesi.usermanagement.domain.UserDetail#username
	 **/
	public static volatile SingularAttribute<UserDetail, String> username;

}


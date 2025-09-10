package com.intesi.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.intesi.usermanagement.domain.UserDetail;

public interface UserDetailsRepository extends CrudRepository<UserDetail, Long>, JpaRepository<UserDetail, Long>,
		JpaSpecificationExecutor<UserDetail> {

	Optional<UserDetail> findByEmail(String email);


}

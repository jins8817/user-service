package com.intesi.usermanagement.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAudingEntity {

	@CreatedDate
	@JsonIgnore
	@Column(name = "created_at")
	@CreationTimestamp
	private Instant createdDate = Instant.now();

	@LastModifiedDate
	@JsonIgnore
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Instant lastModifiedDate = Instant.now();

}

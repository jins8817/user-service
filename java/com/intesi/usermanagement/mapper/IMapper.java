package com.intesi.usermanagement.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IMapper<E, D> {

	E toEntity(D dto);

	D toDto(E entity);

	Set<E> toEntity(Set<D> dtos);

	Set<D> toDto(Set<E> entities);

	List<E> toEntity(List<D> dtos);

	List<D> toDto(List<E> entities);

	Optional<E> toEntity(Optional<D> dto);

	Optional<D> toDto(Optional<E> entity);

}

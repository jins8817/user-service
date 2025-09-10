package com.intesi.usermanagement.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractMapper<E, D> implements IMapper<E, D> {

	@Override
	public Set<E> toEntity(Set<D> dtos) {
		if (dtos == null)
			return null;
		Set<E> entities = new HashSet<>();
		for (D dto : dtos) {
			entities.add(toEntity(dto));
		}
		return entities;
	}

	@Override
	public Set<D> toDto(Set<E> entities) {
		if (entities == null)
			return null;
		Set<D> dtos = new HashSet<>();
		for (E entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
	}

	@Override
	public List<D> toDto(List<E> entities) {
		if (entities == null)
			return null;
		List<D> dtos = new ArrayList<>();
		for (E entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
	}

	@Override
	public List<E> toEntity(List<D> dtos) {
		if (dtos == null)
			return null;
		List<E> entities = new ArrayList<>();
		for (D dto : dtos) {
			entities.add(toEntity(dto));
		}
		return entities;
	}

	@Override
	public Optional<E> toEntity(Optional<D> dto) {
		return dto.map(this::toEntity);
	}

	@Override
	public Optional<D> toDto(Optional<E> entity) {
		return entity.map(this::toDto);
	}

}

package com.intesi.usermanagement.mapper;

import org.springframework.stereotype.Component;

import com.intesi.usermanagement.domain.UserDetail;
import com.intesi.usermanagement.service.dto.UserDetailDTO;



@Component
public class UserDetailMapper extends AbstractMapper<UserDetail, UserDetailDTO> {

	
	@Override
	public UserDetail toEntity(UserDetailDTO dto) {
		if (dto == null)
			return null;
		UserDetail user = new UserDetail();
		user.setId(dto.getId());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setCodiceFiscale(dto.getCodiceFiscale());
		user.setNome(dto.getNome());
		user.setCognome(dto.getCognome());
		user.setRoles(dto.getRoles());
		user.setCreatedDate(dto.getCreatedDate());
		user.setLastModifiedDate(dto.getLastModifiedDate());
		return user;
	}

	@Override
	public UserDetailDTO toDto(UserDetail entity) {
		if (entity == null)
			return null;

		UserDetailDTO dto = new UserDetailDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setCodiceFiscale(entity.getCodiceFiscale());
		dto.setNome(entity.getNome());
		dto.setCognome(entity.getCognome());
		dto.setRoles(entity.getRoles());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setLastModifiedDate(entity.getLastModifiedDate());
		return dto;
	}

}

package com.intesi.usermanagement.service.dto;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intesi.usermanagement.domain.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailUpdateDTO {
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Generato dal sistema")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	@NotBlank
	private String username;
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Generato dal sistema")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;
	@NotBlank
	private String codiceFiscale;
	@NotBlank
    private String nome;
	@NotBlank
    private String cognome;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Generato dal sistema")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdDate;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Generato dal sistema")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Instant lastModifiedDate=Instant.now();
	
    private Set<Role> roles;
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (!(obj instanceof UserDetailUpdateDTO))
			return false;
		else
			return Objects.equals(((UserDetailUpdateDTO) obj).getId(), this.getId());
	}

}

package com.intesi.usermanagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "UserDetail")
@Table(name = "\"user\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail extends AbstractAudingEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username may not be blank")
	@Column(nullable = false, length = 100)
	private String username;

	@Email
	@NotBlank(message = "Email may not be blank")
	@Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "codice_fiscale", nullable = false, length = 16,unique = true)
    private String codiceFiscale;
    
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String cognome;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(name = "uk_user_role", columnNames = {"user_id", "role_id"})}
    )
    private Set<Role> roles = new HashSet<>();



	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (!(obj instanceof UserDetail))
			return false;
		else
			return Objects.equals(((UserDetail) obj).getId(), this.getId());
	}

}
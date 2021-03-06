package com.alvaro.useitspring.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends Usuarios {
	@Override
	@Transient
	/**
	 * This function returns a collection of player credentials.
	 * Returns the collection of granted authority for the user.
	 */ public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
	}
}

package com.baseproject.auth.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baseproject.auth.model.Profile;
import com.baseproject.auth.model.Role;
import com.baseproject.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetail implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String username;
	private static boolean isAccountNonLocked;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetail(Long id, String name, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetail create(User user) {

		Set<Role> roles = new HashSet<>();
		List<Profile> profiles = user.getProfiles();
		if (profiles != null) {
			for (Profile p : profiles) {
				roles.addAll(p.getRoles());
			}
		}
		
		isAccountNonLocked = null != user.getIsAccountNonLocked() ? user.getIsAccountNonLocked() : true;

		List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetail(user.getId(), user.getName(), user.getUsername(), user.getPassword(), authorities);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetail that = (UserDetail) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

}
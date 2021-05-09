package com.baseproject.auth.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "tb_user")
public class User {

	public User() {
		super();
	}

	public User(String name, String username, String password, String uuid, List<Profile> profiles) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.uuid = uuid;
		this.profiles = profiles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@NotBlank
	private String username;

	private String password;

	@NotBlank
	private String name;

	private String tel;

	private String uuid;

	private String recoverUuid;

	private LocalDateTime recoverExpiration;

	private Boolean isAccountNonLocked;

	@ManyToMany
	@JoinTable(name = "tb_user_profile", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "profile_id") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Profile> profiles = new ArrayList<>();

	@Transient
	private List<Profile> avaliableProfiles = new ArrayList<>();

}

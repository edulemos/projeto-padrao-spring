package com.baseproject.parameterization.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_funcionalidade")
public class Funcionalidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false, length = 100, unique = true)
	private String nome;

	@NotBlank
	@Column(nullable = false, length = 255)
	private String descricao;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "funcionalidade")
	private List<Parametro> parametros;

	@Transient
	private String funcionalidadeReplicada;
	
	private String uuid;


}

package com.baseproject.parameterization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_parametro")
public class Parametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = true, length = 255)
	private String descricao;

	@NotBlank
	@Column(nullable = false, length = 255)
	private String valor;

	@ManyToOne
	@JoinColumn(name = "funcionalidade_id", nullable = false)
	private Funcionalidade funcionalidade;

	private String uuid;

}

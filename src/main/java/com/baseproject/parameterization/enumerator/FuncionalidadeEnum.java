package com.baseproject.parameterization.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FuncionalidadeEnum {

	SYSTEM("SYSTEM", "parametros internos sistema"),
	CORREIOS("CORREIOS", "parametros correios");

	private String nome;
	private String descricao;

}

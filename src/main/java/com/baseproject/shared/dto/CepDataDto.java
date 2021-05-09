package com.baseproject.shared.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepDataDto {
	
	private String uf;
	private String localidade;
	private String locNu;
	private String localidadeSubordinada;
	private String logradouroDNEC;
	private String logradouroTextoAdicional;
	private String logradouroTexto;
	private String bairro;
	private String baiNu;
	private String nomeUnidade;
	private String cep;
	private String tipoCep;
	private String numeroLocalidade;
	private String situacao;
	private List<Object> faixasCaixaPostal;
	private List<Object> faixasCep;

}

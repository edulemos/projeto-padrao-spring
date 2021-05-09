package com.baseproject.shared.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepResponseDto {
	
	private boolean erro;
	private String mensagem;
	private int total;
	private List<CepDataDto> dados;

}

package com.baseproject.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.baseproject.parameterization.enumerator.ParametrosEnum;
import com.baseproject.shared.dto.CepDataDto;
import com.baseproject.shared.dto.CepResponseDto;

@Component
public class BuscaCepUtil {

	public CepDataDto buscaCep(String cep) {

		RestTemplate restTemplate = new RestTemplate();
		String endpoint = ServiceUtil.getParameter(ParametrosEnum.ENDPOINT_CEP);
		String url = endpoint  + "?endereco=" + cep + "&tipoCEP=ALL";
		ResponseEntity<CepResponseDto> response = restTemplate.getForEntity(url, CepResponseDto.class);

		if (null != response.getBody() && null != response.getBody().getDados() && !response.getBody().getDados().isEmpty()) {
			CepDataDto cepDataDto = response.getBody().getDados().get(0);
			String logradouroDNEC = cepDataDto.getLogradouroDNEC();
			try {
				cepDataDto.setLogradouroDNEC(logradouroDNEC.split("-")[0].trim());
			} catch (Exception e) {
			}
			
			return cepDataDto;
		} else {
			return new CepDataDto();
		}

	}

}



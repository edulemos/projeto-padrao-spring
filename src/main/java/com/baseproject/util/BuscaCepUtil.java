package com.baseproject.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
		String parametros = "endereco=" + cep + "&tipoCEP=ALL";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> request = new HttpEntity<>(parametros, headers);

		CepResponseDto cepResponse = restTemplate.postForObject(endpoint, request, CepResponseDto.class);

		if (null != cepResponse && null != cepResponse.getDados()) {
			CepDataDto cepDataDto = cepResponse.getDados().get(0);
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

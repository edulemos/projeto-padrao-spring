package com.baseproject.util;

import java.util.List;
import java.util.UUID;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baseproject.auth.dto.SessionDto;
import com.baseproject.parameterization.enumerator.ParametrosEnum;
import com.baseproject.parameterization.model.Parametro;

public class ServiceUtil {

	private ServiceUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String generateUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static Long getIdUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		SessionDto dto = (SessionDto) attr.getRequest().getSession(true).getAttribute("sessionDto");
		return null != dto ? dto.getIdUser() : null;
	}

	public static String getNomeUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		SessionDto dto = (SessionDto) attr.getRequest().getSession(true).getAttribute("sessionDto");
		return null != dto ? dto.getNomeUser() : null;
	}

	public static String getParameter(ParametrosEnum parametro) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		SessionDto dto = (SessionDto) attr.getRequest().getSession(true).getAttribute("sessionDto");
		List<Parametro> parametros = dto.getParametros();
		Parametro p = parametros.stream().filter(prm -> parametro.toString().equals(prm.getNome())).findAny().orElse(null);
		return null != p ? p.getValor() : "";
	}

	public static boolean isEmpty(String str) {
		return null == str || str.isEmpty();
	}

	public static boolean notEmpty(String str) {
		return null != str && !str.isEmpty();
	}

	public static Integer strToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

}

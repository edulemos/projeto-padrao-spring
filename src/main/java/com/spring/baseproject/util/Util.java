package com.spring.baseproject.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class Util {

	@Autowired
	private MessageSource messageSource;

	public String getMsg(String key) {
		return messageSource.getMessage(key, null, new Locale("pt", "BR"));
	}

	public String getMsg(String key, String[] args) {
		return messageSource.getMessage(key, args, new Locale("pt", "BR"));
	}

	public String encryptMD5(String s) {
		if (null == s || "".equals(s.trim()))
			return null;
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(), 0, s.length());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new BigInteger(1, m.digest()).toString(16);
	}

	public boolean emailValido(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static void main(String[] args) {
		String senhaCripto = new Util().encryptMD5("101010");
		System.out.println(senhaCripto);
	}

}

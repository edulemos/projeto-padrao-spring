package com.baseproject.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {

	private PageableUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static Pageable getPageable(Integer limit) {
		return PageRequest.of(0, limit);
	}

	public static Pageable getPageable() {
		return PageRequest.of(0, 100);
	}
	
	public static Pageable getPageable(String sort) {
		return PageRequest.of(0, 100, Sort.by(Sort.Order.asc(sort).ignoreCase()));
	}

}

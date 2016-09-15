package com.spring.baseproject.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtil {
	
	public static String dataHoraString(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
}

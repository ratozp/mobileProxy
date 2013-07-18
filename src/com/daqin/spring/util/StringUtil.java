package com.daqin.spring.util;

public class StringUtil{

	public static boolean isBlank(String str){
		return null==str||"".equals(str);
	}
	
	public static boolean isNotBlank(String str){
		return null!=str&&(!"".equals(str));
	}
	
	public static boolean isNumeric(String str){
		if(null==str||"".equals(str)){
			return false;
		}
		return str.matches("^\\d+$");
	}
}

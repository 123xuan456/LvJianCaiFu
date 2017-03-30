package com.ryan.slidefragment.utils;

import com.google.gson.Gson;

/**
 * 使用json进行类型转换
 * 
 * @author de
 * 
 */

public class JsonUtils {

	public static <T> T parser(String jsonResult, Class<T> clazz) {
		Gson gson = null;
		if (null == gson) {
			gson = new Gson();
		}
		T t = gson.fromJson(jsonResult, clazz);
		return t;
	}

	public static <T> String String2Jison(T obj) {
		Gson gson = null;
		if (null == gson) {
			gson = new Gson();
		}
		String json = gson.toJson(obj);
		return json;
	}
}

package com.volley;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class JsonJudger {
	public static boolean JsonJudger(String jsonStr,String key,String valueTure){
		
		try {
			if (jsonStr != null) {
				JSONObject jsonObject = new JSONObject(jsonStr);
				String statuses_code  = jsonObject.get(key).toString().trim();
				if (TextUtils.isEmpty(key)||TextUtils.isEmpty(jsonStr)||TextUtils.isEmpty(valueTure)) {
					return false;
				}else {
					if (valueTure.equals(statuses_code)) {
						return true;
					}else {
						return false;
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}

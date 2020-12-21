package com.lukec.ratpack.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils {
    
    public static String toJson(Object objectPassed) {
	Gson gson = new GsonBuilder().setDateFormat("dd MMM yy HH:mm").enableComplexMapKeySerialization().create();
	return gson.toJson(objectPassed);
    }
    
}

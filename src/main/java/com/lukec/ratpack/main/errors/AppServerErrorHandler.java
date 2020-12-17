package com.lukec.ratpack.main.errors;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.netty.handler.codec.http.HttpResponseStatus;
import ratpack.error.ServerErrorHandler;
import ratpack.handling.Context;

public class AppServerErrorHandler implements ServerErrorHandler {
    
	@Override
	public void error(Context context, Throwable throwable) throws Exception {
		// This lets us throw a JSON error for the API rather than the standard error page in Ratpack
		try {
		      Map<String, String> errors = new HashMap<>();

		      errors.put("error", throwable.getClass().getCanonicalName());
		      errors.put("message", throwable.getMessage());

		      Gson gson = new GsonBuilder().serializeNulls().create();

		      context.getResponse().status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).send(gson.toJson(errors));
		      throw throwable;
		    } catch (Throwable throwable1) {
		      throwable1.printStackTrace();
		    }
		
	}
}


package com.lukec.ratpack.main.errors;

import ratpack.error.ServerErrorHandler;
import ratpack.handling.Context;

public class AppServerErrorHandler implements ServerErrorHandler {
    
	@Override
	public void error(Context context, Throwable throwable) throws Exception {
		 context.render("error500.html");
		
	}
}


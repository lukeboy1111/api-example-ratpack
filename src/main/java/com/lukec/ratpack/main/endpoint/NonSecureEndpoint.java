package com.lukec.ratpack.main.endpoint;

import ratpack.func.Action;
import ratpack.handling.Chain;

import java.util.HashMap;
import java.util.Map;

import com.lukec.ratpack.service.LoginService;

import static ratpack.jackson.Jackson.json;

/**
 * REST endpoint that is not secured by JWT.
 */
public class NonSecureEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
    	// This is the login call that generates the new token
    	chain.prefix("login", c -> {
    		c.get(ctx -> {
                LoginService loginService = ctx.get(LoginService.class);
                ctx.render(loginService.render(ctx));
              });
        });
    	chain.get("", ctx -> {
            Map<String, String> response = new HashMap<>();
            response.put("message", "This endpoint is NOT protected by JWT");
            ctx.render(json(response));
        });
    }
}

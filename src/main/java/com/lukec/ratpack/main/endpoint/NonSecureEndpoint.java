package com.lukec.ratpack.main.endpoint;

import static ratpack.groovy.Groovy.groovyTemplate;
import static ratpack.jackson.Jackson.json;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import com.lukec.ratpack.service.InitService;
import com.lukec.ratpack.service.LoginService;

import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * REST endpoint that is not secured by JWT.
 */
public class NonSecureEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
    	// This is the login call that generates the new token
    	chain.prefix("login", c -> {
    		c.post(ctx -> {
    		LoginService loginService = ctx.get(LoginService.class);
                String token = loginService.render(ctx);
                InitService initService = ctx.get(InitService.class);
                initService.checkUserInitialised(token);
                final Map<String, Object> model = Maps.newHashMap();
                model.put("token", token);
                ctx.render(groovyTemplate(model, "jwt.html"));
              })
    		;
        });
    	chain.get("", ctx -> {
            Map<String, String> response = new HashMap<>();
            response.put("message", "This endpoint is NOT protected by JWT");
            ctx.render(json(response));
        });
    }
}

package com.lukec.ratpack.main.endpoint;

import static ratpack.groovy.Groovy.groovyTemplate;

import java.util.HashMap;
import java.util.Map;

import org.pac4j.http.client.direct.ParameterClient;

import com.google.common.collect.Maps;
import com.lukec.ratpack.bo.JwtCollection;
import com.lukec.ratpack.bo.Secret;
import com.lukec.ratpack.service.LoginService;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.pac4j.RatpackPac4j;

/**
 * REST endpoint that is not secured by JWT.
 */
public class NonSecureEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
    	// This is the login call that generates the new token
    	chain.prefix("login", c -> {
    		c.post(ctx -> {
    		    Secret theSecret = ctx.get(Secret.class);
    		    LoginService loginService = ctx.get(LoginService.class);
    		    JwtCollection jwt = loginService.render(theSecret);
                    String token = jwt.getJwtToken();
                    ParameterClient parameterClient = jwt.getParameterClient();
                    RatpackPac4j.authenticator("callback", parameterClient);
                    final Map<String, Object> model = Maps.newHashMap();
                    model.put("token", token);
                    ctx.render(groovyTemplate(model, "jwt.html"));
              });
        });
    	chain.get("", ctx -> {
            Map<String, String> response = new HashMap<>();
            response.put("message", "This endpoint is NOT protected by JWT");
            ctx.render(groovyTemplate(response, "error401.html"));
        });
    }
}

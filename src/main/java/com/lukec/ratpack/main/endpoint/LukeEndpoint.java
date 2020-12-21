package com.lukec.ratpack.main.endpoint;

import com.lukec.ratpack.service.DescriptionService;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.jackson.Jackson;

public class LukeEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
	// This is the login call that generates the new token
    	chain.prefix("luke", c -> {
    		c.get(ctx -> {
    		    DescriptionService service = ctx.get(DescriptionService.class);
    		    service.makeDescription().map(Jackson::json).then(ctx::render);
              });
        });
	
    }

}

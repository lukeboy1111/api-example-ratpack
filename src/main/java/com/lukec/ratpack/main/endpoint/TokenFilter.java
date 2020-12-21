package com.lukec.ratpack.main.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ratpack.func.Action;
import ratpack.handling.Chain;

public class TokenFilter implements Action<Chain> {
    final static Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    
    @Override
    public void execute(Chain chain) throws Exception {
	chain.prefix("spend", c -> {
	    logger.debug("spend post call");
	    c.post(ctx -> {
		ctx.next();
	    });
	});
	chain.prefix("transactions", c -> {
	    logger.debug("tx call");
	    c.get(ctx -> {
		ctx.next();
	    });
	});
	chain.prefix("balance", c -> {
	    logger.debug("balance call");
	    c.get(ctx -> {
		ctx.next();
	    });
	});
	chain.all(ctx -> {
            logger.debug("All other calls");
            ctx.next();
        });
    }

}

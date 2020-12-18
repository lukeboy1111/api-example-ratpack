package com.lukec.ratpack.main.endpoint;

import com.lukec.ratpack.service.BalanceService;
import com.lukec.ratpack.service.TransactionService;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.jackson.Jackson;

/**
 * REST endpoint that is secured by JWT.
 */
public class SecureEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {

	// Secured with Authorization Header Token
	chain.prefix("balance", c -> {
	    // c.all(RatpackPac4j.requireAuth(HeaderClient.class));
	    c.get(ctx -> {
		BalanceService service = ctx.get(BalanceService.class);
		service.getBalance(ctx).map(Jackson::json).then(ctx::render);
	    });
	});

	// Secured with Authorization Header Token
	chain.prefix("transactions", c -> {
	    // c.all(RatpackPac4j.requireAuth(HeaderClient.class));
	    c.get(ctx -> {
		TransactionService service = ctx.get(TransactionService.class);
		service.getTransactions(ctx).map(Jackson::json).then(ctx::render);
	    });
	});

	// Secured with Authorization Header Token
	chain.prefix("spend", c -> {
	    // c.all(RatpackPac4j.requireAuth(HeaderClient.class));
	    c.post(ctx -> {
		TransactionService service = ctx.get(TransactionService.class);
		service.spend(ctx).map(Jackson::json).then(ctx::render);
	    });
	});

    }
}

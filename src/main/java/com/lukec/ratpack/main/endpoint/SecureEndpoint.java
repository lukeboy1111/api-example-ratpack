package com.lukec.ratpack.main.endpoint;

import com.google.gson.Gson;
import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.service.BalanceService;

import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * REST endpoint that is secured by JWT.
 */
public class SecureEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {

	// Secured with Authorization Header Token
	chain.prefix("balance", c -> {
	    c.get(ctx -> {
		final String token = ctx.getRequest().getHeaders().get("Authorization");
		BalanceService balanceService = ctx.get(BalanceService.class);
		UserBalance bal = balanceService.retrieveFullBalance(token);
		Gson gson = new Gson();
		String balanceText = gson.toJson(bal);
		ctx.render(balanceText);
		ctx.next();
	    });
	});

    }
}

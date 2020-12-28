package com.lukec.ratpack.main.endpoint;

import static ratpack.groovy.Groovy.groovyTemplate;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.net.HttpHeaders;
import com.lukec.ratpack.bo.Transaction;
import com.lukec.ratpack.service.TransactionService;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.jackson.Jackson;

public class TransactionEndpoint implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {

	chain.prefix("spend", c -> {
	    c.post(ctx -> {
		TransactionService service = ctx.get(TransactionService.class);
		final String token = ctx.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
		final Map<String, Object> model = Maps.newHashMap();
		ctx.parse(Jackson.fromJson(Transaction.class)).then(t -> service.spend(token, t));
		ctx.render(groovyTemplate(model, "spend.html"));

	    });
	});

	// Secured with Authorization Header Token
	chain.prefix("transactions", c -> {
	    c.get(ctx -> {
		TransactionService service = ctx.get(TransactionService.class);
		final String token = ctx.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
		service.getTransactions(token).map(Jackson::json).then(ctx::render);
	    });
	});
    }
}

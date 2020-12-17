package com.lukec.ratpack.main.endpoint;

import static ratpack.jackson.Jackson.json;

import org.pac4j.http.client.direct.HeaderClient;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.service.BalanceService;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.pac4j.RatpackPac4j;

/**
 * REST endpoint that is secured by JWT.
 */
public class SecureEndpoint implements Action<Chain> {

	@Override
	public void execute(Chain chain) throws Exception {
		

		// Secured with Authorization Header Token
		chain.prefix("balance", c -> {
			c.all(RatpackPac4j.requireAuth(HeaderClient.class));

			c.get(ctx -> {

				//UserProfile profile = ctx.get(UserProfile.class);
				//String token = (String) profile.getAuthenticationAttribute("token");
				//Map<String, Object> userInfo = new HashMap<>();
				//userInfo.put("email", profile.getAttribute("email"));
				//userInfo.put("displayName", profile.getAttribute("display_name"));
				//userInfo.put("roles", profile.getRoles());
			        String token = "";
				BalanceService balanceService = ctx.get(BalanceService.class);
				UserBalance balance = balanceService.getBalance(token);

				ctx.render(json(balance));
			});
		});
	}
}

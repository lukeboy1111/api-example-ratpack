package com.lukec.ratpack.main.endpoint;

import static ratpack.jackson.Jackson.json;

import java.util.HashMap;
import java.util.Map;

import org.pac4j.core.profile.UserProfile;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;

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
		// Secured with URL Parameter Token
		chain.prefix("secure", c -> {
			c.all(RatpackPac4j.requireAuth(ParameterClient.class));
			c.get(ctx -> {
				UserProfile profile = ctx.get(UserProfile.class);

				Map<String, Object> userInfo = new HashMap<>();
				userInfo.put("email", profile.getAttribute("email"));
				userInfo.put("displayName", profile.getAttribute("display_name"));
				userInfo.put("roles", profile.getRoles());

				Map<String, Object> response = new HashMap<>();
				response.put("message", "This endpoint is protected by JWT");
				response.put("user", userInfo);

				ctx.render(json(response));
			});
		});

		// Secured with Authorization Header Token
		chain.prefix("balance", c -> {
			c.all(RatpackPac4j.requireAuth(HeaderClient.class));

			c.get(ctx -> {

				UserProfile profile = ctx.get(UserProfile.class);
				profile.getAuthenticationAttribute("token");
				Map<String, Object> userInfo = new HashMap<>();
				userInfo.put("email", profile.getAttribute("email"));
				userInfo.put("displayName", profile.getAttribute("display_name"));
				userInfo.put("roles", profile.getRoles());

				BalanceService balanceService = ctx.get(BalanceService.class);
				UserBalance balance = balanceService.getBalance(profile);

				ctx.render(json(balance));
			});
		});
	}
}

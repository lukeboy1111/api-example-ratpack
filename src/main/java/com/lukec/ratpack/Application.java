package com.lukec.ratpack;

import com.lukec.ratpack.config.SecretsConfig;
import com.lukec.ratpack.main.endpoint.NonSecureEndpoint;
import com.lukec.ratpack.main.endpoint.SecureEndpoint;
import com.lukec.ratpack.main.errors.AppServerErrorHandler;
import com.lukec.ratpack.registry.ModuleRegister;

import ratpack.error.ServerErrorHandler;
import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class Application {

	public static void main(String[] args) throws Exception {

		RatpackServer.start(s -> s
				.serverConfig(c -> c.yaml("config.yaml").yaml("redis.yaml")
						.require("/secrets-config", SecretsConfig.class).baseDir(BaseDir.find()).build())
				.registry(Guice.registry(bindingsSpec -> bindingsSpec.module(ModuleRegister.class)
						.bind(ServerErrorHandler.class, AppServerErrorHandler.class)))
				.handlers(chain -> chain.insert(NonSecureEndpoint.class).insert(SecureEndpoint.class)
						.all(ctx -> ctx.render("root handler!"))));
	}
}

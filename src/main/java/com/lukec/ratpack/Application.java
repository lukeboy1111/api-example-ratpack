package com.lukec.ratpack;

import java.io.File;

import com.lukec.ratpack.bo.Secret;
import com.lukec.ratpack.main.endpoint.NonSecureEndpoint;
import com.lukec.ratpack.main.endpoint.SecureEndpoint;
import com.lukec.ratpack.main.errors.AppServerErrorHandler;
import com.lukec.ratpack.registry.ModuleRegister;

import ratpack.error.ServerErrorHandler;
import ratpack.groovy.template.TextTemplateModule;
import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import ratpack.session.SessionModule;

public class Application {

	public static void main(String[] args) throws Exception {

		RatpackServer.start(s -> s
				.serverConfig(config -> 
					config.baseDir(new File("src/main").getAbsoluteFile())
					.yaml("config.yaml")
					.yaml("redis.yaml")
					.require("/secrets-config", Secret.class)
					.baseDir(BaseDir.find()).build()
				)
				.registry(Guice.registry(bindingsSpec -> bindingsSpec.module(ModuleRegister.class)
						.bind(ServerErrorHandler.class, AppServerErrorHandler.class)
						.module(TextTemplateModule.class)
						.module(SessionModule.class)
					)
						
				)
				.handlers(chain -> 
						chain.insert(NonSecureEndpoint.class)
						.insert(SecureEndpoint.class)
						.all(ctx -> ctx.render("root handler!"))
				)
				);
	}
}

package com.lukec.ratpack;

import static ratpack.groovy.Groovy.groovyTemplate;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lukec.ratpack.bo.Secret;
import com.lukec.ratpack.main.endpoint.LukeEndpoint;
import com.lukec.ratpack.main.endpoint.NonSecureEndpoint;
import com.lukec.ratpack.main.endpoint.SecureEndpoint;
import com.lukec.ratpack.main.endpoint.TransactionEndpoint;
import com.lukec.ratpack.main.errors.AppServerErrorHandler;
import com.lukec.ratpack.redis.ConfigurationModule;
import com.lukec.ratpack.redis.RedisConfig;
import com.lukec.ratpack.registry.ModuleRegister;

import ratpack.error.ClientErrorHandler;
import ratpack.error.ServerErrorHandler;
import ratpack.groovy.template.TextTemplateModule;
import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import ratpack.session.SessionModule;

public class Application {
    	
    	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    
	public static void main(String[] args) throws Exception {

		RatpackServer.start(s -> s
				.serverConfig(config -> 
					config.baseDir(new File("src/main").getAbsoluteFile())
					.yaml("config.yaml")
					.yaml("redis.yaml")
					.require("/secrets-config", Secret.class)
					.require("/redis", RedisConfig.class)
					.baseDir(BaseDir.find()).build()
					
				)
				.registry(Guice.registry(bindingsSpec -> bindingsSpec.module(ModuleRegister.class)
						.bind(ServerErrorHandler.class, AppServerErrorHandler.class)
						.bindInstance(ClientErrorHandler.class, (ctx, statusCode) -> {
			                            ctx.getResponse().status(statusCode);
			                            if (statusCode == 404) {
			                                ctx.render(groovyTemplate("error404.html"));
			                            } else if (statusCode == 401) {
			                                ctx.render(groovyTemplate("error401.html"));
			                            } else if (statusCode == 403) {
			                                ctx.render(groovyTemplate("error403.html"));
			                            } else {
			                                LOGGER.error("Unexpected: {}", statusCode);
			                            }
			                        })
						.module(ConfigurationModule.class)
						.module(TextTemplateModule.class)
						.module(SessionModule.class)
						
					)
						
				)
				.handlers(chain -> 
						chain
						//.insert(TokenFilter.class)
						.insert(NonSecureEndpoint.class)
						.insert(SecureEndpoint.class)
						.insert(TransactionEndpoint.class)
						.insert(LukeEndpoint.class)
						
				)
				);
	}
}

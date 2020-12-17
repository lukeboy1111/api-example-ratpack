package com.lukec.ratpack;

import ratpack.error.ServerErrorHandler;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

import java.util.Arrays;
import java.util.Map;

import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;

import com.lukec.ratpack.main.SecretsConfig;
import com.lukec.ratpack.main.endpoint.NonSecureEndpoint;
import com.lukec.ratpack.main.endpoint.SecureEndpoint;
import com.lukec.ratpack.main.errors.AppServerErrorHandler;
import com.lukec.ratpack.main.handler.ApplicationHandler;
import com.lukec.ratpack.registry.ModuleRegister;
import com.lukec.ratpack.service.LoginService;

public class Application {

  public static void main(String[] args) throws Exception {
	  
    RatpackServer.start(s -> s
    		.serverConfig(c -> c
                    .yaml("config.yaml")
                    .yaml("redis.yaml")
                    .require("/secrets-config", SecretsConfig.class)
                    .baseDir(BaseDir.find()).build()
                    )
        .registry(Guice.registry(bindingsSpec -> bindingsSpec.module(ModuleRegister.class)
        		.bind(ServerErrorHandler.class, AppServerErrorHandler.class)
        		))
        .handlers(chain -> chain
            .insert(NonSecureEndpoint.class)
            .insert(SecureEndpoint.class)
            .all(ctx -> ctx.render("root handler!"))
        )
    );
  }
}

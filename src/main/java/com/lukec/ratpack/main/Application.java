package com.lukec.ratpack.main;

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

import com.lukec.ratpack.main.handler.ApplicationHandler;
import com.lukec.ratpack.module.ModuleRegister;
import com.lukec.ratpack.service.LoginService;

public class Application {

  public static void main(String[] args) throws Exception {
	  /*
	  String secret = System.getProperty("JWT_SHARED_SECRET");
	  final SignatureConfiguration signatureConfiguration = new SecretSignatureConfiguration(secret);
      final EncryptionConfiguration encryptionConfiguration = new SecretEncryptionConfiguration(secret);
      final JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(Arrays.asList(signatureConfiguration), Arrays.asList(encryptionConfiguration));

      // Use this if you are going to pass the JWT as a URL parameter
      final ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator);

      // Use this if you are going to pass the JWT in the Authorization header
      final HeaderClient headerClient = new HeaderClient("Authorization", "Bearer ", jwtAuthenticator);

      parameterClient.setSupportGetRequest(true);
      parameterClient.setSupportPostRequest(false);
    */  
    RatpackServer.start(s -> s
    		.serverConfig(c -> c
                    .yaml("config.yaml")
                    .require("/config", SecretsConfig.class)
                    .baseDir(BaseDir.find()).build())
        .registry(Guice.registry(b -> b.module(ModuleRegister.class)))
        .handlers(chain -> chain
            .path("foo", ctx -> ctx.render("from the foo handler")) // Map to /foo
            .path("bar", ctx -> ctx.render("from the bar handler")) // Map to /bar
            .prefix("login", nested -> { // Set up a nested routing block, which is delegated to `nestedHandler`
                nested.path("", ctx -> { // The path tokens are the :var1 and :var2 path components above
                  LoginService loginService = ctx.get(LoginService.class);
                  ctx.render(loginService.render(ctx));
                });
              })
            .prefix("nested", nested -> { // Set up a nested routing block, which is delegated to `nestedHandler`
              nested.path(":var1/:var2?", ctx -> { // The path tokens are the :var1 and :var2 path components above
            	
                Map<String, String> pathTokens = ctx.getPathTokens();
                ctx.render(
                  "from the nested handler, var1: " + pathTokens.get("var1") +
                    ", var2: " + pathTokens.get("var2")
                );
              });
            })
            .path("injected", ApplicationHandler.class) // Map to a dependency injected handler
            .prefix("static", nested -> nested.fileSystem("assets/images", Chain::files)) // Bind the /static app path to the src/ratpack/assets/images dir
            .all(ctx -> ctx.render("root handler!"))
        )
    );
  }
}

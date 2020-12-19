package com.lukec.ratpack.service.impl;

import java.util.Arrays;

import javax.inject.Inject;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.definition.CommonProfileDefinition;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;

import com.lukec.ratpack.bo.JwtCollection;
import com.lukec.ratpack.bo.Secret;
import com.lukec.ratpack.service.InitService;
import com.lukec.ratpack.service.LoginService;

public class LoginServiceImpl implements LoginService {
    	private InitService initService;
    	
    	@Inject
    	public LoginServiceImpl(InitService init) {
    	    this.initService = init;
    	}
    	
	@Override
	public JwtCollection render(Secret theSecret) throws Exception {
	    	JwtCollection jwt = getKey("test@test.com", "Test Person", "READ_WRITE", theSecret.getSecret());
	    	String token = jwt.getJwtToken();
	    	initService.checkUserInitialised(token);
		return jwt;
	}

	private JwtCollection getKey(String email, String displayName, String role, String secret) throws Exception {
		if (null == secret) {
			throw new Exception("Secret key cannot be null");
		}
		CommonProfile profile = new CommonProfile();
		profile.addAttribute(CommonProfileDefinition.EMAIL, email);
		profile.addAttribute(CommonProfileDefinition.DISPLAY_NAME, displayName);
		profile.addRole(role);

		System.err.println("SECRET KEY is " + secret);
		final SignatureConfiguration signatureConfiguration = new SecretSignatureConfiguration(secret);
		final EncryptionConfiguration encryptionConfiguration = new SecretEncryptionConfiguration(secret);
		final JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(Arrays.asList(signatureConfiguration), Arrays.asList(encryptionConfiguration));
                final ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator);
                String name = parameterClient.getParameterName();
                parameterClient.setSupportGetRequest(false);
                parameterClient.setSupportPostRequest(true);
		JwtGenerator generator = new JwtGenerator(signatureConfiguration, encryptionConfiguration);
		String jwt = generator.generate(profile);
		JwtCollection collection = new JwtCollection(jwtAuthenticator, generator, parameterClient, jwt);
		return collection;
	}

}

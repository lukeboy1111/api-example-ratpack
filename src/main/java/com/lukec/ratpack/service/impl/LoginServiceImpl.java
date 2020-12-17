package com.lukec.ratpack.service.impl;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.definition.CommonProfileDefinition;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;

import com.lukec.ratpack.bo.Secret;
import com.lukec.ratpack.service.LoginService;

import ratpack.handling.Context;

public class LoginServiceImpl implements LoginService {

	@Override
	public String render(Context ctx) throws Exception {
	    	Secret theSecret = ctx.get(Secret.class);
		
		return getKey("test@test.com", "Test Person", "READ_WRITE", theSecret.getSecret());
	}

	private String getKey(String email, String displayName, String role, String secret) throws Exception {
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

		JwtGenerator generator = new JwtGenerator(signatureConfiguration, encryptionConfiguration);
		String jwt = generator.generate(profile);

		return jwt;
	}

}

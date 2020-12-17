package com.lukec.ratpack.service.impl;

import com.lukec.ratpack.service.LoginService;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.definition.CommonProfileDefinition;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;

import ratpack.handling.Context;

public class LoginServiceImpl implements LoginService {

	@Override
	public String render(Context ctx) {
		return getKey("test@test.com", "Test Person", "READ_WRITE", "kSuZdhba2bXoV5O1rLdsI4yEX1pIAE2kkSuZdhba2bXoV5O1rLdsI4yEX1pIAE2kkSuZdhba2bXoV5O1rLdsI4yEX1pIAE2kkSuZdhba2bXoV5O1rLdsI4yEX1pIAE2k");
	}
	
	
	private String getKey(String email, String displayName, String role, String secret) {


        final SignatureConfiguration signatureConfiguration = new SecretSignatureConfiguration(secret);
        final EncryptionConfiguration encryptionConfiguration = new SecretEncryptionConfiguration(secret);

        CommonProfile profile = new CommonProfile();
        profile.addAttribute(CommonProfileDefinition.EMAIL, email);
        profile.addAttribute(CommonProfileDefinition.DISPLAY_NAME, displayName);
        profile.addRole(role);

        JwtGenerator generator = new JwtGenerator(signatureConfiguration, encryptionConfiguration);
        String jwt = generator.generate(profile);

        return jwt;
    }

}

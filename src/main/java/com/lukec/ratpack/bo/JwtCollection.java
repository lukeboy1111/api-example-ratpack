package com.lukec.ratpack.bo;

import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtCollection {
    JwtAuthenticator jwtAuthenticator;
    JwtGenerator generator;
    ParameterClient parameterClient;
    String jwtToken;
}

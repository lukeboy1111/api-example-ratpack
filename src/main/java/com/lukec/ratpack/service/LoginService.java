package com.lukec.ratpack.service;

import com.lukec.ratpack.bo.JwtCollection;
import com.lukec.ratpack.bo.Secret;

public interface LoginService {

    JwtCollection render(Secret theSecret) throws Exception;

}

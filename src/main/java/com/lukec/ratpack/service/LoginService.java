package com.lukec.ratpack.service;

import com.lukec.ratpack.bo.JwtCollection;

import ratpack.handling.Context;

public interface LoginService {

    JwtCollection render(Context ctx) throws Exception;

}

package com.lukec.ratpack.service;

import ratpack.handling.Context;

public interface InitService {
    void checkUserInitialised(final Context ctx, String token);
}

package com.lukec.ratpack.service.impl;

import com.lukec.ratpack.descriptor.LukeCampbell;
import com.lukec.ratpack.service.DescriptionService;

import ratpack.exec.Promise;

public class DescriptionServiceImpl implements DescriptionService {

    @Override
    public Promise<LukeCampbell> makeDescription() {
	return Promise.value(new LukeCampbell());
    }

}

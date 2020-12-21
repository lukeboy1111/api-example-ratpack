package com.lukec.ratpack.service;

import com.lukec.ratpack.descriptor.LukeCampbell;

import ratpack.exec.Promise;

public interface DescriptionService {
    Promise<LukeCampbell> makeDescription();
}

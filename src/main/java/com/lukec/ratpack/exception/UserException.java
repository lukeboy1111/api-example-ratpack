package com.lukec.ratpack.exception;

public class UserException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1810194456697244343L;

    public UserException(String errorMessage, Throwable err) {
	super(errorMessage, err);
    }

}

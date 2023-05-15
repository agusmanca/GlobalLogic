package com.globallogic.challenge.globallogicchallenge.exception.errorModels;

public class BadRequestEx extends Exception {

    public static final String DESCRIPTION = "BadRequest:";

    public BadRequestEx() {
        super(DESCRIPTION);
    }

    public BadRequestEx(String detail) {
        super(DESCRIPTION + " " + detail);
    }
}

package com.globallogic.challenge.globallogicchallenge.exception.errorModels;

public class InternalServerErrorEx extends Exception {
    public static final String DESCRIPTION = "Internal Server Error:";

    public InternalServerErrorEx() {
        super(DESCRIPTION);
    }

    public InternalServerErrorEx(String detail) {
        super(DESCRIPTION + " " + detail);
    }
}

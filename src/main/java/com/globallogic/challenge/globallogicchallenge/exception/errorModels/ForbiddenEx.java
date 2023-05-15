package com.globallogic.challenge.globallogicchallenge.exception.errorModels;

public class ForbiddenEx extends Exception {
    public static final String DESCRIPTION = "Forbidden Access:";

    public ForbiddenEx() {
        super(DESCRIPTION);
    }

    public ForbiddenEx(String detail) {
        super(DESCRIPTION + " " + detail);
    }
}

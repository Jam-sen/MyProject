package com.ys;

import java.io.PrintStream;

public class ExceptionOne extends Exception {
    public ExceptionOne() {
        super ();
    }

    public ExceptionOne(String message) {
        super (message);
    }

    @Override
    public String getMessage() {
        return super.getMessage ();
    }
}



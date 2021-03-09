package com.oddschecker.odds.exception;

import java.io.Serializable;

public class InvalidOddsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7354593324049098003L;

    public InvalidOddsException(String message) {
        super(message);
    }

}

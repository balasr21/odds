package com.oddschecker.odds.exception;

import java.io.Serializable;

public class InvalidBetException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2660949029853424074L;

    public InvalidBetException(String message) {
        super(message);
    }

}

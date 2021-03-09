package com.oddschecker.odds.exception;

import java.io.Serializable;

public class InvalidUserException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7356388847736724148L;

    public InvalidUserException(String message) {
        super(message);
    }

}

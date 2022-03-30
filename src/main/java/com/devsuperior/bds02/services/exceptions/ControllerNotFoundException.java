package com.devsuperior.bds02.services.exceptions;

public class ControllerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2062434147875331065L;

    public ControllerNotFoundException(String msg) {
        super(msg);
    }
}

package me.dio.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L; // todo → ???

    public BusinessException(String message) {
        super(message);
    }
}

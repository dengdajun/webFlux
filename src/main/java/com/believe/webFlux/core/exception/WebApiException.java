package com.believe.webFlux.core.exception;

import lombok.Getter;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Getter
public class WebApiException extends RuntimeException {
    private ErrorCode errorCode;
    private Object[] args;
    private String defaultMessage;

    public WebApiException(String message, Throwable cause, ErrorCode errorCode, Object... args) {
        super(message, cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public WebApiException(Throwable cause, ErrorCode errorCode, Object... args) {
        super(cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public WebApiException(ErrorCode errorCode, Object... args) {
        this.errorCode = errorCode;
        this.args = args;
    }

    public WebApiException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public WebApiException(String message, Throwable cause) {
        super(message, cause);
        this.defaultMessage = message;
    }

    public WebApiException(Throwable cause) {
        super(cause);
        this.defaultMessage = cause.getMessage();
    }

    public static WebApiException error(ErrorCode errorCode) {
        return new WebApiException(errorCode);
    }


    public static WebApiException error(String defaultMessage) {
        return new WebApiException(defaultMessage);
    }
}

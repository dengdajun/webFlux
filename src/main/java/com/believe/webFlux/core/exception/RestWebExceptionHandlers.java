package com.believe.webFlux.core.exception;

import com.believe.webFlux.core.config.annotation.ErrorCode;
import com.believe.webFlux.core.helper.ApplicationHolder;
import com.believe.webFlux.core.helper.MessagesHelper;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Slf4j
@RestControllerAdvice
public class RestWebExceptionHandlers {

    private final MessagesHelper messagesHelper;

    @Autowired
    public RestWebExceptionHandlers(MessagesHelper messagesHelper) {
        this.messagesHelper = messagesHelper;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleExceptionInternal(Exception ex) {
        errorLog("Exception", ex);
        return onExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        errorLog("MethodArgumentNotValidException", ex);
        return onMethodArgumentNotValidException(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleConstraintViolationException(ConstraintViolationException ex) {
        errorLog("ConstraintViolationException", ex);
        return onConstraintViolationException(ex);
    }

    @ExceptionHandler({TransactionSystemException.class})
    public ErrorMessage handleTransactionSystemException(TransactionSystemException ex) {
        Throwable cause = Throwables.getRootCause(ex);
        errorLog("TransactionSystemException", ex);

        if (cause instanceof ConstraintViolationException) {
            return onConstraintViolationException((ConstraintViolationException) cause);
        }
        return handleExceptionInternal(ex);
    }

    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
    protected ErrorMessage handleConflict(final RuntimeException ex) {
        errorLog("InvalidDataAccessApiUsageException DataAccessException", ex);

        return onExceptionInternal(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ErrorMessage handleBadRequest(final DataIntegrityViolationException ex) {
        errorLog("DataIntegrityViolationException", ex);

        return onExceptionInternal(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WebApiException.class})
    public ErrorMessage handleWebApiException(final WebApiException ex) {
        errorLog("WebApiException", ex);
        if (null != ex.getErrorCode()) {
            return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), messagesHelper.get(ex.getErrorCode().getValue(), ex.getArgs()));
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getDefaultMessage());
    }

    private void errorLog(String typeOfException, Exception exStack) {
        if (ApplicationHolder.isDevOrLocal()) {
            log.error("【{}】Stack: {}", typeOfException, Throwables.getStackTraceAsString(exStack));
        } else {
            log.error("【{}】Stack: {}", typeOfException, Throwables.getRootCause(exStack));
        }
    }

    private ErrorMessage onExceptionInternal(Exception ex, HttpStatus httpStatus) {
        return new ErrorMessage(httpStatus.value(), ex.toString());
    }

    private ErrorMessage onConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            if (propertyPath.contains(".")) {
                errorMessage.append(propertyPath).append(constraintViolation.getMessage());
                continue;
            }
            try {
                ErrorCode errorCode = constraintViolation.getRootBeanClass().getDeclaredField(propertyPath).getAnnotation(ErrorCode.class);
                if (null == errorCode) {
                    errorCode = constraintViolation.getRootBeanClass().getSuperclass().getDeclaredField(propertyPath).getAnnotation(ErrorCode.class);
                }
                if (null != errorCode) {
                    errorMessage.append(messagesHelper.get(errorCode.value()));
                }
            } catch (NoSuchFieldException e) {
                // ignore.
                errorMessage.append(propertyPath).append(constraintViolation.getMessage());
            }
            errorMessage.append(StringUtils.SPACE);
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorMessage.toString());
    }

    private ErrorMessage onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                errorMessage.append(((FieldError) error).getField());
            }
            errorMessage.append(error.getDefaultMessage()).append(",");
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorMessage.toString());
    }

}

package com.believe.webFlux.core.exception;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@RequiredArgsConstructor
@Data
public class ErrorMessage {
    @NonNull
    private int status;
    @NonNull
    private String message;
    private Date timestamp = new Date();
}

package com.believe.webFlux.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNDEFINED("error.undefined");

    private String value;
}

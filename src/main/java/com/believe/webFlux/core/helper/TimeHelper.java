package com.believe.webFlux.core.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeHelper {

    public static Instant now() {
        return Instant.now();
    }
}

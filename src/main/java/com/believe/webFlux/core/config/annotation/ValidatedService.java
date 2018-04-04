package com.believe.webFlux.core.config.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

/**
 * <p> The validated service . </p>
 *
 * @author Li Xingping
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated
@Service
public @interface ValidatedService {

    /**
     * Alias for {@link Validated#value}.
     */
    @AliasFor(annotation = Validated.class, attribute = "value")
    Class<?>[] validatedValue() default {};

    /**
     * Alias for {@link Service#value}.
     */
    @AliasFor(annotation = Service.class)
    String value() default "";
}
